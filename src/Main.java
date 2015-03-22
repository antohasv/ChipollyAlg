
public class Main {

    /**
     *
     * a * x ^ 2 + b * x + c = 0 * (mod p)
     *
     * */

     public static void main(String[] args) {

         BinaryPolinom initPolinom = new BinaryPolinom(11, 13, 7);
         new CipollaAlg(initPolinom, 29).execute();
    }

    public static int discremenant(int a, int b, int c, int p) {
        return (b * b - 4 * a * c) % p;
    }

}
