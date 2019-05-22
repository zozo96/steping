package face;

import java.lang.reflect.Parameter;

public class Test {
    public static void main(String args[]) {
        Son son = new Son();
        System.out.println(son.getValue());

//        fun();
    }

//    static Test st = new Test();
//
//    static {
//        System.out.println(1);
//    }
//
//    {
//        System.out.println(2);
//    }
//
//    Test() {
//        System.out.println(3);
//        System.out.println("a=" + a + ",b=" + b);
//    }
//
//    public static void fun() {
//        System.out.println(4);
//    }
//
//    int a = 110;
//    static int b = 112;
}

class Parent {
    int i = 1;
    public int e = getValue();
    static int t = 6;

    Parent() {
        System.out.println(i);
        int x = getValue();
        System.out.println(x);
        System.out.println("exec 父类构造器");
    }

    static {
        System.out.println("exec 静态块1");
    }

    {
        i = 2;
        System.out.println("exec 匿名块儿");
    }

    static {
        System.out.println("exec 静态块2");
    }

    protected int getValue() {
        return i;
    }

    int f = getValue();

}

class Son extends Parent {
    int j = 1;
    static int m = 2;
    Son() {
        j = 2;
    }

    {
        m = 3;
        System.out.println(m);
    }

    {
        j = 3;
    }

    @Override
    protected int getValue() {
        return j;
    }

    static {
        m = 5;
    }
}

class OutCircle {
    private int count;
    private int total;

    OutCircle(int total) {
        this.total = total;
        this.count = total;
    }

    public int calcTheLastSurvive() {

        if (total <= 0){
            return 0;
        }

        int[] circle = new int[total];
        for (int i = 0; i < total; i++) {
            circle[i] = 1;
        }

        int t = 1;
        int index = 0;
        while (count > 1) {
            while (circle[index] == 0) {
                index++;
                if (index >= total) {
                    index = 0;
                }
            }

            if (t == 3) {
                count--;
                circle[index] = 0;
                t = 1;
            } else {
                t++;
                index++;
            }

            if (index >= total) {
                index = 0;
            }
        }

        for (int i = 0; i < total; i++) {
            if (circle[i] != 0)
                return circle[i];
        }

        return -1;
    }
}