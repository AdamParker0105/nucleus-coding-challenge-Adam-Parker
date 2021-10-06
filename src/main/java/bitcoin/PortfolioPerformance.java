package bitcoin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PortfolioPerformance {

    private static final List<Price> PRICES = List.of(
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 5, 0, 0), new BigDecimal("35464.53")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 2, 5, 0, 0), new BigDecimal("35658.76")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 3, 5, 0, 0), new BigDecimal("36080.06")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 3, 13, 0, 0), new BigDecimal("37111.11")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 6, 5, 0, 0), new BigDecimal("38041.47")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 7, 5, 0, 0), new BigDecimal("34029.61")));

    private static final List<Transaction> TRANSACTIONS = List.of(
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 9, 0, 0), new BigDecimal("0.012")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 15, 0, 0), new BigDecimal("-0.007")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 4, 9, 0, 0), new BigDecimal("0.017")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 5, 9, 0, 0), new BigDecimal("-0.01")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 7, 9, 0, 0), new BigDecimal("0.1")));

    // Complete this method to return a list of daily portfolio values with one record for each day from the 01-09-2021-07-09-2021 in ascending date order
    public static List<DailyPortfolioValue> getDailyPortfolioValues() {

        List<DailyPortfolioValue> dailyPortfolioValueList = new ArrayList<>();
        BigDecimal totalBitcoin = new BigDecimal("0");
        BigDecimal portfolioValue = new BigDecimal("0");

        LocalDateTime startDate = TRANSACTIONS.get(0).effectiveDate();
        LocalDateTime endDate = TRANSACTIONS.get(TRANSACTIONS.size() - 1).effectiveDate();

        int TransactionTracker = 0;
        int priceChangeTracker = 0;
        LocalDate dateTracker = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());

        // for each day of the portfolio report
        for(int i = 0; i < startDate.until(endDate, ChronoUnit.DAYS) + 1; i++)
        {
            // for each transaction per day
            for(int j = TransactionTracker; j < TRANSACTIONS.size(); j++)
            {
                // check to see if the transaction happened on the current day
                if(TRANSACTIONS.get(j).effectiveDate().getDayOfYear() == dateTracker.getDayOfYear())
                {
                    // for each remaining price change
                    for(int k = priceChangeTracker; k < PRICES.size(); k++)
                    {
                        if(PRICES.get(k).effectiveDate().isAfter(TRANSACTIONS.get(j).effectiveDate()))
                        {
                            priceChangeTracker = k;
                            totalBitcoin = totalBitcoin.add(TRANSACTIONS.get(j).numberOfBitcoins());
                            break;
                        }
                        else if(k == PRICES.size() - 1 && PRICES.get(k).effectiveDate().getDayOfYear() == TRANSACTIONS.get(j).effectiveDate().getDayOfYear())
                        {
                            priceChangeTracker = k;
                            totalBitcoin = totalBitcoin.add(TRANSACTIONS.get(j).numberOfBitcoins());
                            break;
                        }
                    }
                }
                // transaction happens on another day
                else
                {
                    TransactionTracker = j;
                    break;
                }
            }
            // add end of day value to list
            for(int k = priceChangeTracker; k < PRICES.size(); k++)
            {
                if(PRICES.get(k).effectiveDate().getDayOfYear() > dateTracker.getDayOfYear())
                {
                    portfolioValue = totalBitcoin.multiply(PRICES.get(k - 1).price());
                    dailyPortfolioValueList.add(new DailyPortfolioValue(dateTracker, portfolioValue));

                    break;
                }
                else if(i == startDate.until(endDate, ChronoUnit.DAYS))
                {
                    portfolioValue = totalBitcoin.multiply(PRICES.get(PRICES.size() - 1).price());
                    dailyPortfolioValueList.add(new DailyPortfolioValue(dateTracker, portfolioValue));
                }
            }
            dateTracker = dateTracker.plusDays(1);
        }
        return dailyPortfolioValueList;
    }
}