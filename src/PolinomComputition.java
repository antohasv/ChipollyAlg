
public class PolinomComputition {

    public static BinaryPolinom multiply(LinearPolinom linearPolinom1, LinearPolinom linearPolinom2, int mod) {
        return new BinaryPolinom(Mod.compute(linearPolinom1.getB() * linearPolinom2.getB(), mod),
                Mod.compute(linearPolinom1.getB() * linearPolinom2.getC() + linearPolinom2.getB() * linearPolinom1.getC(), mod),
                Mod.compute(linearPolinom1.getC() * linearPolinom2.getC(), mod)
        );
    }

    public static BinaryPolinom multiplySquare(LinearPolinom linearPolinom, int mod) {
        return multiply(linearPolinom, linearPolinom, mod);
    }

    public static LinearPolinom solvePolinomSystem(BinaryPolinom binaryPolinom, LinearPolinom linearPolinom, int mod) {
        return new LinearPolinom(
                Mod.compute(binaryPolinom.getA() * linearPolinom.getB() + binaryPolinom.getB(), mod),
                Mod.compute(binaryPolinom.getA() * linearPolinom.getC() + binaryPolinom.getC(), mod)
                );
    }
}
