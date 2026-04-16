package re.edu.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import re.edu.entity.TypeVehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleCreateRequest {
    @NotBlank(message = "Biển số không được để trống")
    private String licensePlate;

    @NotBlank(message = "Màu xe không được để trống")
    private String color;

    @NotNull(message = "Loại phương tiện không được null")
    private TypeVehicle vehicleType;
}
