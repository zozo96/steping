/**
 * 项目名：  steping
 * 文件名：  Item9_OverrideHashCode.java
 * 模块说明：
 * 修改历史：
 * 2018-03-05 - Songyanyan - 创建。
 */
package methodsCommonToObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * 当重写equals时，需要重写hashCode，否则无法结合所有基于散列的集合
 *
 * @author Songyanyan
 */
public class Item9_OverrideHashCode {
  public static void main(String[] args) {
    Map<PhoneNumber, String> map = new HashMap<PhoneNumber, String>();
    map.put(new PhoneNumber(101, 152, 9999), "SYY");
    
    // 由于没有重写hashCode 两个相同PhoneNumber实例具有不同的散列值，散列码不同就不需要校验对象等同
    // 重写过hashCode() 就可以得到响应的值
    System.out.println(map.get(new PhoneNumber(101, 152, 9999)));
    System.out.println(new PhoneNumber(101, 152, 9999).toString());
  }
}

final class PhoneNumber implements Comparable<PhoneNumber> {
  private final short areaCode;
  private final short prefix;
  private final short lineNumber;
  
  // Volatile 变量具有 synchronized 的可见性特性，但是不具备原子特性。这就是说线程能够自动发现 volatile 变量的最新值。
  // 下载Volatile变量可用于提供线程安全，但是只能应用于非常有限的一组用例：多个变量之间或者某个变量的当前值与修改后值之间没有约束。
  private volatile int hashCode;
  
  public PhoneNumber(int areaCode, int prefix, int lineNumber) {
    rangeCheck(areaCode, 999, "areaCode");
    rangeCheck(prefix, 999, "prefix");
    rangeCheck(lineNumber, 9999, "lineNumber");
    this.areaCode = (short) areaCode;
    this.prefix = (short) prefix;
    this.lineNumber = (short) lineNumber;
  }
  
  private static void rangeCheck(int arg, int max, String name) {
    if (arg < 0 || arg > max)
      throw new IllegalArgumentException(name + ":" + arg);
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof PhoneNumber))
      return false;
    PhoneNumber phoneNumber = (PhoneNumber) o;
    return phoneNumber.areaCode == areaCode && phoneNumber.prefix == prefix &&
      phoneNumber.lineNumber == lineNumber;
  }
  
  // 重写equals后重写hashCode()是为了保证 相同对象的hashCode一定要相同，而不同对象也可以有相同hashCode，这时候执行equals
  @Override
  public int hashCode() {
    int result = hashCode;
    if (hashCode == 0) {
      result = 17;
      result = 31 * result + areaCode;
      result = 31 * result + prefix;
      result = 31 * result + lineNumber;
      hashCode = result;
    }
    return result;
  }
  
  // Item10_Override toString
  @Override
  public String toString() {
    return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
  }
  
  // Item12_Override CompareTo
  @Override
  public int compareTo(PhoneNumber o) {
    if (areaCode > o.areaCode)
      return 1;
    if (areaCode < o.areaCode)
      return -1;
    
    if (prefix > o.prefix)
      return 1;
    if (prefix < o.prefix)
      return -1;
    
    if (lineNumber > o.lineNumber)
      return 1;
    if (lineNumber < o.lineNumber)
      return -1;
    
    return 0;// All are equal
  }
  /*
   * @Override public int compareTo(PhoneNumber o) { // 用这种方式需要注意，最大值和最小值差不能超过Integer.MAX_VALUE(2^31
   * -1) int areaCodeDiff = areaCode - o.areaCode; if (areaCodeDiff != 0) return areaCodeDiff; int
   * prefixDiff = prefix - o.prefix; if (prefixDiff != 0) return prefixDiff; int lineNumberDiff =
   * lineNumber - o.lineNumber; if (lineNumberDiff != 0) return lineNumberDiff;
   * 
   * return 0;//All are equal }
   */
}