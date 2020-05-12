/**
 * 项目名：  steping
 * 文件名：  ClassTest.java
 * 模块说明：
 * 修改历史：
 * 2018-07-16 - Songyanyan - 创建。
 */
package reflection;

import java.time.LocalDate;

/**
 * Class类学习
 *
 * @author Songyanyan
 */
public class ClassTest {
  public static void main(String[] args) {
    Employee e = new Employee("Harry", 20, 1996, 07, 26);
    System.out.println(e.getClass().getName());
    System.out.println(Double[].class.getName());
    System.out.println(int[].class.getName());
    // Employee p = e.getClass().newInstance(); // 没有默认构造器会抛出异常
  }
}

class Employee {
  private String name;
  private double salary;
  private LocalDate hireDay;
  
  public Employee(String n, double s, int year, int month, int day) {
    name = n;
    salary = s;
    hireDay = LocalDate.of(year, month, day);
  }
  
  public String getName() {
    return name;
  }
  
  public double getSalary() {
    return salary;
  }
  
  public LocalDate getHireDay() {
    return hireDay;
  }
  
  public void raiseSalary(double byPercent) {
    double raise = salary * byPercent / 100;
    salary += raise;
  }
}