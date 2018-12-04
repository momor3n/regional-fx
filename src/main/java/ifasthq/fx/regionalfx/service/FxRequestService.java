package ifasthq.fx.regionalfx.service;

import ifasthq.fx.regionalfx.model.ConversionRequest;
import java.util.List;

public interface FxRequestService {

	List<ConversionRequest> getAllConversionRequest();
	void addConversionRequest(ConversionRequest conversionRequest);

}
