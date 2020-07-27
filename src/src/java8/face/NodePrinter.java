package java8.face;

public class NodePrinter implements INodeHandler {
    @Override
    public void handle(Node n) {
        System.out.print(n.data);
    }
}
