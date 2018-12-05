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
	}

	@Override
	public List<ViewRate> getAllRates() {
		return sampleRates;
	}
}
