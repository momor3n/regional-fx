package ifasthq.fx.regionalfx.controller;

import ifasthq.fx.regionalfx.model.ConversionRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fx")
public class FxTransactionController {

	private List<ConversionRequest> conversionRequestList;
	private List<String> conversionTypeList = new ArrayList<>();
	private List<String> tradeTypeList = new ArrayList<>();
	private List<String> currencyList = new ArrayList<>();

	@PostConstruct
	public void init() {
		conversionRequestList = new ArrayList<>();
		conversionRequestList.add(new ConversionRequest("BID", "CTC0000001", "UT", "SGD", "USD", new BigDecimal("200"), LocalDate.now(), LocalDate.now()));
		conversionRequestList.add(new ConversionRequest("ASK", "CTC0000001", "UT", "SGD", "USD", new BigDecimal("200"), LocalDate.now(), LocalDate.now()));

		conversionTypeList = new ArrayList<>();
		conversionTypeList.add("BID");
		conversionTypeList.add("ASK");

		tradeTypeList = new ArrayList<>();
		tradeTypeList.add("Unit Trust");
		tradeTypeList.add("Bonds");

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

	@GetMapping("/add_transaction")
	public String goAddNewTransaction(Model model) {
		model.addAttribute("conversionRequest", new ConversionRequest());
		model.addAttribute("conversionTypeList", conversionTypeList);
		model.addAttribute("tradeTypeList", tradeTypeList);
		model.addAttribute("currencyList", currencyList);
		return "add_transaction";
	}

	@GetMapping("/transaction")
	public String greeting(Model model) {
		model.addAttribute("requestList", conversionRequestList);
		return "view_transaction";
	}

	@PostMapping("/transaction")
	public String greetingSubmit(@ModelAttribute("conversionRequest") ConversionRequest conversionRequest) {
		conversionRequestList.add(conversionRequest);
		return "result";
	}

}