package re.edu.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequest {

    @NotNull(message = "Vehicle id must not be null")
    private Long vehicleId;

    @NotNull(message = "Zone id must not be null")
    private Long zoneId;
}