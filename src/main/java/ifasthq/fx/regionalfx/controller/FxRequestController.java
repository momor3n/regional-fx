package ifasthq.fx.regionalfx.controller;

import ifasthq.fx.regionalfx.model.ConversionRequest;
import ifasthq.fx.regionalfx.service.FxRequestService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class FxRequestController {

	private final FxRequestService fxRequestService;
	private List<String> conversionTypeList = new ArrayList<>();
	private List<String> tradeTypeList = new ArrayList<>();
	private List<String> currencyList = new ArrayList<>();

	@Autowired
	public FxRequestController(FxRequestService fxRequestService) {
		this.fxRequestService = fxRequestService;
	}

	@PostConstruct
	public void init() {
		conversionTypeList = new ArrayList<>();
		conversionTypeList.add("BID");
		conversionTypeList.add("ASK");

		tradeTypeList = new ArrayList<>();
		tradeTypeList.add("UT");
		tradeTypeList.add("BND");

		currencyList = new ArrayList<>();
		currencyList.add("AUD");
		currencyList.add("CHF");
		currencyList.add("CNH");
		currencyList.add("EUR");
		currencyList.add("GBP");
		currencyList.add("HKD");
		currencyList.add("JPY");
		currencyList.add("SGD");
		currencyList.add("USD");
	}

	@GetMapping
	public String dirViewFxRequest(Model model) {
		model.addAttribute("requestList", fxRequestService.getAllConversionRequest());
		return "fx-request/view_request";
	}

	@GetMapping("/create")
	public String dirCreateFxRequest(Model model) {
		model.addAttribute("conversionRequest", new ConversionRequest());
		model.addAttribute("conversionTypeList", conversionTypeList);
		model.addAttribute("tradeTypeList", tradeTypeList);
		model.addAttribute("currencyList", currencyList);
		return "fx-request/create_request";
	}

	@PostMapping
	public String createConversionRequest(@ModelAttribute("conversionRequest") ConversionRequest conversionRequest) {
		fxRequestService.addConversionRequest(conversionRequest);
		return "fx-request/result";
	}

}
