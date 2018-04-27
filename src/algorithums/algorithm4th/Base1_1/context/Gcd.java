package algorithm4th.Base1_1.context;


import algorithm4th.MyUtils;

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

class Reverse {
    // 数组元素颠倒
    public void reverse(int[] a) {
        int n = a.length;
        for (int i = 0; i < n / 2; i++) {
            MyUtils.exch(a, i, n - 1 - i);
        }
    }
}

class Matrix {
    // 两方阵相乘
    public void TwoMatrix_Multiply(int[][] a, int[][] b) {
        int n = a.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 计算a.行i和b.列j的点乘
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }
}