
/**
 * 项目名：  syy-prj
 * 文件名：  Item5_AvoidUnneceObject.java
 * 模块说明：
 * 修改历史：
 * 2018-02-24 - Songyanyan - 创建。
 */

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 避免创建不需要的对象
 *
 * @author Songyanyan
 */
public class Item5_AvoidUnneceObject {
  // 1.Don't Do this! "hello"本来就是一个String实例
  // 调用String构造函数即新建对象
  // for循环中将会大量产生冗余对象，
  String s = new String("hello");
  
  // 若有相同"hello" 则复用同一对象
  String t = "hello";
  
  // 3.注意自动装箱
  public static void main(String[] args) {
    Long sum = 0L;// 建议优先使用基本类型 此处为Long
    for (long i = 0; i <= Integer.MAX_VALUE; i++) { // 优先使用基本类型，同时注意无意识的自动装箱 例如本例子Long类型和long
      // 计算时候性能差距
      sum += i;
    }
    System.out.println(sum);
  }
  
}

// 2.1不合理的使用对象
class person_bad {
  private final Date birthDate;
  
  person_bad(Date birthDate) {
    this.birthDate = birthDate;
  }
  
  public boolean isTruth() {
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    gmtCal.set(1996, Calendar.JULY, 26);
    Date startTime = gmtCal.getTime();
    gmtCal.set(2100, Calendar.JULY, 26);
    Date endTime = gmtCal.getTime();
    return birthDate.compareTo(startTime) >= 0 && birthDate.compareTo(endTime) < 0;
  }
}

// 2.2极大改善
class person_nice {
  private final Date birthDate;
  
  person_nice(Date birthDate) {
    this.birthDate = birthDate;
  }
  
  // static 修饰的强调只有一份
  private static final Date startTime;
  private static final Date endTime;
  static {
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    gmtCal.set(1996, Calendar.JULY, 26);
    startTime = gmtCal.getTime();
    gmtCal.set(2100, Calendar.JULY, 26);
    endTime = gmtCal.getTime();
  }
  
  public boolean isTruth() {
    return birthDate.compareTo(startTime) >= 0 && birthDate.compareTo(endTime) < 0;
  }
}