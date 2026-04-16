package re.edu.service;

import re.edu.dto.request.TicketRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.TicketResponse;

public interface ParkingService {
    ApiResponse<TicketResponse> checkIn(TicketRequest request);
    ApiResponse<TicketResponse> checkOut(Long vehicleId);
}
