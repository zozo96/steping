/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：  steping
 * 文件名：  CoinChange.java
 * 模块说明：
 * 修改历史：
 * 2018-12-15 - Songyanyan - 创建。
 */
package leetCode.basic;

/**
 * 最少数目硬币得到相应金额
 *
 * @author Songyanyan
 */
public class CoinChange {
  private int[] coins;
  private int amount;
  private static int maxValue = 10000;
  private static int records[][];
  // private static HashMap<int[], Integer> records;
  
  public int coinChange(int[] coins, int amount) {
    this.coins = coins;
    this.amount = amount;
    records = new int[coins.length][1000000];
    int result = search(coins.length - 1, amount);
    return result == maxValue ? -1 : result;
  }
  
  public int search(int index, int rest) {
    if (index < 0 || rest < 0) {
      return maxValue;
    }
    if (rest == 0)
      return 0;
    if (records[index][rest] > 0)
      return records[index][rest];
    records[index][rest] = Math.min(search(index, rest - coins[index]) + 1,
      search(index - 1, rest));
    return records[index][rest];
  }
  
  public static void main(String[] args) {
    CoinChange coinChange = new CoinChange();
    System.out.println(coinChange.coinChange(new int[] { 120, 6, 320, 300, 100, 192, 212, 89, 106,
      461 }, 8332));
  }
}
