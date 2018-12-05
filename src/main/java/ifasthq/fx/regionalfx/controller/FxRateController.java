package ifasthq.fx.regionalfx.controller;

import ifasthq.fx.regionalfx.service.FxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rate")
public class FxRateController {

	private final FxRateService fxRateService;

	@Autowired
	public FxRateController(FxRateService fxRateService) {
		this.fxRateService = fxRateService;
	}

	@GetMapping()
	public String viewAllRates(Model model) {
		model.addAttribute("rateList", fxRateService.getAllRates());
		return "rate/view_rate";
	}

}
