package ifasthq.fx.regionalfx.service;

import ifasthq.fx.regionalfx.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FxNettingServiceImpl implements FxNettingService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private final FxRequestService fxRequestService;
	private final FxRateService fxRateService;
	private final FxCurrencyService fxCurrencyService;

	@Autowired
	public FxNettingServiceImpl(FxRequestService fxRequestService, FxRateService fxRateService, FxCurrencyService fxCurrencyService) {
		this.fxRequestService = fxRequestService;
		this.fxRateService = fxRateService;
		this.fxCurrencyService = fxCurrencyService;
	}

	@Override
	public Map<String, Object> consolidateRequest() throws Exception {

		List<ConversionRequest> requestList = fxRequestService.getAllConversionRequest();
		List<FundFlow> fundFlowList = new ArrayList<>();
		List<FxTransaction> fxTransactionList = new ArrayList<>();
		BigDecimal sgdValueOfTotalConversion = BigDecimal.ZERO;
		BigDecimal sgdValueOfConversionDone = BigDecimal.ZERO;
		BigDecimal sgdValueOfExcessAmount = BigDecimal.ZERO;

		for(ConversionRequest conversionRequest : requestList) {

			if(conversionRequest.getConversionType().equalsIgnoreCase("BID")) {

				ViewRate spotRate = fxRateService.getRateByCurrencyPair(conversionRequest.getCurrencyFrom(), conversionRequest.getCurrencyTo());
				if(spotRate == null) throw new Exception("No rate found");

				FundFlow inFlow = new FundFlow();
				inFlow.setContractNo(conversionRequest.getContractNo());
				inFlow.setCurrency(conversionRequest.getCurrencyFrom());
				inFlow.setAmount(conversionRequest.getAmount());
				fundFlowList.add(inFlow);

				FundFlow outFlow = new FundFlow();
				outFlow.setContractNo(conversionRequest.getContractNo());
				outFlow.setCurrency(conversionRequest.getCurrencyTo());
				outFlow.setAmount(conversionRequest.getAmount().multiply(spotRate.getAsk()).negate());
				fundFlowList.add(outFlow);

			} else if (conversionRequest.getConversionType().equalsIgnoreCase("ASK")) {

				ViewRate spotRate = fxRateService.getRateByCurrencyPair(conversionRequest.getCurrencyFrom(), conversionRequest.getCurrencyTo());
				if(spotRate == null) throw new Exception("No rate found");

				FundFlow inFlow = new FundFlow();
				inFlow.setContractNo(conversionRequest.getContractNo());
				inFlow.setCurrency(conversionRequest.getCurrencyTo());
				inFlow.setAmount(conversionRequest.getAmount());
				fundFlowList.add(inFlow);

				FundFlow outFlow = new FundFlow();
				outFlow.setContractNo(conversionRequest.getContractNo());
				outFlow.setCurrency(conversionRequest.getCurrencyFrom());
				outFlow.setAmount(conversionRequest.getAmount().multiply(spotRate.getAsk()).negate());
				fundFlowList.add(outFlow);

			}
		}

		LOG.info("===== List of Fund Flow =====");
		fundFlowList.forEach(flow -> LOG.info("{}", flow));


		List<String> currencyList = fxCurrencyService.getAllCurrency();
		Map<String, BigDecimal> consolidatedTotal = new HashMap<>();
		currencyList.forEach(currency -> consolidatedTotal.put(currency, BigDecimal.ZERO));

		for(FundFlow fundFlow : fundFlowList) {
			consolidatedTotal.put(fundFlow.getCurrency(), consolidatedTotal.get(fundFlow.getCurrency()).add(fundFlow.getAmount()));
		}

		LOG.info("===== Summary of Fund Flows =====");
		consolidatedTotal.forEach((k,v) -> {
			if(v.compareTo(BigDecimal.ZERO) != 0)
				LOG.info("{} : {}", k, v);
		});

		Map<String, BigDecimal> summaryOfFundFlows = new HashMap<>(consolidatedTotal);


		Map<String, BigDecimal> excessFunds = new HashMap<>();
		Map<String, BigDecimal> shortfallFunds = new HashMap<>();
		consolidatedTotal.forEach((k,v) -> {
			if(v.compareTo(BigDecimal.ZERO) > 0) {
				excessFunds.put(k, v);
			} else {
				shortfallFunds.put(k, v);
			}
		});



		for (Map.Entry<String, BigDecimal> entry : shortfallFunds.entrySet()) {
			String shortCurrency = entry.getKey();
			BigDecimal shortAmount = entry.getValue();

			if(shortAmount.compareTo(BigDecimal.ZERO) == 0) {
				// filter
			} else {


				for (Map.Entry<String, BigDecimal> excessEntry : excessFunds.entrySet()) {
					String excessCurrency = excessEntry.getKey();
					BigDecimal excessAmount = excessEntry.getValue();

					ViewRate fxRate = fxRateService.getRateByCurrencyPair(excessCurrency, shortCurrency);
					BigDecimal excessAmountInShortCurrency = excessAmount.multiply(fxRate.getAsk());

					if(excessAmountInShortCurrency.compareTo(shortAmount) > 0) {
						// more than enough excess

						BigDecimal amountToSwap = shortAmount.divide(fxRate.getAsk(), 6, BigDecimal.ROUND_HALF_UP);
						LOG.info("Swapping {} to {}...", excessCurrency, shortCurrency);
						LOG.info("Converting {} ({}) to exchange for {} of {}", amountToSwap, excessCurrency, shortAmount, shortCurrency);

						FxTransaction fxTransaction = new FxTransaction();
						fxTransaction.setQuoteName(fxRate.getQuoteName());
						fxTransaction.setOrderId(UUID.randomUUID().toString().toUpperCase().substring(0, 12));
						fxTransaction.setFromCurrency(excessCurrency);
						fxTransaction.setFromAmount(amountToSwap.abs());
						fxTransaction.setToCurrency(shortCurrency);
						fxTransaction.setToAmount(shortAmount.abs());
						fxTransaction.setExecRate(fxRate.getAsk());
						fxTransaction.setExecTime(LocalDateTime.now());
						fxTransactionList.add(fxTransaction);

						ViewRate sgdRate = fxRateService.getRateByCurrencyPair(excessCurrency, "SGD");
						sgdValueOfConversionDone = sgdValueOfConversionDone.add(amountToSwap.abs().multiply(sgdRate.getAsk()));

						excessFunds.put(excessCurrency, excessAmount.add(amountToSwap));
						shortfallFunds.put(shortCurrency, shortAmount.subtract(shortAmount));

					} else {
						// not enough to cover, just convert as much as possible

					}

				}

			}

		}

		excessFunds.forEach(consolidatedTotal::put);
		shortfallFunds.forEach(consolidatedTotal::put);
		Map<String, BigDecimal> balanceFunds = new HashMap<>(consolidatedTotal);
		LOG.info("===== Summary of Ledger Funds After Conversion =====");
		consolidatedTotal.forEach((k,v) -> {
			if(v.compareTo(BigDecimal.ZERO) != 0)
			LOG.info("{} : {}", k, v);
		});


		for(ConversionRequest conversionRequest : requestList) {
			ViewRate sgdRate = fxRateService.getRateByCurrencyPair(conversionRequest.getCurrencyFrom(), "SGD");
			sgdValueOfTotalConversion = sgdValueOfTotalConversion.add(conversionRequest.getAmount().multiply(sgdRate.getAsk()));
		}

		LOG.info("Total SGD value of original conversion needed: {}", sgdValueOfTotalConversion);
		LOG.info("Total SGD value of conversion done today: {}", sgdValueOfConversionDone);


		BigDecimal sgdAmountSavedFromNett = sgdValueOfTotalConversion.subtract(sgdValueOfConversionDone);
		BigDecimal costSaving = sgdAmountSavedFromNett.multiply(new BigDecimal(0.001).setScale(6, BigDecimal.ROUND_HALF_UP));
		LOG.info("Netting reduced conversion amount by approximately {} SGD, which generates cost savings of around {} (based on 10 pips)", sgdAmountSavedFromNett, costSaving);

		for (Map.Entry<String, BigDecimal> entry : consolidatedTotal.entrySet()) {
			String currency = entry.getKey();
			BigDecimal amount = entry.getValue();

			if(amount.compareTo(BigDecimal.ZERO) > 0) {
				ViewRate sgdRate = fxRateService.getRateByCurrencyPair(currency, "SGD");
				sgdValueOfExcessAmount = sgdValueOfExcessAmount.add(amount.multiply(sgdRate.getAsk()));
			}
		}

		LOG.info("Total SGD value of leftover amount: {}", sgdValueOfExcessAmount);
		BigDecimal totalProfit = sgdValueOfExcessAmount.add(costSaving);
		LOG.info("Total profit from this exercise: {}", totalProfit);

		NettingReport nettingReport = new NettingReport();
		nettingReport.setTransactDate(LocalDate.now());
		nettingReport.setReportTime(LocalDateTime.now());
		nettingReport.setTotalValueRequestSGD(sgdValueOfTotalConversion);
		nettingReport.setTotalValueConvertSGD(sgdValueOfConversionDone);
		nettingReport.setTotalValueReduceSGD(sgdAmountSavedFromNett);
		nettingReport.setCostSavingSGD(costSaving);
		nettingReport.setTotalSurplusSGD(sgdValueOfExcessAmount);
		nettingReport.setTotalProfitSGD(totalProfit);

		Map<String, Object> results = new HashMap<>();
		results.put("listOfRequest", requestList);
		results.put("rateList", fxRateService.getAllRates());
		results.put("fundFlowList", fundFlowList);
		results.put("summaryFundFlow", summaryOfFundFlows);
		results.put("fxTransactionList", fxTransactionList);
		results.put("balanceFund", balanceFunds);
		results.put("nettingReport", nettingReport);
		return results;

	}


}
