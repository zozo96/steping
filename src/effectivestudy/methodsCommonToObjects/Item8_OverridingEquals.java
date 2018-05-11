/**
 * 项目名：  steping
 * 文件名：  Item8_OverridingEquals.java
 * 模块说明：
 * 修改历史：
 * 2018-02-26 - Songyanyan - 创建。
 */
package methodsCommonToObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

/**
 * 重写Equals请遵守通用约定
 *
 * @author Songyanyan
 */
public class Item8_OverridingEquals {
  // 坚持自反性、对称性、传递性、一致性
  // null != non-null
  static final CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
  static final String s = "polish";
  
  public static void main(String[] args) {
    // 1.违反了对称性 CaseInsensitiveString中重写的equals方法
    System.out.println("s.equals(cis) == " + s.equals(cis));
    System.out.println("cis.equals(s) == " + cis.equals(s));
    
    // 2.打印出的为true 有的情形会抛出Runtime Exception 或 打印false
    List<CaseInsensitiveString> list = new ArrayList<CaseInsensitiveString>();
    list.add(cis);
    System.out.println("list.contains(cis) == " + list.contains(cis));
    
    // 3. 违反连续性 A=B B=C A≠C
    ColorPoint A = new ColorPoint(1, 2, RED);
    Point B = new Point(1, 2);
    ColorPoint C = new ColorPoint(1, 2, BLACK);
    System.out.println("A.equals(B) == " + A.equals(B));
    System.out.println("B.equals(C) == " + B.equals(C));
    System.out.println("C.equals(A) == " + C.equals(A));
    
    class A {
      private String name;
      private int index;
      
      A(int index, String name) {
        this.index = index;
        this.name = name;
      }
    }
    // List中的remove
    List<A> list1 = new ArrayList();
    A a1 = new A(1, "A1");
    A a2 = new A(2, "A2");
    A a3 = new A(3, "A3");
    
    list1.add(a1);
    list1.add(a2);
    list1.add(a3);
    list1.remove(a2);
    for (A a : list1) {
      System.out.println(a.index);
    }
    // [methodsCommonToObjects.Item8_OverridingEquals$1A@3834d63f
    System.out.println(list1.toString());
  }
}

// CaseInsensitiveString 违反对称性
final class CaseInsensitiveString {
  private final String s;
  
  public CaseInsensitiveString(String s) {
    if (s == null)
      throw new NullPointerException();
    this.s = s;
  }
  
  @Override
  public boolean equals(Object o) {
    if (o instanceof CaseInsensitiveString)
      return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
    if (o instanceof String) // Remove this will correct it
      return s.equalsIgnoreCase((String) o);
    return false;
  }
}

// Point及子类 违反连续性
class Point {
  private final int x;
  private final int y;
  public static final Set<Point> unitCircle;
  static {
    unitCircle = new HashSet<Point>();
    unitCircle.add(new Point(0, 1));
    unitCircle.add(new Point(0, -1));
    unitCircle.add(new Point(1, 0));
    unitCircle.add(new Point(-1, 0));
  }
  
  public static boolean onUnitCircle(Point point) {
    return unitCircle.contains(point);
  }
  
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  // public Point(){}
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Point))
      return false;
    Point point = (Point) o;
    return point.x == x && point.y == y;
  }
}

class ColorPoint extends Point {
  private final Color color;
  
  public ColorPoint(int x, int y, Color color) {
    super(x, y);// 手动调用超类构造器必须放在构造器第一行
    this.color = color;
  }
  
  public boolean equals(Object o) {
    if (o instanceof ColorPoint)
      return super.equals(o) && ((ColorPoint) o).color == color;
    if (o instanceof Point) {
      return super.equals(o);
    }
    return false;
  }
  
}

class CounterPoint extends Point {
  private static final AtomicInteger counter = new AtomicInteger();
  
  public CounterPoint(int x, int y) {
    super(x, y);
    counter.getAndIncrement();
  }
  
  public int numberCreated() {
    return counter.get();
  }
  
  public int[] test() {
    int[] ints = { 1, 1 };
    return new int[] { 1, 1 };
  }
}
