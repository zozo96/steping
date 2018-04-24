package algorithm4th.Base1_1.context;

/**
 * 最大公约数  欧几里得算法
 *
 * @author Songyanyan
 */
public class Gcd {
    public static void main(String args[]) {
        System.out.print(gcd(15, 5));
    }

    static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
}
