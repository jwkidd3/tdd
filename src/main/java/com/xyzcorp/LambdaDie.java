package com.xyzcorp;

import java.util.function.Supplier;

/**
 * Created by danno on 9/30/16.
 */
public class LambdaDie implements Die {
    public LambdaDie(Supplier<Integer> supplier) {
    }

    public int getPips() {
        return 0;
    }

    @Override
    public Die roll() {
        return null;
    }
}
