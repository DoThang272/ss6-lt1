package re.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.VehicleCreateRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.PageResponse;
import re.edu.dto.response.VehicleResponse;
import re.edu.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ApiResponse<VehicleResponse> createVehicle(@Valid @RequestBody VehicleCreateRequest request) {
        return vehicleService.createVehicle(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<VehicleResponse>> getVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword
    ) {
        return vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);
    }
}