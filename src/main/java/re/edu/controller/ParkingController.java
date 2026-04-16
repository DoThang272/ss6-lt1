package re.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.TicketRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.TicketResponse;
import re.edu.service.ParkingService;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/check-in")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TicketResponse> checkIn(@Valid @RequestBody TicketRequest request) {
        return parkingService.checkIn(request);
    }

    @PutMapping("/check-out/{vehicleId}")
    public ApiResponse<TicketResponse> checkOut(@PathVariable Long vehicleId) {
        return parkingService.checkOut(vehicleId);
    }
}