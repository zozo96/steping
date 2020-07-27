package java8.face;

public class Node {
    public Node parent;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node left;
    public Node right;
    public String data;

    Node(String n){
        data = n;
    }
}