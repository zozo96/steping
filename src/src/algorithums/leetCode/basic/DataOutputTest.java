package algorithums.leetCode.basic;

import java.util.List;

public class DataOutputTest {
    public static void main(String args[]) {
//        // ThreeSum
//        int[] A = new int[]{0,0,1,1,-1,-1};
//        ThreeSum sum = new ThreeSum();
//        List<List<Integer>> lists = sum.threeSum(A);
//        System.out.println(lists.toString());

        // FourSum
//        int[] B = new int[]{-3,-2,-1,0,0,1,2,3};
//        FourSum fourSum = new FourSum();
//        List<List<Integer>> lists1 = fourSum.fourSum(B, 0);
//        System.out.println(lists1.toString());

        // 原地旋转图像
//        RotateImage image = new RotateImage();
//        int[][] i = new int[][]{{0,1,2,3},{5,6,7,8},{10,11,12,13},{15,16,17,18}};
//        image.rotate(i);
//        System.out.println(i);

        // 是否为子序列
//        Subquence subquence = new Subquence();
//        System.out.println(subquence.isSubsequence("twn",
//                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxtxxxxxxxxxxxxxxxxxxxxwxxxxxxxxxxxxxxxxxxxxxxxxxn"));

        // 鏈表表示整數進行加和    0
//        AddTwoLinkedListNumber addTwo = new AddTwoLinkedListNumber();
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(1);
//        l1.next.next = new ListNode(5);
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(5);
//        ListNode n = addTwo.addTwoNumbers(l1,l2);
//        System.out.println(n);

        // 最长不含重复字母的子串
        SubString str = new SubString();
        System.out.println(str.lengthOfLongestSubstring(""));
    }
}
