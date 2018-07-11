
/**
 * 项目名：  steping
 * 文件名：  Begining.java
 * 模块说明：
 * 修改历史：
 * 2018-03-08 - Songyanyan - 创建。
 */

import java.io.*;
import java.util.Arrays;
import java.util.Set;

/**
 * 算法题汇总
 *
 * @author Songyanyan
 */
public class Begining {
  public static void main(String[] args) {
    // String str = "aaaabbaav";
    // String str1 = "bbc";
    // String[] dict = { "aaa", "abb", "aa" };
    // // boolean result = funcTrancate(str, dict);
    // Set<String> dict1 = new HashSet<String>();
    // dict1.add("a");
    // dict1.add("ab");
    // dict1.add("abc");
    // dict1.add("bc");
    // boolean result1 = funcAllTrancate(str1, dict1);
    // System.out.println(result1);
    // System.out.println(StringUtils.leftPad("foo", 5));
    // 获取到对应的ASCII码
    // char s = 'a';
    // System.out.println(Integer.valueOf(s));
    // System.out.println(Sort_Solution.rearrange("AC2BEW3"));
    // int a[] = { 3, 5, 2, 1, 6, 4, 4,4 };
    
    // int a[] = { 2, 1, 3, 2, 1 };
    // Solution.wiggleSort(a);
    // for (int i = 0; i < a.length; i++) {
    // System.out.println(a[i]);
    // }
    
    int[][] a = { { 1, 2, 3, 4 }, { 1, 2, 3, 4 }, { 1, 2, 3, 4 } };
    int[][] b = a.clone();
    
    System.out.println(a == b); // false
    
    b[2][2] = 100;
    System.out.println(a[2][2]);// 100
    
  }
  
  public Object deepClone(Object a) throws IOException, ClassNotFoundException {
    // 将对象写入流中
    ByteArrayOutputStream bao = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bao);
    oos.writeObject(a);
    // 将对象从流中取出
    ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bis);
    return ois.readObject();
  }
  
  // 有序的check
  private static boolean funcTrancate(String str, String[] dict) {
    String src = "";
    if (dict.length == 1 && dict[0].equals(str)) {
      return true;
    } else if (str.contains(dict[0])) {
      src = str.split(dict[0])[1];
      String[] newDict = new String[dict.length - 1];
      System.arraycopy(dict, 1, newDict, 0, newDict.length);
      return funcTrancate(src, newDict);
    } else {
      return false;
    }
  }
  
  // 无序check
  // 即str字符串 s[0,i) 能被拆分，则一定能找到一个j使得：s[0,j) 能被拆分 且 s[j,i) 在字典中
  private static boolean funcAllTrancate(String s, Set<String> wordDict) {
    int[] pos = new int[s.length() + 1];
    Arrays.fill(pos, -1);
    pos[0] = 0;
    for (int i = 0; i < s.length(); i++) {
      if (pos[i] != -1) {
        for (int j = i + 1; j <= s.length(); j++) {
          String sub = s.substring(i, j);
          if (wordDict.contains(sub)) {// 查看s[0,s.lenth()]中任一字符串s[i,j)是否在词典里面，在的话pos[j] = i
            pos[j] = i;
          }
        }
      }
    }
    return pos[s.length()] != -1;
  }
}

class StringUtils {
  /**
   * @param originalStr:
   *          the string we want to append to with spaces
   * @param size:
   *          the target length of the string
   * @return: A string
   */
  static public String leftPad(String originalStr, int size) {
    // Write your code here
    String newStr = "";
    if (originalStr.length() < size) {
      int n = size - originalStr.length();
      for (int i = 0; i < n; i++) {
        newStr = newStr + " ";
      }
    }
    return newStr.concat(originalStr);
  }
  
  /*
   * @param originalStr: the string we want to append to
   * 
   * @param size: the target length of the string
   * 
   * @param padChar: the character to pad to the left side of the string
   * 
   * @return: A string
   */
  static public String leftPad(String originalStr, int size, char padChar) {
    // write your code here
    String newStr = "";
    if (originalStr.length() < size) {
      int n = size - originalStr.length();
      for (int i = 0; i < n; i++) {
        newStr = newStr + padChar;
      }
    }
    return newStr.concat(originalStr);
  }
  
}

class Solution {
  /*
   * @param nums: A list of integers
   * 
   * @return: nothing
   */
  static public void wiggleSort(int[] nums) {
    // write your code here
    // 快速排序 先有序排数，排好后找到中位数
    // 奇数个：小的要比大的多1个 前n/2 nums[0..n/2] 后n/2 nums[n/2+1..n-1]
    //
    // 偶数个：前n/2 nums[0..n/2-1] 后n/2 nums[n/2..n-1]
    // 交换
    int n = nums.length;
    if (n < 2)
      return;
    if (n == 2) {
      if (nums[0] > nums[1]) {
        exch(nums, 0, 1);
      }
      return;
    }
    sort(nums, 0, n - 1);
    if (n % 2 == 1) {
      // 奇数
      int i = 1;
      int j = n - 1;
      while (i <= j) {
        exch(nums, j, i);
        i += 2;
        j -= 2;
      }
    } else {
      // 偶数
      for (int i = 1; i < n - 1; i += 2) {
        exch(nums, i, n - i);
        exch(nums, n - i, i + 1);
      }
      exch(nums, n - 1, n - 2);
    }
    
  }
  
  static public void sort(int[] nums, int l, int h) {
    if (l >= h)
      return;
    int baseIndex = sortProd(nums, l, h);
    sort(nums, l, baseIndex - 1);
    sort(nums, baseIndex + 1, h);
  }
  
  static public int sortProd(int[] nums, int l, int h) {
    int base = nums[l];
    int i = l, j = h + 1;
    while (true) {
      while (nums[++i] < base)
        if (i == h)
          break;
      while (nums[--j] > base)
        if (j == l)
          break;
      if (i >= j)
        break;
      exch(nums, i, j);
    }
    exch(nums, l, j);
    return j;
  }
  
  static public void exch(int[] nums, int n, int m) {
    int temp = nums[n];
    nums[n] = nums[m];
    nums[m] = temp;
  }
}