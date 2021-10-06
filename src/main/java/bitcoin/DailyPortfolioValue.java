package bitcoin;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailyPortfolioValue(LocalDate date, BigDecimal value) {
}
