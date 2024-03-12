package peaksoft.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import peaksoft.enums.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseForAdmin(
        String name,
        String image,
        List<String> colours,
        List<Size> sizes ,
        long quantity,
        BigDecimal price
) {
}
