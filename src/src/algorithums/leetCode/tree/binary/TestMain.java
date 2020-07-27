/**
 * 项目名：  steping
 * 文件名：  TestMain.java
 * 模块说明：
 * 修改历史：
 * 2018-10-15 - Songyanyan - 创建。
 */
package algorithums.leetCode.tree.binary;

/**
 * 代码执行
 *
 * @author Songyanyan
 */
public class TestMain {
  public static void main(String[] args) {
    // 先序中序成树
    PreOrderInOrderTraversal p = new PreOrderInOrderTraversal();
    TreeNode treeNode = p.buildTree(new int[] { 3, 9, 20, 15, 7 }, new int[] { 9, 3, 15, 20, 7 });

    // 后序中序成树
    PostOrderInOrderTraversal postOrderInOrderTraversal = new PostOrderInOrderTraversal();
    TreeNode pTreeNode = postOrderInOrderTraversal.buildTree(new int[] { 9, 3, 15, 20, 7 },
      new int[] { 9, 15, 7, 20, 3 });
    String str = "1";
    System.out.println(pTreeNode);
    System.out.println((int) Math.floor(Math.sqrt(5)));
  }
}