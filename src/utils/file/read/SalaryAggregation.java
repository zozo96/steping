/**
 * 项目名：  steping
 * 文件名：  SalaryAggregation.java
 * 模块说明：
 * 修改历史：
 * 2018-12-13 - Songyanyan - 创建。
 */
package file.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalaryAggregation {
  private final static SalaryTitles[] salaryTitles = SalaryTitles.values();
  private static ArrayList<HashMap> salary = new ArrayList<>();
  public static Double salarySum;
  public static Double sandCardSum;
  public static Double taxSum;
  public static Double accumulationFundSum;
  
  public void generateSalaryAggregation(String folderPath) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    
    int i = 1;
    Double d;
    while (true) {
      calendar.add(Calendar.MONTH, -i);
      String mon = sdf.format(calendar.getTime());
      File file = new File(folderPath + mon + ".txt");
      if (!file.exists()) {
        break;
      } else {
        try {
          InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),
            "GBK");
          BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
          String line = bufferedReader.readLine();
          HashMap map = new HashMap<String, Double>();
          while (line != null) {
            if (line.charAt(0) == '－') {
              line = bufferedReader.readLine();
              continue;
            }
            for (int s = 0; s < salaryTitles.length; s++) {
              if (line.contains(salaryTitles[s].getName())) {
                d = getNumber(salaryTitles[s].getName(), line);
                if (salaryTitles[s].name().contains("2")) {
                  map.put(salaryTitles[s - 1].name(), d);
                  break;
                } else {
                  map.put(salaryTitles[s].name(), d);
                  break;
                }
              }
            }
            line = bufferedReader.readLine();
          }
          salary.add(map);
          bufferedReader.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      System.out.println(file.getName() + "computed");
    }
    // TODO 计算Sum
  }
  
  private Double getNumber(String title, String line) {
    Double d;
    String str = line.substring(title.length(), line.length());
    int index = str.lastIndexOf(' ');
    d = Double.valueOf(str.substring(index + 1));
    return d;
  }
  
  public static Double getSalarySum() {
    return salarySum;
  }
  
  public static void setSalarySum(Double salarySum) {
    SalaryAggregation.salarySum = salarySum;
  }
  
  public static Double getSandCardSum() {
    return sandCardSum;
  }
  
  public static void setSandCardSum(Double sandCardSum) {
    SalaryAggregation.sandCardSum = sandCardSum;
  }
  
  public static Double getTaxSum() {
    return taxSum;
  }
  
  public static void setTaxSum(Double taxSum) {
    SalaryAggregation.taxSum = taxSum;
  }
  
  public static Double getAccumulationFundSum() {
    return accumulationFundSum;
  }
  
  public static void setAccumulationFundSum(Double accumulationFundSum) {
    SalaryAggregation.accumulationFundSum = accumulationFundSum;
  }
  
  public static void main(String[] args) {
    SalaryAggregation salaryAggregation = new SalaryAggregation();
    
    String folderPath = "C:\\Users\\songyanyang\\Desktop\\S\\salary\\";
    salaryAggregation.generateSalaryAggregation(folderPath);
  }
  
}
