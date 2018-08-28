/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：  steping
 * 文件名：  Operation_Add.java
 * 模块说明：
 * 修改历史：
 * 2018-08-15 - Songyanyan - 创建。
 */
package leetCode.basic;

/**
 * 四则运算实现之加减
 *
 * @author Songyanyan
 */
public class Operation_Add {
  public int doAdd(int a, int b) {
    
    int c;
    // 记录进位情况
    c = a & b;
    while (c != 0) {
      a = a ^ b; // 二进制每位相加（不考虑进位）就相当于各位做异或操作（相同为0，不同为1），101^111
      b = c << 1;// c为当前进位情况，左移一位即获取应当进位的表达 101&111 = 101 101<<1=1010
      c = a & b;
    }
    
    return a | b;
  }
  
  public int doCut(int a, int b) {
    // a - b = a + (-b); -b = ~(b-1); a - b = a + (~(b-1));
    return doAdd(a, ~b + 1);
  }
  
  public int doMulti(int a, int b) {
    return 0;
  }
}
