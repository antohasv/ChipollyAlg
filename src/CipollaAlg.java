public class CipollaAlg {
    private final BinaryPolinom mInitPolinom;
    private final int mModuleValue;

    public CipollaAlg(BinaryPolinom mInitPolinom, int mModuleValue) {
        this.mInitPolinom = mInitPolinom;
        this.mModuleValue = mModuleValue;
    }

    public void execute() {
        int discremenantVal = discremenant(mInitPolinom, mModuleValue);
        boolean hasSolution = JakobiAlg.compute(discremenantVal, mModuleValue) == 1;

        if (!hasSolution) {
            // Jakobi == -1
            return;
        }
        // x ^ 2 - t * x + discremenantValue
        int t = iterateValueT(discremenantVal);

        // x ^ 2 = t * x - discremenantValue
        LinearPolinom initLinearPolinom = new LinearPolinom(t, (-1) * discremenantVal);

        // x ^ ((p + 1) / 2)
        int valDegree = (mModuleValue + 1) / 2;
        int tmpDegree = valDegree;

        LinearPolinom resultLinearPolinom = new LinearPolinom((valDegree & 1), (valDegree & 1) == 1 ? 0 : 1);
        LinearPolinom linearIncrementalPolinom = initLinearPolinom.copy(); // x ^ 2 = t * x - discremenantValue

        tmpDegree = tmpDegree >> 1; //x ^ 2

        if ((tmpDegree & 1) == 1) {
            resultLinearPolinom = getComputedResult(initLinearPolinom, resultLinearPolinom, linearIncrementalPolinom);
        }

        tmpDegree = tmpDegree >> 1;
        while (tmpDegree > 0) {
            linearIncrementalPolinom = getSquarePolinom(linearIncrementalPolinom, initLinearPolinom); // x ^ 2 * x ^ 2 * ...
            if ((tmpDegree & 1) == 1) {
                resultLinearPolinom = getComputedResult(initLinearPolinom, resultLinearPolinom, linearIncrementalPolinom);
            }
            tmpDegree = tmpDegree >> 1;
        }
    }

    private LinearPolinom getSquarePolinom(LinearPolinom linearIncrementalPolinom, LinearPolinom initLinearPolinom) {
        BinaryPolinom binaryPolinomTmp = PolinomComputition.multiplySquare(linearIncrementalPolinom, mModuleValue);
        return PolinomComputition.solvePolinomSystem(binaryPolinomTmp, initLinearPolinom, mModuleValue);
    }

    private LinearPolinom getComputedResult(LinearPolinom initLinearPolinom, LinearPolinom resultLinearPolinom, LinearPolinom linearIncrementalPolinom) {
        BinaryPolinom binaryPolinomTmp = PolinomComputition.multiply(resultLinearPolinom, linearIncrementalPolinom, mModuleValue);
        return PolinomComputition.solvePolinomSystem(binaryPolinomTmp, initLinearPolinom, mModuleValue);
    }


    public static int discremenant(BinaryPolinom binaryPolinom, int module) {
        return Mod.compute(
                binaryPolinom.getB() * binaryPolinom.getB() - 4 * binaryPolinom.getA() * binaryPolinom.getC(), module);
    }

    private int iterateValueT(int discremenantVal) {
        int tmp = 0;
        for (int i = 1; i < mModuleValue; i++) {
            tmp = Mod.compute(i * i - 4 * discremenantVal, mModuleValue);
            if (JakobiAlg.compute(tmp, mModuleValue) == -1) {
                return i;
            }
        }
        throw new IllegalArgumentException("iterateValueT can't to find a right t value");
    }

}
