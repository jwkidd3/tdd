package com.xyzcorp;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by danno on 9/30/16.
 */
public class PenaltyCalculatorTest {

    @Test
    public void testThatCheckoutIstheSameAsCurrent() {
        LocalDate checkoutDate = LocalDate.of(2016,9,20);
        LocalDate todaysDate = LocalDate.of(2016,9,20);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(0);
    }

    @Test
    public void testThatPenaltyIs5AfterOneMonthAndOneDay() {
        LocalDate checkoutDate = LocalDate.of(2016,9,20);
        LocalDate todaysDate = LocalDate.of(2016,10,21);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(5);
    }

    @Test
    public void testThatPenaltyIs5AfterOneMonth() {
        LocalDate checkoutDate = LocalDate.of(2016,9,20);
        LocalDate todaysDate = LocalDate.of(2016,10,20);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(0);
    }

    @Test
    public void testThatPenaltyIs0WithinOneMonth() {
        LocalDate checkoutDate = LocalDate.of(2016,9,20);
        LocalDate todaysDate = LocalDate.of(2016,9,30);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(0);
    }


    @Test
    public void testThatPenaltyAfterWithinTwoMonths() {
        LocalDate checkoutDate = LocalDate.of(2016,9,20);
        LocalDate todaysDate = LocalDate.of(2016,11,20);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(5);
    }


    @Test
    public void testThatPenaltyAfterWithinTwoMonthsAndOneDay() {
        LocalDate checkoutDate = LocalDate.of(2016,9,20);
        LocalDate todaysDate = LocalDate.of(2016,11,21);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(10);
    }

    @Test
    public void testThatPenaltyAfterWithinOneMonthWithJan31() {
        LocalDate checkoutDate = LocalDate.of(2015,1,31);
        LocalDate todaysDate = LocalDate.of(2015,3,1);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(5);
    }

    @Test
    public void testThatPenaltyWithMay31stScenario() {
        LocalDate checkoutDate = LocalDate.of(2015,5,31);
        LocalDate todaysDate = LocalDate.of(2015,7,1);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(5);
    }


    @Test
    public void testThatPenaltyWithAug1stScenario() {
        LocalDate checkoutDate = LocalDate.of(2015,5,31);
        LocalDate todaysDate = LocalDate.of(2015,8,1);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(10);
    }

    @Test
    public void testThatPenaltyWithMay1stScenario() {
        LocalDate checkoutDate = LocalDate.of(2015,5,1);
        LocalDate todaysDate = LocalDate.of(2015,7,30);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(10);
    }


    @Test
    public void testThatPenaltyWithYearScenario() {
        LocalDate checkoutDate = LocalDate.of(2015,5,1);
        LocalDate todaysDate = LocalDate.of(2016,5,1);
        assertThat(PenaltyCalculator.calculate(checkoutDate, todaysDate)).isEqualTo(60);
    }
}
