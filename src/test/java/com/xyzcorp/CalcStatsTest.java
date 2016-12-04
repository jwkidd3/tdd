package com.xyzcorp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.util.Optional;

import org.assertj.core.data.Offset;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalcStatsTest {


    @Test
    public void testMinimumWithArrayOfOneItem() {
        CalcStats calcStats = new CalcStats(new int[]{1});
        assertThat(calcStats.getMinimum()).isEqualTo(Optional.of(1));
        assertThat(calcStats.getMinimum()).contains(1);
    }

    @Test
    public void testMinimumWithArrayOfOneDifferentItem() {
        CalcStats calcStats = new CalcStats(new int[]{3});
        assertThat(calcStats.getMinimum()).isEqualTo(Optional.of(3));
        assertThat(calcStats.getMinimum()).contains(3);
    }

    @Test
    public void testMinimumWithEmptyArray() {
        CalcStats calcStats = new CalcStats(new int[]{});
        assertThat(calcStats.getMinimum()).isEqualTo(Optional.empty());
        assertThat(calcStats.getMinimum()).isEmpty();
    }

    @Test
    public void testMinimumWithArrayOfTwoDifferentItem() {
        CalcStats calcStats = new CalcStats(new int[]{4,3});
        assertThat(calcStats.getMinimum()).contains(3);
    }

    @Test
    //Verification
    public void testMinimumWithArrayOfThreeDifferentItem() {
        CalcStats calcStats = new CalcStats(new int[]{4,3,1});
        assertThat(calcStats.getMinimum()).contains(1);
    }

    @Test
    public void testMinimumWithArrayOfThreeDifferentItemsWithALargeNumberAtEnd() {
        CalcStats calcStats = new CalcStats(new int[]{4,3,5});
        assertThat(calcStats.getMinimum()).contains(3);
    }
    /*@Test
    public void testMiniumumWithArrayOfNullStandardWay() {
    	   try { 
    	      new CalcStats(null);
    	      fail("Should not reach this point");
    	   } catch(IllegalArgumentException iae) {
          assertThat(iae).hasMessage("Array is null"); 		   
    	   }
    	}
    
    @Test(expected=IllegalArgumentException.class)
    public void testMinimumWithArrayOfNullOldJUnit4CrappyWay() {
    	   new CalcStats(null); 
    }
    */

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMinimumWithArrayOfNullUsingRule() {
    	   thrown.expect(NullPointerException.class);
       thrown.expectMessage(CalcStats.ARRAY_IS_NULL);
       new CalcStats(null);
    }

    @Test
    public void testMaximumWithArrayOfOneItem() {
        CalcStats calcStats = new CalcStats(new int[]{1});
        assertThat(calcStats.getMaximum()).contains(1);
    }

    @Test
    public void testMaximumWithArrayOfOneDifferentItem() {
        CalcStats calcStats = new CalcStats(new int[]{3});
        assertThat(calcStats.getMaximum()).contains(3);
    }

    @Test
    public void testMaximumWithEmptyArray() {
        CalcStats calcStats = new CalcStats(new int[]{});
        assertThat(calcStats.getMaximum()).isEmpty();
    }

    @Test
    public void testMaximumWithArrayOfTwoDifferentItem() {
        CalcStats calcStats = new CalcStats(new int[]{4,3});
        assertThat(calcStats.getMaximum()).contains(4);
    }

    @Test
    public void testMaximumWithArrayOfTwoDifferentItemsSwapped() {
        CalcStats calcStats = new CalcStats(new int[]{3,4});
        assertThat(calcStats.getMaximum()).contains(4);
    }

    @Test
    public void testMaximumWithArrayOfThreeDifferentItemsWithALargeNumberAtEnd() {
        CalcStats calcStats = new CalcStats(new int[]{5,3,4});
        assertThat(calcStats.getMaximum()).contains(5);
    }
    
    @Test
    public void testAverageWithOneElement() {
        CalcStats calcStats = new CalcStats(new int[]{3});
        assertThat(calcStats.getAverage()).contains(3.0);
    }

    @Test
    public void testAverageWithOneDifferentElement() {
        CalcStats calcStats = new CalcStats(new int[]{4});
        assertThat(calcStats.getAverage()).contains(4.0);
    }

    @Test
    public void testAverageWithNoElements() {
        CalcStats calcStats = new CalcStats(new int[]{});
        assertThat(calcStats.getAverage()).isEmpty();
    }

    @Test
    public void testAverageWithTwoElements() {
        CalcStats calcStats = new CalcStats(new int[]{40, 100});
        assertThat(calcStats.getAverage()).contains(70.0);
    }

    @Test
    public void testAverageWithThreeElements() {
        CalcStats calcStats = new CalcStats(new int[]{6, 9, 15, -2, 92, 11});
        assertThat(calcStats.getAverage().get()).isEqualTo(21.83, Offset.offset(.01));
    }
}











