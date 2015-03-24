package algorithm;

public class LinearPolinom {
    /**
     *
     * b * x + c
     *
     * */

    private int b;
    private int c;

    public LinearPolinom(int b, int c) {
        this.b = b;
        this.c = c;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public LinearPolinom copy() {
        return new LinearPolinom(b, c);
    }
}
