package test;

import algorithm.BinaryPolinom;
import algorithm.CipollaAlg;
import algorithm.JakobiAlg;
import algorithm.PolinomComputition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CipollaAlgTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAlgCorrect1() throws Exception {
        final int mod = 269;
        BinaryPolinom initPolinom = new BinaryPolinom(78, 71, 15);
        CipollaAlg.PolinomSolution polinomSolution = CipollaAlg.initArg(initPolinom, mod).solve();

        Assert.assertEquals(PolinomComputition.solveSystem(initPolinom, polinomSolution.getX1(), mod), 0);
        Assert.assertEquals(PolinomComputition.solveSystem(initPolinom, polinomSolution.getX2(), mod), 0);
    }

    @Test
    public void testAlgCorrect2() throws Exception {
        final int mod = 281;
        BinaryPolinom initPolinom = new BinaryPolinom(145, 19, 109);
        CipollaAlg.PolinomSolution polinomSolution = CipollaAlg.initArg(initPolinom, mod).solve();
        Assert.assertEquals(PolinomComputition.solveSystem(initPolinom, polinomSolution.getX1(), mod), 0);
        Assert.assertEquals(PolinomComputition.solveSystem(initPolinom, polinomSolution.getX2(), mod), 0);
    }

    @Test
    public void testAlgCorrect3() throws Exception {
        final int mod = 257;
        BinaryPolinom initPolinom = new BinaryPolinom(173, 90, 141);
        CipollaAlg.PolinomSolution polinomSolution = CipollaAlg.initArg(initPolinom, mod).solve();
        Assert.assertEquals(PolinomComputition.solveSystem(initPolinom, polinomSolution.getX1(), mod), 0);
        Assert.assertEquals(PolinomComputition.solveSystem(initPolinom, polinomSolution.getX2(), mod), 0);
    }

    @Test
    public void testHasNoSolution() throws Exception {
        final int mod = 269;
        BinaryPolinom initPolinom = new BinaryPolinom(1, 71, 15);
        CipollaAlg.PolinomSolution polinomSolution = CipollaAlg.initArg(initPolinom, mod).solve();
        Assert.assertNull(polinomSolution);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBoundaryCondition0And1() {
        JakobiAlg.compute(1, 0);
    }
}