package re.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import re.edu.dto.request.VehicleCreateRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.PageResponse;
import re.edu.dto.response.VehicleResponse;
import re.edu.entity.Vehicle;
import re.edu.repository.VehicleRepository;
import re.edu.service.VehicleService;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public ApiResponse<VehicleResponse> createVehicle(VehicleCreateRequest request) {
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(request.getLicensePlate())
                .color(request.getColor())
                .vehicleType(request.getVehicleType())
                .build();

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        VehicleResponse response = VehicleResponse.builder()
                .id(savedVehicle.getId())
                .licensePlate(savedVehicle.getLicensePlate())
                .color(savedVehicle.getColor())
                .vehicleType(savedVehicle.getVehicleType())
                .build();

        return ApiResponse.<VehicleResponse>builder()
                .success(true)
                .message("Vehicle created successfully")
                .data(response)
                .build();
    }

    @Override
    public ApiResponse<PageResponse<VehicleResponse>> getPagedVehicles(
            int page,
            int size,
            String sortBy,
            String direction,
            String keyword
    ) {
        if (page < 0) {
            page = 0;
        }

        if (size <= 0) {
            size = 10;
        }

        Sort sort = Sort.unsorted();

        if (sortBy != null && !sortBy.isBlank() && direction != null && !direction.isBlank()) {
            Sort.Direction sortDirection =
                    direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
            sort = Sort.by(sortDirection, sortBy);
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        String normalizedKeyword = (keyword == null || keyword.isBlank()) ? null : keyword.trim();

        Page<VehicleResponse> vehiclePage = vehicleRepository.findAllByKeyword(normalizedKeyword, pageable);

        PageResponse<VehicleResponse> pageResponse = PageResponse.<VehicleResponse>builder()
                .items(vehiclePage.getContent())
                .page(vehiclePage.getNumber())
                .size(vehiclePage.getSize())
                .totalItems(vehiclePage.getTotalElements())
                .totalPages(vehiclePage.getTotalPages())
                .isLast(vehiclePage.isLast())
                .build();

        return ApiResponse.<PageResponse<VehicleResponse>>builder()
                .success(true)
                .message("Vehicles fetched successfully")
                .data(pageResponse)
                .build();
    }
}