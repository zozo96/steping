
/**
 * 项目名：  steping
 * 文件名：  Sorts.java
 * 模块说明：
 * 修改历史：
 * 2018-03-25 - Songyanyan - 创建。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 几种排序的实现
 *
 * @author Songyanyan
 */
public class Sorts {
  public static void main(String[] args) {
    // 静态初始化
    // int[] a = new int[] { 5, 1, 3, 2, 9, 11, 15, 6, 3, 1, 4 };
    // int[] d = new int[] { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
    // 静态初始化简化
    // int b[] = { 1, 2 };
    // 动态初始化
    // int[] c = new int[4];
    // int[] result = BubbleSort.sort(a);
    // int[] result = DirectInsertSort.sortResult(a);
    // int[] result = ShellSort.sort(a);
    // int[] result = SimpleSelectionSort.sort(a);
    // int[] result = QuickSort.sort(a, 0, 10);
    // int[] result = HeapSort.sort(a);
    // int[] result = MergeSort.sort(a);
    
    // int[] result = QuickSort2.sort(d);
    // for (int i = 0; i < result.length; i++) {
    // System.out.println(result[i]);
    // }
    
    BucketSolution solution = new BucketSolution();
    int[] nums = { 1, 1, 1, 2, 2, 3 };
    int k = 2;
    System.out.println(solution.topKFrequent(nums, k));
    
  }
}

// 交换：冒泡排序：相邻两个比较，第一趟最大沉底 一般不用。
// 时间复杂度 最好O(n) 最坏O(n2)
// 空间复杂度 O(n)
// 平均复杂度 O(n2)
class BubbleSort {
  static public int[] sort(int[] a) {
    int tag;
    for (int j = 0; j < a.length; j++) {
      for (int i = 1; i < a.length - j; i++) {
        if (a[i] < a[i - 1]) {
          tag = a[i - 1];
          a[i - 1] = a[i];
          a[i] = tag;
        }
      }
    }
    return a;
  }
}

// 交换：快速排序 要求时间最快时。
// 选择基数为p，小于p的数放在左边，大于p的数放在右边。
// a[j]已排定 a[l..j-1]都不大于a[j] a[j+1..h]都不小于a[j]。
class QuickSort {
  static public int[] sort(int[] a, int l, int h) {
    if (l >= h)
      return a;
    int j = partition(a, l, h);
    sort(a, l, j - 1);
    sort(a, j + 1, h);
    return a;
  }
  
  static private int partition(int[] a, int l, int h) {
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
      SortUtils.exch(a, i, j);// 交换大于/小于base的值
    }
    SortUtils.exch(a, l, j); // 将base = a[j] 即切分值一直留在a[j]中
    return j;
  }
}

/**
 * 快排实现2： 设定比值哨兵、存储哨兵 存储哨兵index = 比值哨兵index + 1 对未排序部分：forEach(比值哨兵index + 1,最右指数) i <
 * 比对哨兵、exch(i,存储哨兵)、存储哨兵index++
 */
class QuickSort2 {
  static int[] sort(int[] a) {
    doSort(a, 0, a.length - 1);
    return a;
  }
  
  static void doSort(int[] a, int l, int r) {
    if (r - l < 1)
      return;
    int base = a[l];
    int index = l + 1;
    for (int i = l + 1; i <= r; i++) {
      if (a[i] < base) {
        SortUtils.exch(a, i, index);
        index++;
      }
    }
    SortUtils.exch(a, l, index - 1);
    
    doSort(a, l, index - 2);
    doSort(a, index, r);
  }
}

// 快速排序优化
// 1.定义M：5~15，小分组时采取插入排序
// 2.大量重复数据时，可以采用熵最优：根据重复数据（大于、小于、等于基准数据）分组

// 选择：简单选择排序：常用于取序列中最大最小的几个数时。
// 如果每次比较都交换，那么就是交换排序；如果每次比较完一个循环再交换，就是简单选择排序。
// 遍历整个序列，将最小的数放在最前面。
// 遍历剩下的序列，将最小的数放在最前面。
class SimpleSelectionSort {
  static public int[] sort(int[] a) {
    for (int i = 0; i < a.length; i++) {
      int temp = a[i];
      int index = i;
      for (int j = i; j < a.length; j++) {
        if (a[j] < temp) {
          temp = a[j];
          index = j;
        }
      }
      if (index != i) {
        a[index] = a[i];
        a[i] = temp;
      }
    }
    return a;
  }
}

// 归并：归并排序 速度仅次于快速排序，为稳定排序算法
// 一般用于对总体无序，但是各子项相对有序的数列
// 指的是将两个顺序序列合并成一个顺序序列的方法:一个无序序列分成两部分（）每部分继续递归），合并
class MergeSort {
  static public int[] sort(int[] a) {
    doMerge(a, 0, a.length - 1);
    return a;
  }
  
  static private void doMerge(int[] a, int s1, int end) {
    if (s1 < end) {
      int mid = (s1 + end) / 2;
      doMerge(a, s1, mid);
      doMerge(a, mid + 1, end);
      mergeArray(a, s1, mid, end);
    }
  }
  
  /**
   * 
   * @param a
   *          原序列
   * @param s1
   *          序列1起始索引
   * @param s2
   *          序列2起始索引
   * @param end
   *          序列2结束索引
   */
  static private void mergeArray(int[] a, int s1, int s2, int end) {
    int[] temp = new int[end - s1 + 1];
    int s = s1, mid = s2;
    int n = s2 + 1;
    int k = 0;
    // 合并a[s .. m] a[n..e] 至temp中
    while (s <= mid && n <= end) {
      if (a[s] < a[n])
        temp[k++] = a[s++];
      else
        temp[k++] = a[n++];
    }
    while (s <= mid) {
      temp[k++] = a[s++];
    }
    while (n <= end)
      temp[k++] = a[n++];
    
    for (int i = 0; i < k; i++) {
      a[s1 + i] = temp[i];// 注意是起始位置哦
    }
  }
}

// 选择：堆排序：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
// 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
class HeapSort {
  static public int[] sort(int[] a) {
    for (int i = 0; i < a.length - 1; i++) {// 执行n-1次大顶堆排序
      bigHeap(a, a.length - 1 - i);
      a = SortUtils.exch(a, 0, a.length - 1 - i);
    }
    return a;
  }
  
  static void bigHeap(int[] a, int n) {// 建造大顶堆
    for (int i = n; (i - 1) >= 0; i--) {
      if (a[(i - 1) / 2] < a[i]) {
        SortUtils.exch(a, i, (i - 1) / 2);
      }
    }
  }
}

// 插入：直接插入排序,少量数据可以，中等数量集可以采用希尔排序
class DirectInsertSort {
  static public int[] sortResult(int[] a) {
    if (a.length <= 1) {
      return a;
    }
    if (a[0] > a[1]) {
      int temp = a[0];
      a[0] = a[1];
      a[1] = temp;
    }
    for (int i = 2; i < a.length; i++) {
      for (int j = i; j > 0; j--) {
        if (a[j] < a[j - 1]) {
          int t = a[j - 1];
          a[j - 1] = a[j];
          a[j] = t;
        }
      }
    }
    return a;
  }
}

// 插入：希尔排序 缩小增量排序，大量数量集可以采用快速排序
// 1.确定增量序列 2.根据增量形成的子数组排序
class ShellSort {
  static public int[] sort(int[] a) {
    if (a.length <= 1) {
      return a;
    }
    int n = a.length;
    int h = 1;
    while (h < n)
      h = h * 3 + 1;
    while (h >= 1) {
      // h = ... 40,13,4,1 分别根据增量进行排序，最后的1增量排序，为最后一次调整
      for (int i = h; i < n; i++) {
        // n-h+1个分组进行排序
        for (int j = i; j >= h && SortUtils.less(a[j], a[j - h]); j -= h) {
          // 对j, j-2h, j-3h ... 排序
          SortUtils.exch(a, j, j - h);
        }
      }
      h = h / 3;
    }
    return a;
  }
  
}

class BucketSolution {
  private HashMap<Integer, Integer> map = new HashMap<>();
  
  public List<Integer> topKFrequent(int[] nums, int k) {
    // 桶排 -- HashMap 统计
    int length = nums.length;
    int i = 0;
    while (i < length) {
      if (!map.containsKey(nums[i])) {
        map.put(nums[i], 1);
      } else {
        map.put(nums[i], map.get(nums[i]) + 1);
      }
      i++;
    }
    
    List<Integer> rs = new ArrayList<>();
    // 方式二 流 JDK 8
    rs = map.entrySet().stream().sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
      .map(o -> o.getKey()).collect(Collectors.toList());
    
    // 方式一 JDK 7
    // List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
    // Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
    // @Override
    // public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
    // // 降序排序
    // return o2.getValue().compareTo(o1.getValue());
    // }
    // });
    //
    // List result1 = list.subList(0, k);
    // Iterator it = result1.iterator();
    // Map.Entry<Integer, Integer> entry;
    // while (it.hasNext()) {
    // entry = (Map.Entry<Integer, Integer>) it.next();
    // rs.add(entry.getKey());
    // }
    
    return rs;
  }
}

class SortUtils {
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