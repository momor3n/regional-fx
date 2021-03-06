package ifasthq.fx.regionalfx.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class ConversionRequest {

	private String conversionType; // from or to
	private String contractNo;
	private String tradeType; // UT or Bond
	private String currencyFrom;
	private String currencyTo;
	private BigDecimal amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate requestDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate conversionDate;

	public ConversionRequest() {
		requestDate = LocalDate.now();
		conversionDate = LocalDate.now();
	}

	public ConversionRequest(String conversionType, String contractNo, String tradeType, String currencyFrom, String currencyTo, BigDecimal amount, LocalDate requestDate, LocalDate conversionDate) {
		this.conversionType = conversionType;
		this.contractNo = contractNo;
		this.tradeType = tradeType;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amount = amount;
		this.requestDate = requestDate;
		this.conversionDate = conversionDate;
	}

	@Override
	public String toString() {
		return "ConversionRequest{" +
			"conversionType='" + conversionType + '\'' +
			", contractNo='" + contractNo + '\'' +
			", tradeType='" + tradeType + '\'' +
			", currencyFrom='" + currencyFrom + '\'' +
			", currencyTo='" + currencyTo + '\'' +
			", amount=" + amount +
			", requestDate=" + requestDate +
			", conversionDate=" + conversionDate +
			'}';
	}

	public String getConversionType() {
		return conversionType;
	}

	public void setConversionType(String conversionType) {
		this.conversionType = conversionType;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDate getConversionDate() {
		return conversionDate;
	}

	public void setConversionDate(LocalDate conversionDate) {
		this.conversionDate = conversionDate;
	}
}
