package ifasthq.fx.regionalfx.service;

import ifasthq.fx.regionalfx.model.ViewRate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FxRateServiceImpl implements FxRateService {

	private List<ViewRate> sampleRates;

	public FxRateServiceImpl() {
	}

	@PostConstruct
	public void init() {
		sampleRates = new ArrayList<>();
		sampleRates.add(new ViewRate("EUR/USD",new BigDecimal("1.13285"),new BigDecimal("1.13267"),"EUR","USD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("USD/CHN",new BigDecimal("6.8498"),new BigDecimal("6.8492"),"USD","CHN", LocalDateTime.now()));
		sampleRates.add(new ViewRate("EUR/CHN",new BigDecimal("7.7795"),new BigDecimal("7.7792"),"EUR","CHN", LocalDateTime.now()));

		sampleRates.add(new ViewRate("USD/SGD",new BigDecimal("1.42184"),new BigDecimal("1.42164"),"USD","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("CHN/SGD",new BigDecimal("0.1983"),new BigDecimal("0.1980"),"CHN","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("HKD/SGD",new BigDecimal("0.1759"),new BigDecimal("0.1758"),"HKD","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("SGD/SGD",new BigDecimal("1.0"),new BigDecimal("1.0"),"SGD","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("EUR/SGD",new BigDecimal("1.5831"),new BigDecimal("1.5829"),"EUR","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("AUD/SGD",new BigDecimal("0.9779"),new BigDecimal("0.9777"),"AUD","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("NZD/SGD",new BigDecimal("0.9056"),new BigDecimal("0.9052"),"NZD","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("CHF/SGD",new BigDecimal("1.3836"),new BigDecimal("1.3833"),"CHF","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("GBP/SGD",new BigDecimal("1.7906"),new BigDecimal("1.7902"),"GBP","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("JPY/SGD",new BigDecimal("0.01219"),new BigDecimal("0.01209"),"JPY","SGD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("CNY/SGD",new BigDecimal("0.1983"),new BigDecimal("0.1980"),"CNY","SGD", LocalDateTime.now()));
	}

	@Override
	public List<ViewRate> getAllRates() {
		return sampleRates;
	}

	@Override
	public ViewRate getRateByName(String quoteName) {
		return sampleRates
			.stream()
			.filter(rate -> rate.getQuoteName().equalsIgnoreCase(quoteName))
			.findFirst()
			.orElse(null);
	}

	@Override
	public ViewRate getRateByCurrencyPair(String baseCurrency, String quoteCurrency) {
		return sampleRates
			.stream()
			.filter(rate -> rate.getCurrencyBid().equalsIgnoreCase(baseCurrency) && rate.getCurrencyAsk().equalsIgnoreCase(quoteCurrency))
			.findFirst()
			.orElse(null);
	}
}
