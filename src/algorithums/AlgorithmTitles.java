
/**
 * 项目名：  steping
 * 文件名：  AlgorithmTitles.java
 * 模块说明：
 * 修改历史：
 * 2018-03-28 - Songyanyan - 创建。
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 算法题目
 *
 * @author Songyanyan
 */
public class AlgorithmTitles {
  public static void main(String[] args) {
    int[] a = { 3, 5, 4, 9, 8, 10, 7, 5, 1, 6, 3 };
    // System.out.println(TheKBiggestNum_BinaryTree.theKthNum(a, 3));
    // for (int i = 0; i < a.length; i++) {
    // System.out.println(a[i]);
    // }
    String[] nuts = { "ab", "bc", "dd", "gg" };
    String[] bolts = { "AB", "GG", "DD", "BC" };
    NBCompare compare = new NBCompare();
    sortNutsAndBolts.sortNutsAndBolts(nuts, bolts, compare);
  }
}

/* 爬梯子 */
class climbStairs {
  public int climbStairs(int n) {
    // write your code here
    // 1.递归 当n<=3时 n=44时 RuntimeError
    // if (n <= 3) {
    // return n;
    // } else {
    // return climbStairs(n - 1) + climbStairs(n - 2);
    // }
    // 2.动态规划
    int[] a = new int[n];
    if (n < 3) {
      return n;
    } else {
      a[0] = 1;
      a[1] = 2;
      int result = 0;
      for (int i = 2; i < n; i++) {
        a[i] = a[i - 1] + a[i - 2];
        result = a[i];
      }
      return result;
    }
  }
}

/**
 * 
 * 字母数字的重排序 给一包含大写字母和整数(从 0 到 9)的字符串, 试写一函数返回有序的字母以及数字和. 样例 给出 str = AC2BEW3, 返回 ABCEW5 字母按字母表的顺序排列,
 * 接着是整数的和(2 和 3).
 *
 */
class NumWordsRearrange {
  static public String rearrange(String str) {
    // Write your code here
    if (str == null)
      return null;
    if (str.equals("")) {
      return str;
    }
    int com = 0;
    int sum = 0;
    List a = new ArrayList();
    
    for (int i = 0; i < str.length(); i++) {
      com = Integer.valueOf(str.charAt(i));
      if (com <= 57) {
        sum += Integer.parseInt(String.valueOf(str.charAt(i)));
      } else {
        a = sortList(a, com, str.charAt(i));
      }
    }
    
    String result = "";
    for (int j = 0; j < a.size(); j++) {
      result = result + a.get(j);
    }
    return result + sum;
  }
  
  static public List sortList(List a, int num, char c) {
    int sb = 1;// 哨兵 1表示在末尾,0则表示在中间
    char x;
    if (a.size() == 0) {
      a.add(c);
      return a;
    }
    
    for (int i = 0; i < a.size(); i++) {
      if (Integer.valueOf(c) < Integer.valueOf((char) a.get(i))) {
        a.add('t');
        for (int j = a.size() - 1; j > i; j--) {
          // i和i位置之后的全部后移一个元素
          x = (char) a.get(j - 1);
          a.set(j, x);
        }
        a.set(i, c);
        sb = 0;
        break;
      }
    }
    if (sb == 1) {
      a.add(c);
    }
    return a;
  }
}

// 第K大快排解决方案
class TheKBiggestNum {
  
  static public int kthLargestElement(int k, int[] nums) {
    // write your code here
    // 用快排 j=n-k 就是第k大元素
    if (nums.length < 0)
      return 0;
    if (nums.length < 1)
      return nums[0];
    quickSort(nums, 0, nums.length - 1, k);
    return nums[k - 1];
  }
  
  static public void quickSort(int[] nums, int l, int h, int k) {
    if (l >= h)
      return;
    int j = sort(nums, l, h);
    if (j > k - 1) {
      quickSort(nums, l, j - 1, k);
    } else if (j < k - 1) {
      quickSort(nums, j + 1, h, k);
    } else {
      return;
    }
  }
  
  static public int sort(int[] nums, int l, int h) {
    int i = l;
    int j = h + 1;
    int base = nums[l];
    while (true) {
      while (nums[++i] > base)
        if (i == h)
          break;
      while (nums[--j] < base)
        if (j == l)
          break;
      if (i >= j)
        break;
      exch(nums, i, j);
    }
    exch(nums, l, j);
    return j;
  }
  
  static public void exch(int[] nums, int m, int n) {
    int temp = nums[m];
    nums[m] = nums[n];
    nums[n] = temp;
  }
}

// 第K大 大顶堆排 解决方案（二叉树）
class TheKBiggestNum_BinaryTree {
  static public int theKthNum(int[] nums, int k) {
    for (int i = 0; i < nums.length - 1; i++) {
      bigHeap(nums, nums.length - 1 - i);
      exch(nums, 0, nums.length - 1 - i);
      if (i == k)
        break;
    }
    return nums[nums.length - k];
  }
  
  static public void bigHeap(int[] nums, int n) {
    for (int i = n; i >= 1; i--) {
      if (nums[(i - 1) / 2] < nums[i]) {
        exch(nums, i, (i - 1) / 2);
      }
    }
  }
  
  static public void exch(int[] nums, int m, int n) {
    int temp = nums[m];
    nums[m] = nums[n];
    nums[n] = temp;
  }
}

/**
 * public class NBCompare { public int cmp(String a, String b); } You can use compare.cmp(a, b) to
 * compare nuts "a" and bolts "b", if "a" is bigger than "b", it will return 1, else if they are
 * equal, it will return 0, else if "a" is smaller than "b", it will return -1. When "a" is not a
 * nut or "b" is not a bolt, it will return 2, which is not valid.
 */
class NBCompare {
  public int cmp(String a, String b) {
    if (a.length() > b.length())
      return 1;
    else if (a.length() < b.length())
      return -1;
    else
      return 0;
  }
}

class sortNutsAndBolts {
  /**
   * @param nuts:
   *          an array of integers
   * @param bolts:
   *          an array of integers
   * @param compare:
   *          a instance of Comparator
   * @return: nothing
   */
  static void sortNutsAndBolts(String[] nuts, String[] bolts, NBCompare compare) {
    // write your code here
    sortProd(nuts, bolts, 0, bolts.length - 1, compare);
  }
  
  static void sortProd(String[] nuts, String[] bolts, int l, int h, NBCompare compare) {
    if (l >= h)
      return;
    for (int i = 0; i < nuts.length; i++) {
      int j = sortBolts(nuts[i], bolts, l, h, compare);
      exch(nuts, i, j);
      sortProd(nuts, bolts, l, j - 1, compare);
      sortProd(nuts, bolts, j + 1, h, compare);
      
    }
  }
  
  // 对Bolts排序
  static int sortBolts(String nut, String[] bolts, int l, int h, NBCompare compare) {
    int i = l;
    int j = h + 1;
    while (true) {
      while (compare.cmp(nut, bolts[i++]) == 1)
        if (i == h)
          break;
      while (compare.cmp(nut, bolts[--j]) == -1)
        if (j == l)
          break;
      if (i >= j)
        break;
      exch(bolts, i, j);
    }
    exch(bolts, l, j);
    return j;
  }
  
  static void exch(String[] a, int i, int j) {
    String temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
};