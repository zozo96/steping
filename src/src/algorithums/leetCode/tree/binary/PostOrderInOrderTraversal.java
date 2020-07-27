/**
 * 项目名：  steping
 * 文件名：  PostOrderInOrderTraversal.java
 * 模块说明：
 * 修改历史：
 * 2018-10-15 - Songyanyan - 创建。
 */
package algorithums.leetCode.tree.binary;

/**
 * 后序中序遍历
 *
 * @author Songyanyan
 */
public class PostOrderInOrderTraversal {

  private int[] in;
  private int[] post;

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if (inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) {
      return null;
    }
    this.in = inorder;
    this.post = postorder;
    return doBuild(0, in.length - 1, 0, post.length - 1);
  }

  public TreeNode doBuild(int s1, int e1, int s2, int e2) {
    if (s1 > e1) {
      return null;
    }
    TreeNode node = new TreeNode(post[e2]);
    int find = -1;
    for (int i = s1; i <= e1; i++) {
      if (in[i] == node.val) {
        find = i;
        break;
      }
    }
    if (find == -1) {
      return null;
    }
    // countR = e1 - find; 右子树元素个数
    // [s1, find - 1][find][find + 1, e1]
    // [s2, e2 - countR - 1][e2 - countR, e2 - 1][e2]
    int countR = e1 - find;
    node.left = doBuild(s1, find - 1, s2, e2 - countR - 1);
    node.right = doBuild(find + 1, e1, e2 - countR, e2 - 1);

    return node;
  }
}
