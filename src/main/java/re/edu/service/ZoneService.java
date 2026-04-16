package re.edu.service;

import re.edu.dto.request.ZoneCreateRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.ZoneResponse;

public interface ZoneService {
    ApiResponse<ZoneResponse> createZone(ZoneCreateRequest request);
}