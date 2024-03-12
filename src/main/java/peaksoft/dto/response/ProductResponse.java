package peaksoft.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        String image,
        String name,
        BigDecimal price
) {
}
