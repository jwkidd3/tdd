package com.xyzcorp;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

public class CalcStats {

    public static final String ARRAY_IS_NULL = "Array is null";
    private int[] array;

    public CalcStats(int[] array) {
        Objects.requireNonNull(array, ARRAY_IS_NULL);
        this.array = array;
    }

    private Optional<Integer> process(BiPredicate<Integer, Integer> biPredicate) {
        if (array.length == 0) return Optional.empty();
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (biPredicate.test(result, array[i])) {
                result = array[i];
            }
        }
        return Optional.of(result);
    }

    public Optional<Integer> getMinimum() {
        return process((result, next) -> result > next);
    }

    public Optional<Integer> getMaximum() {
        return process((result, next) -> result < next);
    }

    public Optional<Double> getAverage() {
        if (array.length == 0) return Optional.empty();
        int sum = 0;
        for (int element: array) {
            sum += element;
        }
        return Optional.of(((double) sum) / array.length);
    }
}
