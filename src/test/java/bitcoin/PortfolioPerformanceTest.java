package bitcoin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortfolioPerformanceTest {

    @Test
    public void verify_output() {
        assertEquals(List.of(
                        new DailyPortfolioValue(LocalDate.of(2021, 9, 1), new BigDecimal("177.32265")),
                        new DailyPortfolioValue(LocalDate.of(2021, 9, 2), new BigDecimal("178.29380")),
                        new DailyPortfolioValue(LocalDate.of(2021, 9, 3), new BigDecimal("185.55555")),
                        new DailyPortfolioValue(LocalDate.of(2021, 9, 4), new BigDecimal("816.44442")),
                        new DailyPortfolioValue(LocalDate.of(2021, 9, 5), new BigDecimal("445.33332")),
                        new DailyPortfolioValue(LocalDate.of(2021, 9, 6), new BigDecimal("456.49764")),
                        new DailyPortfolioValue(LocalDate.of(2021, 9, 7), new BigDecimal("3811.31632"))),
                PortfolioPerformance.getDailyPortfolioValues());
    }
}
