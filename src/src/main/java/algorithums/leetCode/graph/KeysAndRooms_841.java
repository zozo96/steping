/**
 * 项目名：  steping
 * 文件名：  KeysAndRooms_841.java
 * 模块说明：
 * 修改历史：
 * 2018-07-10 - Songyanyan - 创建。
 */
package algorithums.leetCode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 钥匙和房间 深度优先遍历 DFS
 *
 * 升级：找到走遍所有房间的最短路径 广度优先遍历BFS
 *
 * @author Songyanyan
 */
public class KeysAndRooms_841 {
  public static void main(String[] args) {
    List<List<Integer>> rooms = new ArrayList<>();
    // 测试用例 [[1],[2],[3],[]]
    // List<Integer> key0 = new ArrayList<>();
    // List<Integer> key1 = new ArrayList<>();
    // List<Integer> key2 = new ArrayList<>();
    // List<Integer> key3 = new ArrayList<>();
    // key0.add(1);
    // key1.add(2);
    // key2.add(3);
    //
    // rooms.add(key0);
    // rooms.add(key1);
    // rooms.add(key2);
    // rooms.add(key3);

    // 测试用例[[1,5,6],[2,4],[5,0,3],[1,2],[5,6,2,1],[],[]]
    // List<Integer> key0 = new ArrayList<>();
    // List<Integer> key1 = new ArrayList<>();
    // List<Integer> key2 = new ArrayList<>();
    // List<Integer> key3 = new ArrayList<>();
    // List<Integer> key4 = new ArrayList<>();
    // List<Integer> key5 = new ArrayList<>();
    // List<Integer> key6 = new ArrayList<>();
    // key0.add(1);
    // key0.add(5);
    // key0.add(6);
    // key1.add(2);
    // key1.add(4);
    // key2.add(5);
    // key2.add(0);
    // key2.add(3);
    // key3.add(1);
    // key3.add(2);
    // key4.add(5);
    // key4.add(6);
    // key4.add(2);
    // key4.add(1);
    //
    // rooms.add(key0);
    // rooms.add(key1);
    // rooms.add(key2);
    // rooms.add(key3);
    // rooms.add(key4);
    // rooms.add(key5);
    // rooms.add(key6);

    // 测试用例 1个
    // List<Integer> key0 = new ArrayList<>();
    // key0.add(0);
    // rooms.add(key0);

    // System.out.println(Solution.canVisitAllRooms(rooms));
    System.out.println(ShortestSolution.canVisitAllRooms(rooms));
  }
}

// 深度优先遍历
class Solution {
  public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
    boolean[] marked = new boolean[rooms.size()];
    dfs(rooms, 0, marked);
    for (int i = 0; i < rooms.size(); i++) {
      if (!marked[i])
        return false;
    }
    return true;
  }

  public static void dfs(List<List<Integer>> rooms, int room, boolean[] marked) {
    marked[room] = true;
    List<Integer> keys = rooms.get(room);
    for (int key : keys) {
      if (!marked[key])
        dfs(rooms, key, marked);
    }
  }
}

// 广度优先遍历BFS
class ShortestSolution {
  public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
    boolean[] marked = new boolean[rooms.size()];
    int[] path = new int[rooms.size()];// 安排path 即可获得最短路径
    bfs(rooms, marked, path);
    for (int i = 0; i < rooms.size(); i++) {
      if (!marked[i])
        return false;
    }
    for (int i = 0; i < rooms.size(); i++) {
      System.out.println(path[i]);
    }
    return true;
  }

  public static void bfs(List<List<Integer>> rooms, boolean[] marked, int[] path) {
    marked[0] = true;
    Queue queue = new ArrayDeque<Integer>();
    queue.add(0);
    int count = 0;
    while (!queue.isEmpty()) {
      int head = (int) queue.peek();
      path[count] = (int) queue.poll();
      List<Integer> keys = rooms.get(head);
      for (int key : keys) {
        if (!marked[key] && !queue.contains(key)) {
          marked[key] = true;
          queue.add(key);
        }
      }
      count++;
    }
  }
}