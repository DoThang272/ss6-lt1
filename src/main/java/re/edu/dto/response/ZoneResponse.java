package re.edu.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneResponse {
    private Long id;
    private String name;
    private Integer capacity;
    private Integer occupiedSpots;
}