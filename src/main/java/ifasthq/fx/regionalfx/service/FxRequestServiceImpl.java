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
		conversionRequestList.add(new ConversionRequest("BID", "UTC002", "UT", "EUR", "USD", new BigDecimal("5000"), LocalDate.now(), LocalDate.now()));
		conversionRequestList.add(new ConversionRequest("BID", "UTC003", "UT", "EUR", "USD", new BigDecimal("5000"), LocalDate.now(), LocalDate.now()));
		conversionRequestList.add(new ConversionRequest("BID", "UTC004", "UT", "USD", "CHN", new BigDecimal("6000"), LocalDate.now(), LocalDate.now()));
		conversionRequestList.add(new ConversionRequest("BID", "UTC005", "UT", "USD", "CHN", new BigDecimal("2000"), LocalDate.now(), LocalDate.now()));
		conversionRequestList.add(new ConversionRequest("BID", "UTC006", "UT", "USD", "CHN", new BigDecimal("4000"), LocalDate.now(), LocalDate.now()));
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
