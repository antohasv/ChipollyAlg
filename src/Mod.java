

public class Mod {
    public static int compute(int val, int mod) {
        int result = val % mod;
        if (result < 0) result += mod;
        return result;
    }
}
