package ifasthq.fx.regionalfx;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FxTransactionController {

	@GetMapping("/fx/transaction")
	public String greeting(Model model) {

		List<ParamConversionRequest> conversionRequestList = new ArrayList<>();

		conversionRequestList.add(
			new ParamConversionRequest(
				"BID",
				"CTC0000001",
				"UT",
				"SGD",
				"USD",
				new BigDecimal("200"),
				LocalDate.now(),
				LocalDate.now()
			)
		);

		conversionRequestList.add(
			new ParamConversionRequest(
				"ASK",
				"CTC0000001",
				"UT",
				"SGD",
				"USD",
				new BigDecimal("200"),
				LocalDate.now(),
				LocalDate.now()
			)
		);

		model.addAttribute("requestList", conversionRequestList);
		return "view_transaction";
	}

}
