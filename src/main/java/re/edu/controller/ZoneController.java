package re.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.ZoneCreateRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.ZoneResponse;
import re.edu.service.ZoneService;

@RestController
@RequestMapping("/api/v1/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ZoneResponse> createZone(@Valid @RequestBody ZoneCreateRequest request) {
        return zoneService.createZone(request);
    }
}