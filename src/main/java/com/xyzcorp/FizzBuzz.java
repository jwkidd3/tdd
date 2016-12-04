package com.xyzcorp;

import java.util.stream.IntStream;

/**
 * Created by danno on 9/30/16.
 */
public class FizzBuzz {
    public static String translate(int number) {
        if (number % 5 == 0 && number % 3 == 0) return "FizzBuzz";
        if (number % 3 == 0) return "Fizz";
        if (number % 5 == 0) return "Buzz";
        return Integer.toString(number);
    }

    public static void main(String[] args) {
        IntStream.range(1, 100).boxed().map(x -> FizzBuzz.translate(x)).forEach(System.out::println);

    }
}
