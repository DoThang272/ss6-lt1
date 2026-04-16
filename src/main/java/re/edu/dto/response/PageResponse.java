package re.edu.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> items;
    private int size;
    private int page;
    private long totalItems;
    private int totalPages;
    private boolean isLast;
}
