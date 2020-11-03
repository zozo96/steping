/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：  steping
 * 文件名：  MinimumMovesToEqualArrayElements.java
 * 模块说明：
 * 修改历史：
 * 2019-08-06 - Songyanyan - 创建。
 */
package algorithums.leetCode.basic;

import java.util.Arrays;

/**
 * 462. 最少移动次数使数组元素相等 II
 *
 * @author Songyanyan
 */
public class MinimumMovesToEqualArrayElements {
	public int minMoves2(int[] nums) {
		Arrays.sort(nums);

		// 通过nums的中位数（奇） 、中位数的平均值（偶）

		int i = 0, j = nums.length - 1;
		int result = 0;
		while (i < j) {
			result += nums[j--] - nums[i++];
		}

		return result;
	}
}
