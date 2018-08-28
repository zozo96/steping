/**
 * 项目名：  steping
 * 文件名：  CloneableTest.java
 * 模块说明：
 * 修改历史：
 * 2018-07-17 - Songyanyan - 创建。
 */
package interfaces;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 克隆接口实现 提供clone方法 拷贝一份新对象
 *
 * @author Songyanyan
 */
public class CloneableTest {
  public static void main(String[] args) {
    
    Employee em = new Employee("syy", 8.5);
    try {
      Employee em2 = em.clone();
      em2.setHireDay(2018, 07, 23);
      em2.raiseSalary(50);
      System.out.println(em);
      System.out.println(em2);
    } catch (CloneNotSupportedException ex) {
      ex.printStackTrace();
    }
    
    // 数组克隆 所有数组类型都有一个 public 的 clone 方法， 而不是 protected 。
    int[] arrays = { 1, 2, 3 };
    int[] arraysCloned = arrays.clone();
    arrays[2] = 100;
    System.out.println(arraysCloned[2]); // 3
    
    // 二维数组浅克隆
    int[][] arrayTest = { { 3, 4 }, { 1, 2 }, { 2, 4 }, { 3, 5 }, { 2, 5 } };
    int[][] arrayTestCloned = arrayTest.clone();
    arrayTestCloned[0][0] = 100;
    System.out.println(arrayTest[0][0]); // 100
    
    int[][] arrayTestCloned2 = new int[arrayTest.length][];
    for (int i = 0; i < arrayTest.length; i++) {
      arrayTestCloned2[i] = Arrays.copyOf(arrayTest[1], arrayTest[1].length);
    }
    
    arrayTestCloned2[0][0] = 50;
    System.out.println(arrayTest[0][0]);
  }
}

class Employee implements Cloneable {
  // clone方法是Object的一个protected方法，不可以直接调用
  private String name;
  private double salary;
  private Date hireDay;
  
  public Employee(String name, double salary) {
    this.name = name;
    this.salary = salary;
    hireDay = new Date();
  }
  
  @Override
  public Employee clone() throws CloneNotSupportedException {
    // call Object.clone()
    Employee cloned = (Employee) super.clone();
    // clone mutable fields 克隆可变域
    cloned.hireDay = (Date) hireDay.clone();
    
    return cloned;
  }
  
  /**
   * Set the hire day to a given date.
   * 
   * @param year
   *          the year of the hire day
   * @param month
   *          the month of the hire day
   * @param day
   *          the day of the hire day
   */
  public void setHireDay(int year, int month, int day) {
    Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
    
    // Example of instance field mutation
    hireDay.setTime(newHireDay.getTime());
  }
  
  public void raiseSalary(double byPercent) {
    double raise = salary * byPercent / 100;
    salary += raise;
  }
  
  public String toString() {
    return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
  }
}