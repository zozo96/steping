/**
 * 项目名：  steping
 * 文件名：  Size.java
 * 模块说明：
 * 修改历史：
 * 2018-07-16 - Songyanyan - 创建。
 */
package enum_;

/**
 * 枚举类
 *
 * @author Songyanyan
 */
public enum Size {
  SMALL("S"), MEDIUM("M"), LARGR("L"), EXTRA_LARGE("XL");
  
  private String abbr; // 缩写
  
  private Size(String abbr) {
    this.abbr = abbr;
  }
  
  public String getAbbr() {
    return abbr;
  }
  
  public static void main(String[] args) {
    SMALL.getAbbr(); // S
    SMALL.toString(); // SMALL
    
    Size s = Enum.valueOf(Size.class, "SMALL");// toString的逆方法 valueOf
    
    Size[] sizes = Size.values();
    System.out.println(sizes);
    
    Size.EXTRA_LARGE.ordinal(); // 声明中枚举常量的位置 3
  }
}