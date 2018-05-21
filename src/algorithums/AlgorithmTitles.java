
/**
 * 项目名：  steping
 * 文件名：  AlgorithmTitles.java
 * 模块说明：
 * 修改历史：
 * 2018-03-28 - Songyanyan - 创建。
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 算法题目
 *
 * @author Songyanyan
 */
public class AlgorithmTitles {
  public static void main(String[] args) {
    // int[] a = { 3, 5, 4, 9, 8, 10, 7, 5, 1, 6, 3 };
    // System.out.println(TheKBiggestNum_BinaryTree.theKthNum(a, 3));
    // for (int i = 0; i < a.length; i++) {
    // System.out.println(a[i]);
    // }
    // String[] nuts = { "ab", "bc", "dd", "gg" };
    // String[] bolts = { "AB", "GG", "DD", "BC" };
    // NBCompare compare = new NBCompare();
    // sortNutsAndBolts.sortNutsAndBolts(nuts, bolts, compare);
    
    // TreeNode node1 = new TreeNode(1);
    // TreeNode node2 = new TreeNode(1);
    // TreeNode node3 = new TreeNode(1);
    // TreeNode node4 = new TreeNode(1);
    // TreeNode node5 = new TreeNode(1);
    // TreeNode node6 = new TreeNode(1);
    // TreeNode node7 = new TreeNode(1);
    // node1.left = node2;
    // node1.right = node3;
    // node2.left = node4;
    // node2.right = node5;
    // node3.left = node6;
    // node3.right = node7;
    // int min = TreeNode.run(node1);
    // System.out.println(min);
    
    // ListNode node = new ListNode(9);
    // ListNode node1 = new ListNode(1);
    // ListNode node2 = new ListNode(9);
    // node1.next = node2;
    // ListNode link = node2;
    // for (int i=0;i<8;i++){
    // link.next = new ListNode(9);
    // link = link.next;
    // }
    // System.out.println(Solution_ListNodeAdd.addTwoNumbers(node, node1).val);
    
    // printJC0Num.printNum(100);
    
    // Nod51_1009.bigTry(101);
    
    // sumToK.printK();
    
    // getBigSumFromTower.get();
    
    // Dp1002_Vayne.test();
    
    // Nod1015.print();
    
    // Nod1015.flowerNum();
    
    // int[] a = Nod1016.getLen(6000344752545454545L);
    // for (int i : a) {
    // System.out.println(i);
    // }
    // System.out.println(a.length);
    
    // System.out.println(Math.sqrt(87604414021L));
    // Nod1080_Vayne.printNums(87604414021L);
    // 143711 258750
    
    Nod1082_Sim.printNums();
    // Nod1082.Nod1082();
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

class TreeNode {
  public int val;
  public TreeNode left, right;
  
  public TreeNode(int val) {
    this.val = val;
    this.left = this.right = null;
  }
  
  public static int minDepth(TreeNode root) {
    // write your code here
    if (root == null)
      return 0;
    if (root.left == null && root.right == null)
      return 1;
    if (root.left == null)
      return getDepth(root.right, 2);
    if (root.right == null)
      return getDepth(root.left, 2);
    return getDepth(root, 1);
  }
  
  public static int getDepth(TreeNode root, int depth) {
    depth++;
    int left = depth;
    int right = depth;
    if (root.left != null && root.right != null) {
      left = getDepth(root.left, depth);
      right = getDepth(root.right, depth);
    }
    return left <= right ? left : right;
  }
  
  public static int run(TreeNode root) {
    if (root == null)
      return 0;
    int left = run(root.left);
    int right = run(root.right);
    return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
  }
}

class ListNode {
  int val;
  ListNode next;
  
  ListNode(int x) {
    val = x;
  }
}

class Solution_ListNodeAdd {
  // 顺序排列 1->2->3->4 1234 1->2->3 123 加和 1234+123 = 1357 1->3->5->7
  static ListNode addTwoNumbersLine(ListNode l1, ListNode l2) {
    ListNode node1 = l1;
    ListNode node2 = l2;
    int num_l1 = 0;
    int num_l2 = 0;
    int bit_l1 = 1;
    int bit_l2 = 1;
    if (l1.next != null) {
      do {
        node1 = node1.next;
        bit_l1++;
      } while (node1.next != null);
    }
    if (l2.next != null) {
      do {
        bit_l2++;
        node2 = node2.next;
      } while (node2.next != null);
    }
    int time1 = 1;
    for (int m = 1; m < bit_l1; m++) {
      time1 *= 10;
    }
    int time2 = 1;
    for (int n = 1; n < bit_l2; n++) {
      time2 *= 10;
    }
    for (int i = bit_l1; i > 0; i--) {
      num_l1 += l1.val * time1;
      time1 /= 10;
      if (l1.next != null)
        l1 = l1.next;
    }
    for (int j = bit_l2; j > 0; j--) {
      num_l2 += l2.val * time2;
      time2 /= 10;
      if (l2.next != null)
        l2 = l2.next;
    }
    int num = num_l1 + num_l2;
    int temp_num = num;
    int bit = 0;
    do {
      bit++;
      temp_num /= 10;
    } while (temp_num >= 1);
    int[] a = new int[bit];
    int time = 0;
    for (int s = 0; s < bit; s++) {
      if (time == 0) {
        time = 1;
        continue;
      }
      time *= 10;
    }
    for (int t = 0; t < bit; t++) {
      a[t] = num / time;
      num -= a[t] * time;
      time /= 10;
    }
    ListNode header = new ListNode(0);
    for (int r = bit - 1; r >= 0; r--) {
      ListNode node = new ListNode(a[r]);
      node.next = header.next;
      header.next = node;
    }
    return header.next;
  }
  
  // 逆序排列 1->2->3->4 4324 1->2->3 321 加和 4324+321 = 4645
  static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode node1 = l1;
    ListNode node2 = l2;
    long num_l1 = 0;
    long num_l2 = 0;
    int bit_l1 = 1;
    int bit_l2 = 1;
    long time1 = 1;
    long time2 = 1;
    if (l1.next != null) {
      do {
        node1 = node1.next;
        bit_l1++;
      } while (node1.next != null);
    }
    if (l2.next != null) {
      do {
        bit_l2++;
        node2 = node2.next;
      } while (node2.next != null);
    }
    for (int i = bit_l1; i > 0; i--) {
      num_l1 += l1.val * time1;
      time1 *= 10;
      if (l1.next != null)
        l1 = l1.next;
    }
    for (int j = bit_l2; j > 0; j--) {
      num_l2 += l2.val * time2;
      time2 *= 10;
      if (l2.next != null)
        l2 = l2.next;
    }
    long num = num_l1 + num_l2;
    
    long temp_num = num;
    int bit = 0;
    do {
      bit++;
      temp_num /= 10;
    } while (temp_num >= 1);
    int[] a = new int[bit];
    long time = 0;
    for (int s = 0; s < bit; s++) {
      if (time == 0) {
        time = 1;
        continue;
      }
      time *= 10;
    }
    for (int t = 0; t < bit; t++) {
      a[t] = (int) (num / time);
      num -= a[t] * time;
      time /= 10;
    }
    ListNode header = new ListNode(0);
    for (int r = 0; r < bit; r++) {
      // 逆序输出结果
      ListNode node = new ListNode(a[r]);
      node.next = header.next;
      header.next = node;
    }
    return header.next;
  }
}

// 打印N的N次方的个位数值
class N_N {
  void getN_N() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int temp = getRemainder(n);
    int index = n % 4 - 1;
    int s = temp;
    if (index == -1) {
      index = 3;
    }
    int[] a = new int[4];
    for (int i = 0; i < 4; i++) {
      a[i] = getRemainder(temp);
      temp *= s;
    }
    System.out.println(a[index]);
  }
  
  static int getRemainder(int temp) {
    int time = 10;
    temp %= time;
    return temp;
  }
}

// 打印阶乘0的数量
class printJC0Num {
  static void printNum(int n) {
    if (n == 0) {
      System.out.println(1);
    }
    int num = n / 5;
    int temp = num;
    while (temp / 5 > 0) {
      num += temp / 5;
      temp /= 5;
    }
    System.out.println(num);
  }
}

// 打印出1-N中所有的1数量
class Nod51_1009 {
  public static void bigTry(int n) {
    int index;
    int high;
    int low;
    int mut = 1;
    int num = 0;
    
    while (n / mut != 0) {
      index = (n / mut) % 10; // 个位、十位、百位。。
      high = n / (mut * 10);// 当前位高位
      low = n - (n / mut) * mut; // 当前位低位
      if (index > 1)
        num = num + (high + 1) * mut;
      else if (index == 0)
        num = num + high * mut;
      else if (index == 1)
        num = num + high * mut + low + 1;
      mut *= 10;
    }
    System.out.println(num);
  }
  // public static void printNum1(int n) {
  // int num = 0;
  // if (n < 100) {
  // num = getInner100Num(n);
  // } else {
  // int temp = n;
  // int mut = 100;
  // while (temp / mut > 1) {
  // num += mut;
  // mut *= 10;
  // }
  //
  // int remainder = getInner100Num(temp);// 100内的余数
  // num += remainder;
  // }
  // System.out.println(num);
  // }
  //
  // static int getInner100(int n) {
  // if (n < 10)
  // return 1;
  // else if (n == 10)
  // return 2;
  // else if (n > 10 && n < 20) {
  // return n - 7;
  // } else if (n >= 20 && n < 100) {
  // if (n % 10 >= 1)
  // return 11 + n / 10;
  // else
  // return 10 + n / 10;
  // } else
  // return 21;
  // }
}

// 打印出 数组中和等于K的数对 1001
class sumToK {
  public static void printK() {
    Scanner sc = new Scanner(System.in);
    // String k_n = sc.nextLine();
    // int K = Integer.parseInt(k_n.split(" ")[0]); // K值
    // int n = Integer.parseInt(k_n.split(" ")[1]); // a数组的元素个数
    int K = sc.nextInt(); // 两种方式都是ok的
    int n = sc.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
    }
    a = quickSort(a, 0, n - 1);
    // Arrays.sort(a);
    boolean flag = false; // 若为false 输出 No Solution
    for (int i = 0; i < n; i++) {
      if (a[i] >= K / 2) {
        break;
      } else if (check(a, K - a[i], i, n - 1) == 1) {
        System.out.println(a[i] + " " + (K - a[i]));
        flag = true;
      }
    }
    if (flag == false) {
      System.out.println("No Solution");
    }
  }
  
  public static int check(int a[], int value, int l, int h) {
    // 二分
    if (l > h)
      return -1;
    int mid = (l + h) / 2;
    if (a[mid] < value)
      return check(a, value, mid + 1, h);
    else if (a[mid] > value)
      return check(a, value, l, mid - 1);
    else
      return 1;
  }
  
  // 对a排序
  public static int[] quickSort(int[] a, int l, int h) {
    if (l >= h)
      return a;
    int j = partition(a, l, h);
    quickSort(a, l, j - 1);
    quickSort(a, j + 1, h);
    return a;
  }
  
  public static int partition(int[] a, int l, int h) {
    int base = a[l];
    int i = l;
    int j = h + 1;
    while (true) {
      while (a[++i] < base) {
        if (i == h)
          break;
      }
      while (a[--j] > base) {
        if (j == l)
          break;
      }
      if (i >= j)
        break;
      exch(a, i, j);// 交换大于/小于base的值
    }
    exch(a, l, j); // 将base = a[j] 即切分值一直留在a[j]中
    return j;
  }
  
  public static void exch(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}

// 1002 数塔取数问题
class getBigSumFromTower {
  public static void get() {
    try {
      BufferedReader bur = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(bur.readLine());
      // Scanner sc = new Scanner(System.in);
      // int N = sc.nextInt(); // 塔高
      int[][] a = new int[N][N];
      for (int i = 0; i < N; i++) {
        StringTokenizer stk = new StringTokenizer(bur.readLine());
        int j = 0;
        while (stk.hasMoreElements()) {
          a[i][j] = Integer.parseInt(stk.nextToken());
          j++;
        }
      }
      // for (int i = 0; i < N; i++) {
      // for (int j = 0; j <= i; j++) {
      // a[i][j] = sc.nextInt();
      // }
      // }
      // dp 下一层+上一层较大的数
      for (int i = 1; i < N; i++) {
        for (int j = 0; j <= i; j++) {
          if (j == 0)
            a[i][j] += a[i - 1][j];
          else if (i == j)
            a[i][j] += a[i - 1][j - 1];
          else
            a[i][j] += a[i - 1][j] >= a[i - 1][j - 1] ? a[i - 1][j] : a[i - 1][j - 1];
        }
      }
      
      int result = 0;
      int m = 0;
      while (m < N) {
        result = a[N - 1][m] > result ? a[N - 1][m] : result;
        m++;
      }
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/**
 * 51nod, 1002 数塔取数问题 基准时间限制：1 秒 空间限制：131072 KB 分值: 5 难度：1级算法题
 *
 * http://www.51nod.com/onlineJudge/questionCode.html#!problemId=1002
 *
 * @author 破晓
 *
 */
class Dp1002 {
  static int[] tower = new int[125255];
  static int len = 0;
  
  public static void main(String[] args) {
    BufferedReader bur = new BufferedReader(new InputStreamReader(System.in));
    try {
      int N = Integer.parseInt(bur.readLine());
      for (int i = 0; i < N; i++) {
        StringTokenizer stk = new StringTokenizer(bur.readLine());
        while (stk.hasMoreElements())
          tower[len++] = Integer.parseInt(stk.nextToken());
      }
      
      int[] dp = new int[N + 1];
      for (int i = N; i > 0; i--)
        for (int j = 0; j < i; j++)
          dp[j] = Math.max(dp[j], dp[j + 1]) + tower[--len];
        
      System.out.println(dp[0]);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class Dp1002_Vayne {
  public static void test() {
    BufferedReader bur = new BufferedReader(new InputStreamReader(System.in));
    try {
      int n = Integer.parseInt(bur.readLine());
      int[][] dp = new int[n][n];
      dp[0][0] = Integer.parseInt(bur.readLine());
      int max = 0;
      for (int i = 1; i < n; i++) { // 层数
        StringTokenizer stk = new StringTokenizer(bur.readLine());
        for (int j = 0; j <= i; j++) { // 每一层读取的个数
          int tmp = Integer.parseInt(stk.nextToken());
          if (j == 0) {
            dp[i][j] = dp[i - 1][j] + tmp;
          } else {
            dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + tmp;
          }
          max = Math.max(max, dp[i][j]);
        }
      }
      System.out.println(max);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class Nod1015 {
  static void flowerNum() {
    Scanner sc = new Scanner(System.in);
    int M = sc.nextInt();
    int[] nums = new int[] { 153, 370, 371, 407, 1634 };
    
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > M) {
        System.out.println(nums[i]);
        break;
      }
    }
  }
  
  static void print() {
    // 打印出所有的水仙花数
    for (int i = 100000; i < 1000000; i++) {
      if (i == Math.pow(i % 10, 6) + Math.pow(i % 100 / 10, 6) + Math.pow(i % 1000 / 100, 6) + Math
        .pow(i % 10000 / 1000, 6) + Math.pow(i % 100000 / 10000, 6) + Math.pow(i / 100000, 6))
        System.out.println(i);
    }
  }
  
}

class Nod1016 {
  static void flowerNum() {
    Scanner sc = new Scanner(System.in);
    long M = sc.nextLong();
    int[] a = getLen(M);// 0-len:个十百千万....位
    int len = a.length;
    long num = 0;
    for (int i : a) {// M是否为水仙花数
      num += Math.pow(i, len);
    }
    boolean flag = false;
    if (num == M) {
      System.out.println(M);
      flag = true;
    } else { // 找到大于M的第一个水仙花数：首先找长度为len的 len+1 len+2..
      for (int i = len; i < 64; i++) {
        
      }
    }
  }
  
  static int[] getLen(long n) {
    long temp = n;
    int count = 0;
    while (n > 0) {
      count++;
      n = n / 10;
    }
    int[] a = new int[count];
    int index = 0;
    while (temp > 0) {
      a[index] = (int) temp % 10;
      index++;
      temp /= 10;
    }
    return a;
  }
}

// 1080 两个数的平方和
class Nod1080 {
  public static void printNums(int N) {
    int n = (int) Math.sqrt(N);
    for (int i = 1; i <= n; i++) {
      for (int j = i; j <= n; j++) {
        if (i * i + j * j == N) {
          System.out.println(i + " " + j);
          break;
        }
      }
    }
  }
}

class Nod1080_Vayne {
  public static void printNums(long N) {
    int M = (int) Math.sqrt(N);
    for (long i = 0; i <= M; i++) {
      int num = (int) Math.sqrt(N - i * i);
      if (num == Math.sqrt(N - i * i) && i <= num) {
        System.out.println(i + " " + num);
      }
    }
  }
}

// 1082 小于等于N且与7无关的数的平方和
class Nod1082_Sim {
  public static void printNums() {
    long[] t = table();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    try {
      int N = Integer.parseInt(reader.readLine());
      while (N-- != 0) {
        writer.write((t[Integer.parseInt(reader.readLine())])+"\n");
      }
      writer.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static boolean check(int N) {
    if (N % 7 == 0)
      return false;
    int tmp = N;
    while (tmp > 1) {
      if (tmp % 10 == 7) {
        return false;
      }
      tmp /= 10;
    }
    return true;
  }
  
  public static long[] table() {
    long[] t = new long[1000002];
    t[1] = 1;
    for (int i = 2; i <= 1000000; i++) {
      if (check(i)) {
        t[i] = t[i - 1] + (long) i * i;
      } else {
        t[i] = t[i - 1];
      }
    }
    return t;
  }
}

class Nod1082_Complex {
  public static void Nod1082() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    try {
      int N = Integer.parseInt(reader.readLine());
      int[] a = new int[N];
      int i = 0;
      while (i < N) {
        a[i] = Integer.parseInt(reader.readLine());
        i++;
      }
      Map<Integer, Long> map = new HashMap<>(); // x,n x对应的与7无关的平方和
      for (int m = 0; m < N; m++) {
        long num = getSum(a[m], map);
        writer.write(num+"\n");
        map.put(a[m], num);
      }
      writer.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static boolean check(int N) {
    if (N % 7 == 0)
      return false;
    int tmp = N;
    while (tmp > 1) {
      if (tmp % 10 == 7) {
        return false;
      }
      tmp /= 10;
    }
    return true;
  }
  
  public static long getFirstSum(int N) {
    long sum = 0;
    for (int i = 0; i <= N; i++) {
      if (check(i))
        sum += (long) i * i;
    }
    return sum;
  }
  
  public static long getSum(int N, Map<Integer, Long> map) {
    if (map.size() == 0) {
      return getFirstSum(N);
    }
    Set<Integer> keySet = map.keySet();
    boolean lessExistFlag = false; // 是否存在较小值
    int tmp = 0;
    for (int i : keySet) {
      if (i == N) // 相等直接返回该key对应的value
        return map.get(i);
      if (N - i > 0)
        tmp = N - i;
      if (N - i > 0 && N - i <= tmp) {
        // 获取到最接近tmp的sum键
        tmp = N - i;
        lessExistFlag = true;
      }
    }
    long curSum;
    if (!lessExistFlag) {
      curSum = getFirstSum(N);
    } else {
      curSum = map.get(N - tmp);
      for (int i = N - tmp + 1; i <= N; i++) {
        if (check(i))
          curSum += (long) i * i;
      }
    }
    return curSum;
  }
}