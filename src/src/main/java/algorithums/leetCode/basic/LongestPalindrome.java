/**
 * 项目名：  steping
 * 文件名：  LongestPalindrome.java
 * 模块说明：
 * 修改历史：
 * 2019-08-06 - Songyanyan - 创建。
 */
package algorithums.leetCode.basic;

/**
 * 最长回文串 leetcode-409
 * <p>
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 65～90为26个大写英文字母，97～122号为26个小写英文字母
 *
 * @author Songyanyan
 */
public class LongestPalindrome {
	static class Solution {
		private static int[] arrs = new int[52];

		public static void main(String[] args) {
			System.out.println(longestPalindrome("ccc"));
		}

		public static int longestPalindrome(String s) {
			char[] chars = s.toCharArray();
			for (char c : chars) {
				if (c >= 65 && c <= 90) {
					arrs[c - 65]++;
				} else {
					arrs[c - 71]++;
				}
			}

			int result = 0;
			boolean odded = false;

			for (int i : arrs) {
				if (i % 2 == 0) {
					result += i;
				} else {
					result += i - 1;
					odded = true;
				}
			}

			if (odded) {
				result++;
			}

			return result;
		}
	}
}
