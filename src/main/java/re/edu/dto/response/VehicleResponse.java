
package re.edu.dto.response;

import lombok.*;
import re.edu.entity.TypeVehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private TypeVehicle vehicleType;
}
