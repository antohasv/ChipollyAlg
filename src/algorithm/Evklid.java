package algorithm;

public class Evklid {

    private int mA;
    private int mMod;

    public static Evklid initArg(int a, int mod) {
        return new Evklid(a, mod);
    }

    public Evklid(int mA, int mMod) {
        this.mA = mA;
        this.mMod = mMod;
    }

    public EvklidResult solve() {
        return gcdWide(mA, mMod);
    }

    EvklidResult gcdWide(int a, int b) {
        EvklidResult temphere = new EvklidResult(a, 1, 0);
        EvklidResult temphere2 = new EvklidResult();

        if (b == 0) {
            return temphere;
        }

        temphere2 = gcdWide(b, Mod.compute(a, b));
        temphere = new EvklidResult();
        temphere.d = temphere2.d;
        temphere.x = temphere2.y;
        temphere.y = temphere2.x - ((a / b) * temphere2.y);
        return temphere;
    }

    public class EvklidResult {
        int d;
        int x;
        int y;

        EvklidResult(int one, int two, int three) {
            d = one;
            x = two;
            y = three;
        }

        EvklidResult() {
        }
    }
}
