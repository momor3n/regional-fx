package ifasthq.fx.regionalfx.controller;

import ifasthq.fx.regionalfx.model.*;
import ifasthq.fx.regionalfx.service.FxNettingService;
import ifasthq.fx.regionalfx.service.FxNettingServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nett")
public class FxNettingController {

	private final FxNettingService fxNettingService;

	@Autowired
	public FxNettingController(FxNettingService fxNettingService) {
		this.fxNettingService = fxNettingService;
	}

	@GetMapping
	public String performNetting(Model model) throws Exception {
		model.addAllAttributes(fxNettingService.consolidateRequest());

		/*List<ConversionRequest> requestList = new ArrayList<>();
		List<ViewRate> viewRateList = new ArrayList<>();
		List<FundFlow> fundFlowList = new ArrayList<>();
		Map<String, BigDecimal> summaryOfFundFlows = new HashMap<>();
		List<FxTransaction> fxTransactionList = new ArrayList<>();
		Map<String, BigDecimal> balanceFunds = new HashMap<>();
		NettingReport nettingReport = new NettingReport();

		model.addAttribute("listOfRequest", requestList);
		model.addAttribute("rateList", viewRateList);
		model.addAttribute("fundFlowList", fundFlowList);
		model.addAttribute("summaryFundFlow", summaryOfFundFlows);
		model.addAttribute("fxTransactionList", fxTransactionList);
		model.addAttribute("balanceFund", balanceFunds);
		model.addAttribute("nettingReport", nettingReport);*/

		return "netting/netting";
	}

}
