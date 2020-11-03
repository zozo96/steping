package algorithums.algorithm4th;

/**
 * Created by Songyanyan on 18-4-24
 */
public class MyUtils {
    static public int[] exch(int[] a, int behind, int front) {
        int temp = a[front];
        a[front] = a[behind];
        a[behind] = temp;
        return a;
    }

    static public boolean less(int n1, int n2) {
        if (n1 < n2)
            return true;
        return false;
    }
}
