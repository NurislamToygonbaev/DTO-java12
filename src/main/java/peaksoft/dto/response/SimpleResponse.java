package peaksoft.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleResponse {
    private HttpStatus httpStatus;
    private String message;
}
