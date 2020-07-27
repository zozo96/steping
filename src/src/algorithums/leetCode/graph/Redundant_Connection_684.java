/**
 * 项目名：  steping
 * 文件名：  Redundant_Connection_684.java
 * 模块说明：
 * 修改历史：
 * 2018-07-12 - Songyanyan - 创建。
 */
package algorithums.leetCode.graph;

import java.util.*;

/**
 * 图的冗余连接 即环 这道题目可以当作是寻找有向图的环
 *
 * @author Songyanyan
 */
public class Redundant_Connection_684 {

  public static void main(String[] args) {
    int[][] data0 = { { 3, 4 }, { 1, 2 }, { 2, 4 }, { 3, 5 }, { 2, 5 } };
    int[][] data1 = { { 1, 3 }, { 1, 4 }, { 1, 5 }, { 3, 5 }, { 2, 5 } };
    int[][] data2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } };
    int[][] data3 = { { 1, 3 }, { 3, 4 }, { 1, 5 }, { 3, 5 }, { 2, 3 } };
    int[][] data4 = { { 1, 4 }, { 3, 4 }, { 1, 3 }, { 1, 2 }, { 4, 5 } };

    // { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 3, 6 }, { 4, 7 }, { 4, 5 }, { 6, 7 }, { 5, 8 } }; 通过
    // { { 1, 3 }, { 3, 4 }, { 1, 5 }, { 3, 5 }, { 2, 3 } };通过
    // {{1,3},{3,4},{1,5},{3,5},{2,3}}
    // { { 1,4 },{ 1,3 },{ 1,5 },{ 3,5 },{ 2,5 } }
    int[] result = findRedundantConnection0(data4);
    System.out.println(result);

    // {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}}
    // int[}[] diData = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 1 }, { 1, 5 } };
    // int[][] diData1 = { { 1, 2 }, { 1, 3 }, { 2, 3 } };
    // findRedundantDirectedConnection(diData1);
  }

  public static int[] findRedundantConnection0(int[][] edges) {
    int[] result = new int[1];
    if (edges.length == 3) {
      return edges[2];
    } else {
      Map<Integer, List<Integer>> map = new HashMap<>();
      boolean[] marked = new boolean[edges.length + 1];
      int[] edgeTo = new int[edges.length + 1];
      for (int i = 0; i < edges.length; i++) {
        List<Integer> bag;
        if (map.get(edges[i][0]) == null) {
          bag = new ArrayList<>();
        } else {
          bag = map.get(edges[i][0]);
        }
        bag.add(edges[i][1]);

        map.put(edges[i][0], bag);
        if (map.get(edges[i][1]) == null) {
          map.put(edges[i][1], new ArrayList<>());
        }
      }
      for (int i = 0; i < edges.length; i++) {
        if (!marked[edges[i][0]])
          dfs0(map, edges[i][0], marked, edgeTo);
      }
      for (int i = 0; i < edges.length; i++) {
        if (edges[i][0] == edgeTo[0] || edges[i][1] == edgeTo[0]) {
          result = edges[i];
        }
      }
    }
    return result;
  }

  public static void dfs0(Map<Integer, List<Integer>> map, int v, boolean[] marked, int[] edgeTo) {
    marked[v] = true;
    List<Integer> list = map.get(v);
    for (int i : list) {
      if (!marked[i]) {
        edgeTo[i] = v;
        dfs0(map, i, marked, edgeTo);
      } else {
        if (edgeTo[0] == 0)
          edgeTo[0] = i;
      }
    }
  }

  public static int[] findRedundantDirectedConnection(int[][] edges) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      List<Integer> bag;
      if (map.get(edges[i][0]) == null) {
        bag = new ArrayList<>();
      } else {
        bag = map.get(edges[i][0]);
      }
      bag.add(edges[i][1]);

      map.put(edges[i][0], bag);
    }

    boolean[] marked = new boolean[edges.length + 1];
    boolean[] onStack = new boolean[edges.length + 1];
    int[] edgeTo = new int[edges.length + 1];
    Stack<Integer> cycle = new Stack<>();

    for (int i = 0; i < edges.length; i++) {
      if (!marked[edges[i][0]])
        dfs(map, edges[i][0], marked, onStack, edgeTo, cycle);
    }

    return new int[0];
  }

  public static void dfs(Map<Integer, List<Integer>> map, int v, boolean[] marked,
    boolean[] onStack, int[] edgeTo, Stack<Integer> cycle) {
    marked[v] = true;
    onStack[v] = true;
    List<Integer> list = map.get(v) == null ? new ArrayList<>() : map.get(v);
    for (int i : list) {
      if (cycle.size() != 0)
        return;
      else if (!marked[i]) {
        edgeTo[i] = v;
        dfs(map, i, marked, onStack, edgeTo, cycle);
      } else if (onStack[i]) {
        for (int t = v; t != i; t = edgeTo[t]) {
          cycle.push(t);
        }
        cycle.push(i);
        cycle.push(v);
      }
    }
    onStack[v] = false;
  }
}
