package ifasthq.fx.regionalfx.service;

import ifasthq.fx.regionalfx.model.ViewRate;
import java.util.List;

public interface FxRateService {

	List<ViewRate> getAllRates();
	ViewRate getRateByName(String quoteName);
	ViewRate getRateByCurrencyPair(String baseCurrency, String quoteCurrency);

}
