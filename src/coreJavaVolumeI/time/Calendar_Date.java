/**
 * 项目名：  steping
 * 文件名：  Calendar_Date.java
 * 模块说明：
 * 修改历史：
 * 2018-07-11 - Songyanyan - 创建。
 */
package time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日历测试
 *
 * @author Songyanyan
 */
public class Calendar_Date {
  public static void main(String[] args) {
    
    Date birthday = new Date();
    System.out.println(birthday);
    
    LocalDate now = LocalDate.now(); // 2018-07-11
    LocalDate date1999_12_31 = LocalDate.of(1999, 12, 31); // 1999-12-31
    LocalDate localDate = date1999_12_31.plusDays(1000); // 返回一个新对象 不改变对象date1999_12_31
    System.out.println(localDate);// 2002-09-26
    
    GregorianCalendar someDay = new GregorianCalendar(1999, 12, 31);
    someDay.add(Calendar.DAY_OF_MONTH, 1000);
    System.out.println(someDay.get(Calendar.YEAR) + "-" + someDay.get(Calendar.MONTH) + "-" +
      someDay.get(Calendar.DAY_OF_MONTH));// someDay对象状态会改变
    
    // 时间线 Instant
    // 查看算法的运行时间
    Instant start = Instant.now();
    printCalendar();
    Instant end = Instant.now();
    Duration timeElapsed = Duration.between(start, end);
    long millis = timeElapsed.toMillis();
    long nanos = timeElapsed.toNanos();
    
    // 查看两个算法运行谁更快
    Instant start1 = Instant.now();
    printCalendar();
    Instant end1 = Instant.now();
    Duration timeElapsed1 = Duration.between(start1, end1);
    boolean faster = timeElapsed1.minus(timeElapsed).isNegative();
    
    // 日期调整器
    LocalDate nextTuesday = LocalDate.of(2018, 7, 1).with(TemporalAdjusters.nextOrSame(
      DayOfWeek.TUESDAY)); // 从2018.7.1号开始的下一个周二
    
    // 实现自己的日期调整器 实现TemporalAdjuster接口
    TemporalAdjuster NEXT_SUNDAY = w -> {
      LocalDate result = (LocalDate) w; // 需要强制转换 TemporalAdjuster任何一个实现类都可
      do {
        result = result.plusDays(1);
      } while (result.getDayOfWeek().getValue() != 7);
      return result;
    };
    // 避免强制转换 TemporalAdjusters.ofDateAdjuster(UnaryOperator<LocalDate> dateBasedAdjuster)
    TemporalAdjuster NEXT_SATURDAY = TemporalAdjusters.ofDateAdjuster(w -> {
      LocalDate date = w;
      do {
        date = date.plusDays(1);
      } while (date.getDayOfWeek().getValue() != 6);
      return date;
    });
    
    System.out.println(LocalDate.now().with(NEXT_SUNDAY));
    
    System.out.println(birthday.toInstant());
    System.out.println(Date.from(Instant.now()));
    // Date.valueOf(LocalDate.now()); // java.sql.Date
  }
  
  public static void printCalendar() {
    // 打印当月日历
    LocalDate date = LocalDate.now();
    System.out.println("\n\nMON TUE WED THU FRI SAT SUN");
    // 获取月、日
    int month = date.getMonthValue();
    int day = date.getDayOfMonth();
    // 获取本月第一天
    date = date.minusDays(day - 1);
    // 本月第一天第一天为周几？
    int dayOfWeek = date.getDayOfWeek().getValue();
    // 打印日历第一行的缩进
    for (int i = 1; i < dayOfWeek; i++) {
      System.out.print("    ");
    }
    // 打印主体
    while (date.getMonthValue() == month) {
      System.out.printf("%3d", date.getDayOfMonth());
      if (date.getDayOfMonth() == day)
        System.out.print("*");
      else
        System.out.print(" ");
      date = date.plusDays(1);
      if (date.getDayOfWeek().getValue() == 1)
        System.out.println();
    }
  }
}