package re.edu.service;

import re.edu.dto.request.VehicleCreateRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.PageResponse;
import re.edu.dto.response.VehicleResponse;

public interface VehicleService {
    ApiResponse<VehicleResponse> createVehicle(VehicleCreateRequest request);
    ApiResponse<PageResponse<VehicleResponse>> getPagedVehicles(
            int page,
            int size,
            String sortBy,
            String direction,
            String keyword
    );
}