package java8.face;

public class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
        System.out.println("1");
    }

    public void travel(INodeHandler nh) {
        this.travel(nh, root);
    }

    private void travel(INodeHandler nh, Node node) {
        //你的前序遍历代码
        if (node == null){
            return;
        }

        nh.handle(node);

        if (node.left != null) {
            nh.handle(node.left);
            travel(nh, node.left.left);
            travel(nh, node.left.right);
        }

        if (node.right != null) {
            nh.handle(node.right);
            travel(nh, node.right.left);
            travel(nh, node.right.right);
        }

    }
}
