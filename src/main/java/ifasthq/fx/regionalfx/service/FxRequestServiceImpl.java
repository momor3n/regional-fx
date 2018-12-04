package ifasthq.fx.regionalfx.service;

import ifasthq.fx.regionalfx.model.ConversionRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FxRequestServiceImpl implements FxRequestService {

	private List<ConversionRequest> conversionRequestList;

	public FxRequestServiceImpl() {
	}

	@PostConstruct
	public void init() {
		conversionRequestList = new ArrayList<>();
		conversionRequestList.add(new ConversionRequest("BID", "UTC001", "UT", "EUR", "USD", new BigDecimal("1000"), LocalDate.now(), LocalDate.now()));
		conversionRequestList.add(new ConversionRequest("ASK", "UTC002", "UT", "USD", "CHN", new BigDecimal("6000"), LocalDate.now(), LocalDate.now()));
	}

	@Override
	public List<ConversionRequest> getAllConversionRequest() {
		return conversionRequestList;
	}

	@Override
	public void addConversionRequest(ConversionRequest conversionRequest) {
		conversionRequestList.add(conversionRequest);
	}
}
