package com.xyzcorp;

import java.util.Objects;
import java.util.Random;

/**
 * Created by danno on 9/30/16.
 */
public class DieImpl implements Die {
    private final Random random;
    private final int pips;

    public DieImpl(Random random) {
        this(random, 1);
    }

    public DieImpl(Random random, int pips) {
    	   Objects.requireNonNull(random, "Random is null");
       this.random = random;
    	   this.pips = pips;
    }

    @Override
    public int getPips() {
        return pips;
    }

    @Override
    public Die roll() {
        return new DieImpl(random, random.nextInt(5) + 1);
    }
}
