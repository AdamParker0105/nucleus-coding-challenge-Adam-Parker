package bitcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(LocalDateTime effectiveDate, BigDecimal numberOfBitcoins) {
}
