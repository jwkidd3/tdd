package com.xyzcorp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DieTest {
	@Test
	public void testDefaultIsWithRandom() {
		Die die = new DieImpl(mock(Random.class));
		assertThat(die.getPips()).isEqualTo(1);
	}

	@Test
	public void testRollOf4() {
		// Collaborator
		Random random = new Random() {
			@Override
			public int nextInt(int bound) {
				return 3;
			}
		};

		Die die = new DieImpl(random);
		Die rolledDie = die.roll();
		assertThat(rolledDie.getPips()).isEqualTo(4);
	}

	@Test
	public void testRollOf2() {
		// Collaborator
		Random random = new Random() {
			@Override
			public int nextInt(int bound) {
				return 1;
			}
		};

		Die die = new DieImpl(random);
		Die rolledDie = die.roll();
		assertThat(rolledDie.getPips()).isEqualTo(2);
	}

	@Test
	public void testThatWithRandomConstructorThatDefaultIs1() {
		// Collaborator
		Random random = new Random() {
			@Override
			public int nextInt() {
				return 2;
			}
		};

		Die die = new DieImpl(random);
		assertThat(die.getPips()).isEqualTo(1);
	}

	@Test
	public void testRollTwiceUsingTwoDifferentNumbers() {
		// Create Mock
		Random random = mock(Random.class);

		// Rehearse With the Mock
		when(random.nextInt(5)).thenReturn(4, 3);

		// Test
		Die die = new DieImpl(random);
		Die rolledDie = die.roll().roll();
		assertThat(rolledDie.getPips()).isEqualTo(4);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testThatRandomIsNotNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("Random is null");

		new DieImpl(null);
	}

	@Test
	public void testRollOneMillionTimes() {
		Die die = new DieImpl(new Random());
		for (int i = 0; i < 1000000; i++) {
			assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);
		}
	}

	@Test
	public void testBUG3012() {
		Random random = mock(Random.class);
		when(random.nextInt(7)).thenReturn(5);
		Die die = new DieImpl(random);
		assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);
	}

	@Test
	public void testBUG3012WithZero() {
		Random random = mock(Random.class);
		when(random.nextInt(7)).thenReturn(0);
		Die die = new DieImpl(random);
		assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);
	}

	@Test
	public void testIntegrationTestForDistribution() {
		//Map...
	}
}
