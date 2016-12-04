package com.xyzcorp;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.MONTHS;

/**
 * Created by danno on 9/30/16.
 */
public class PenaltyCalculator {
    public static int calculate(LocalDate checkoutDate, LocalDate todaysDate) {
        int deduction = 1;
        if(todaysDate.getDayOfMonth() == 1) deduction = 0;
        return ((int)MONTHS.between(checkoutDate, todaysDate.minusDays(deduction))) * 5;
    }
}
