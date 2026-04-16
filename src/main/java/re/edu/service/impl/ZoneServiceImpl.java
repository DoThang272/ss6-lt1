package re.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.dto.request.ZoneCreateRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.ZoneResponse;
import re.edu.entity.Zone;
import re.edu.repository.ZoneRepository;
import re.edu.service.ZoneService;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    @Override
    public ApiResponse<ZoneResponse> createZone(ZoneCreateRequest request) {
        if (zoneRepository.existsByName(request.getName())) {
            throw new RuntimeException("Zone name already exists");
        }

        Zone zone = Zone.builder()
                .name(request.getName())
                .capacity(request.getCapacity())
                .occupiedSpots(0)
                .build();

        Zone savedZone = zoneRepository.save(zone);

        ZoneResponse response = ZoneResponse.builder()
                .id(savedZone.getId())
                .name(savedZone.getName())
                .capacity(savedZone.getCapacity())
                .occupiedSpots(savedZone.getOccupiedSpots())
                .build();

        return ApiResponse.<ZoneResponse>builder()
                .success(true)
                .message("Zone created successfully")
                .data(response)
                .build();
    }
}