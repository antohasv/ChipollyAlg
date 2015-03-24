package algorithm;

public class CipollaAlg {
    private final BinaryPolinom mInitPolinom;
    private final int mModuleValue;

    public static CipollaAlg initArg(BinaryPolinom initPolinom, int moduleValue) {
        return new CipollaAlg(initPolinom, moduleValue);
    }

    private CipollaAlg(BinaryPolinom initPolinom, int moduleValue) {
        this.mInitPolinom = initPolinom;
        this.mModuleValue = moduleValue;
    }

    public PolinomSolution solve() {
        int discremenantVal = discremenant(mInitPolinom, mModuleValue);
        boolean hasSolution = JakobiAlg.compute(discremenantVal, mModuleValue) == 1;

        if (!hasSolution) {
            // Jakobi == -1
            return null;
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

        if (resultLinearPolinom.getB() != 0) {
            throw new IllegalArgumentException("can't to solve polinom");
        }

        int denominatorD = 2 * mInitPolinom.getA();

        Evklid.EvklidResult evklidResult = Evklid.initArg(denominatorD, mModuleValue).solve();
        int k = evklidResult.x;

        int x1 = Mod.compute((-1 * mInitPolinom.getB() + resultLinearPolinom.getC()) * k, mModuleValue);
        int x2 =  Mod.compute((-1 * mInitPolinom.getB() - resultLinearPolinom.getC()) * k, mModuleValue);

        return new PolinomSolution(x1, x2, discremenantVal, resultLinearPolinom.getC());
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

    public class PolinomSolution {
        private int x1;
        private int x2;

        private int discremenant;
        private int sqrtDiscremenant;

        public PolinomSolution(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        public PolinomSolution(int x1, int x2, int discremenant, int sqrtDiscremenant) {
            this.x1 = x1;
            this.x2 = x2;
            this.discremenant = discremenant;
            this.sqrtDiscremenant = sqrtDiscremenant;
        }

        public int getDiscremenant() {
            return discremenant;
        }

        public int getSqrtDiscremenant() {
            return sqrtDiscremenant;
        }

        public int getX1() {
            return x1;
        }

        public int getX2() {
            return x2;
        }
    }

}
