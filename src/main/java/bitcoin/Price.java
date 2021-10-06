package bitcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(LocalDateTime effectiveDate, BigDecimal price) {
}
