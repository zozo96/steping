/**
 * 项目名：  steping
 * 文件名：  PreOrderInOrderTraversal.java
 * 模块说明：
 * 修改历史：
 * 2018-10-15 - Songyanyan - 创建。
 */
package algorithums.leetCode.tree.binary;

/**
 * 二叉树的先序后序生成树
 *
 * @author Songyanyan
 */
public class PreOrderInOrderTraversal {

  private int[] pre;
  private int[] in;

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0 || inorder.length == 0 || inorder.length != preorder.length) {
      return null;
    }
    this.pre = preorder;
    this.in = inorder;
    return doBuild(0, pre.length - 1, 0, in.length - 1);
  }

  public TreeNode doBuild(int s1, int e1, int s2, int e2) {
    if (s1 > e1) {
      return null;
    }
    TreeNode node = new TreeNode(pre[s1]);
    int find = -1;
    // 注意这里是 <= 包含 s 和 e 为同一index的情况
    for (int i = s2; i <= e2; i++) {
      if (in[i] == node.val) {
        find = i;
        break;
      }
    }

    if (find == -1) {
      return null;
    }

    // 前序个数：c = find - s2
    // 前序 s1 [s1 + 1 .. s1 + c] [s1 + c + 1 .. e1]
    // 中序 [s2 .. find - 1] find [find + 1 .. e2]

    int count = find - s2;
    node.left = doBuild(s1 + 1, s1 + count, s2, find - 1);
    node.right = doBuild(s1 + count + 1, e1, find + 1, e2);

    return node;
  }
}
