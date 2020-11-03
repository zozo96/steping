package algorithums.leetCode.basic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * 最大公约数
 *
 * @author : Sonya
 * @date : 2019/12/6 17:17
 */
public class GCD {
    /**
     * 多个值的最简比
     */
    public static int[] simpleByMulti(int[] source) {
        HashSet<Integer> sourceSet = (HashSet<Integer>) Arrays.stream(source).boxed().collect(Collectors.toSet());
        // 1. 获取多个值的最大公约数
        while (sourceSet.size() > 1) {
            int[] tmp = new int[sourceSet.size()];
            Iterator ss = sourceSet.iterator();
            for (int i = 0; i < tmp.length; i++){
                tmp[i] = (Integer) ss.next();
            }
            sourceSet.clear();
            for (int i = 0; i < tmp.length - 1; i++) {
                sourceSet.add(fetchGCD(tmp[i], tmp[i + 1]));
            }
        }

        Integer gcd = null;
        Iterator it = sourceSet.iterator();
        if (it.hasNext()) {
            gcd = (Integer) it.next();
        }

        // 2. 计算最简比
        int[] result = new int[source.length];
        if (gcd != null && gcd > 0) {
            for (int i = 0; i < source.length; i++) {
                result[i] = source[i] / gcd;
            }
        }

        return result;
    }

    /**
     * 多个值的最小公倍数
     * @param source
     * @return
     */
    public static int fetchGMultipleByMulti(int[] source) {
        int[] copy = Arrays.copyOf(source, source.length);
        // 最多计算次数
        int maxCount = (int) Math.ceil((double) source.length / 2);
        int count = 0;
        while (count < maxCount) {
            for (int i = 0; i < copy.length; ){
                if (i + (int) Math.pow(2, count) < copy.length){
                    copy[i] = multiple(copy[i], copy[i + (int) Math.pow(2, count)]);
                }
                i += 2*(count + 1);
            }
            count++;
        }
        return copy[0];
    }

    /**
     * 两个值的最大公约数
     *
     * @param m
     * @param n
     * @return
     */
    public static int fetchGCD(int m, int n) {
        int result = 0;
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }

    /**
     * 两个数的最简整数比
     *
     * @param a
     * @param b
     */
    public static void simple(int a, int b) {
        int gcd = fetchGCD(a, b);
        System.out.print((a / gcd) + ":" + (b / gcd));
    }

    /**
     * 两个数的最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    public static int multiple(int a, int b) {
        int gcd = fetchGCD(a, b);
        return a * (b / gcd);
    }

    public static void main(String[] args) {
//        int[] source1 = new int[]{1, 3, 5, 10, 2};
//        int[] source2 = new int[]{125, 75, 25, 10, 55};
//        int[] source3 = new int[]{1200, 750, 2500, 1000, 5500};
//        int[] res1 = simpleByMulti(source1);
//        int[] res2 = simpleByMulti(source2);
//        int[] res3 = simpleByMulti(source3);
//        System.out.println("source1");
//        printArray(source1);
//        System.out.println();
//        System.out.println("res1");
//        printArray(res1);
//        System.out.println();
//
//        System.out.println("source2");
//        printArray(source2);
//        System.out.println();
//        System.out.println("res2");
//        printArray(res2);
//        System.out.println();
//
//        System.out.println("source3");
//        printArray(source3);
//        System.out.println();
//        System.out.println("res3");
//        printArray(res3);
//        System.out.println();

        int[] source1 = new int[]{1, 3, 5, 10, 2}; // 30
        int[] source2 = new int[]{4, 20, 1, 1, 2, 50, 1, 1}; // 100
        int[] source3 = new int[]{4, 5, 200, 10, 50}; // 200
        int[] source4 = new int[]{4, 1, 1, 1, 1, 1, 1, 10}; // 20
        int res1 = fetchGMultipleByMulti(source1);
        int res2 = fetchGMultipleByMulti(source2);
        int res3 = fetchGMultipleByMulti(source3);
        int res4 = fetchGMultipleByMulti(source4);
        System.out.println("source1");
        printArray(source1);
        System.out.println();
        System.out.println("res1: " + res1);

        System.out.println("source2");
        printArray(source2);
        System.out.println();
        System.out.println("res2: " + res2);

        System.out.println("source3");
        printArray(source3);
        System.out.println();
        System.out.println("res3: " + res3);

        System.out.println("source4");
        printArray(source4);
        System.out.println();
        System.out.println("res4: " + res4);
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
