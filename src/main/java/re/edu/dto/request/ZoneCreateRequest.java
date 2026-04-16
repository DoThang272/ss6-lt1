package re.edu.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneCreateRequest {

    @NotBlank(message = "Tên khu vực để xe không được để trống")
    private String name;

    @NotNull(message = "Sức chứa không được null")
    @Min(value = 1, message = "Sức chứa phải lớn hơn 0")
    private Integer capacity;
}