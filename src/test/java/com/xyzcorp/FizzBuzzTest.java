package com.xyzcorp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Test
    public void testFizzBuzzOf1() {
        assertThat(FizzBuzz.translate(1)).isEqualTo("1");
    }

    @Test
    public void testFizzBuzzOf2() {
        assertThat(FizzBuzz.translate(2)).isEqualTo("2");
    }

    @Test
    public void testFizzBuzzOf3() {
        assertThat(FizzBuzz.translate(3)).isEqualTo("Fizz");
    }

    @Test
    public void testFizzBuzzOf4() {
        assertThat(FizzBuzz.translate(4)).isEqualTo("4");
    }

    @Test
    public void testFizzBuzzOf5() {
        assertThat(FizzBuzz.translate(5)).isEqualTo("Buzz");
    }

    @Test
    public void testFizzBuzzOf10() {
        assertThat(FizzBuzz.translate(10)).isEqualTo("Buzz");
    }

    @Test
    public void testFizzBuzzOf6() {
        assertThat(FizzBuzz.translate(6)).isEqualTo("Fizz");
    }

    @Test
    public void testFizzBuzzOf15() {
        assertThat(FizzBuzz.translate(15)).isEqualTo("FizzBuzz");
    }


}
