package ifasthq.fx.regionalfx.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FxCurrencyServiceImpl implements FxCurrencyService {

	private List<String> currencyList = new ArrayList<>();

	@PostConstruct
	public void init() {
		currencyList.add("USD");
		currencyList.add("CHN");
		currencyList.add("SGD");
		currencyList.add("HKD");
		currencyList.add("EUR");
		currencyList.add("AUD");
		currencyList.add("NZD");
		currencyList.add("CHF");
		currencyList.add("GBP");
		currencyList.add("JPY");
	}

	@Override
	public List<String> getAllCurrency() {
		return currencyList;
	}
}
