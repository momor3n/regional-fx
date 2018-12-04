package ifasthq.fx.regionalfx.controller;

import ifasthq.fx.regionalfx.model.ViewRate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rate")
public class FxRateController {

	private List<ViewRate> sampleRates;

	@PostConstruct
	public void init() {
		sampleRates = new ArrayList<>();
		sampleRates.add(new ViewRate("EUR/USD",new BigDecimal("1.13285"),new BigDecimal("1.13267"),"EUR","USD", LocalDateTime.now()));
		sampleRates.add(new ViewRate("USD/CHN",new BigDecimal("6.8498"),new BigDecimal("6.8492"),"USD","CHN", LocalDateTime.now()));
	}

	@GetMapping()
	public String viewAllRates(Model model) {
		model.addAttribute("rateList", sampleRates);
		return "rate/view_rate";
	}

}
