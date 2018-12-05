package leetCode.basic;

import java.util.List;

public class DataOutputTest {
    public static void main(String args[]) {
        int[] A = new int[]{0,0,1,1,-1,-1};
        ThreeSum sum = new ThreeSum();
        List<List<Integer>> lists = sum.threeSum(A);
        System.out.println(lists.toString());

        // 原地旋转图像
//        RotateImage image = new RotateImage();
//        int[][] i = new int[][]{{0,1,2,3},{5,6,7,8},{10,11,12,13},{15,16,17,18}};
//        image.rotate(i);
//        System.out.println(i);
    }
}
