package algorithm;

public class BinaryPolinom extends LinearPolinom {
    /**
     *
     * a * x ^ 2 + b * x + c
     *
     * */

     private int a;

    public BinaryPolinom(int a, int b, int c) {
        super(b, c);
        this.a = a;
    }

    public int getA() {
        return a;
    }
}
