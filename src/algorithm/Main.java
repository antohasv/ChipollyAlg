package algorithm;

public class Main {

    /**
     *
     * a * x ^ 2 + b * x + c = 0 * (mod p)
     *
     * */

     public static void main(String[] args) {

         BinaryPolinom initPolinom = new BinaryPolinom(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
         CipollaAlg.PolinomSolution solution = CipollaAlg.initArg(initPolinom, Integer.parseInt(args[3])).solve();
         System.out.println("Sqrt(" + solution.getDiscremenant() + ") = " + solution.getSqrtDiscremenant());
         System.out.println("x1 = " + solution.getX1() + "\n" + "x2 = " + solution.getX2());
    }
}
