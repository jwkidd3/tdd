package com.xyzcorp;

import org.junit.Test;

import java.util.Random;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class LambdaDieTest {

    @Test
    public void testDefaultIsWithRandom() {
        LambdaDie die = new LambdaDie(() -> 4);
        assertThat(die.getPips()).isEqualTo(1);
    }


}
