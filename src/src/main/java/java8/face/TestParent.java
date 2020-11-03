package java8.face;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Sonya
 * @date : 2020/3/17 11:15
 */
public class TestParent {

    public static void main(String[] args) {
        List<Node> ints = Arrays.asList(new Node("1"), new Node("2"), null, new Node("3"));
        List<Node> ints2 = Arrays.asList(new Node("1"), new Node("2"), new Node(null), new Node("3"));
//        List<String> collect = ints.stream().map(Node::getData).collect(Collectors.toList());
        List<String> collect2 = ints2.stream().map(Node::getData).collect(Collectors.toList());
    }

}
