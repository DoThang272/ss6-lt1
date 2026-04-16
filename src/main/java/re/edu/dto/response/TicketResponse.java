package re.edu.dto.response;

import lombok.*;
import re.edu.entity.TypeVehicle;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private TypeVehicle typeVehicle;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}