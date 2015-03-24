package test;

import algorithm.JakobiAlg;

import org.junit.Assert;
import org.junit.Test;

public class JakobiAlgTest {

    @Test(expected=IllegalArgumentException.class)
    public void testBoundaryCondition0And1() {
        JakobiAlg.compute(1, 0);
    }

    @Test
    public void testNegativeValue() {
        Assert.assertTrue(JakobiAlg.compute(-51, 3) == -1);
    }

    @Test
    public void testAlgCorrect() throws Exception {
        Assert.assertTrue(JakobiAlg.compute(219, 383) == 1);
    }
}