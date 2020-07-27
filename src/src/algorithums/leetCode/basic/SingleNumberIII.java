/**
 * 项目名：  steping
 * 文件名：  SingleNumberIII.java
 * 模块说明：
 * 修改历史：
 * 2019-08-06 - Songyanyan - 创建。
 */
package algorithums.leetCode.basic;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 只出现一次的数字 III - leetcode260
 *
 * @author Songyanyan
 */
public class SingleNumberIII {
	/**
	 * 集合做法
	 *
	 * @param nums
	 * @return
	 */
	public int[] singleNumberS1(int[] nums) {
		HashSet set = new HashSet();

		for (int num : nums) {
			if (set.contains(num)) {
				set.remove(num);
			} else {
				set.add(num);
			}
		}
		Integer[] res = new Integer[2];
		set.toArray(res);
		return new int[]{res[0], res[1]};
	}

	/**
	 * 位运算做法-异或
	 *
	 * @param nums
	 * @return
	 */
	public int[] singleNumberS2(int[] nums) {
		Arrays.sort(nums);
		int count = 0;
		int[] res = new int[2];

		int i = 0;
		while (i < nums.length - 1) {
			int calc = nums[i] ^ nums[i + 1];
			if (calc == 0) {
				i += 2;
				continue;
			}
			res[count] = nums[i];
			if (++count == 2) {
				break;
			}
			i++;
		}

		if (count == 1) {
			res[1] = nums[nums.length - 1];
		}

		return res;
	}
}
