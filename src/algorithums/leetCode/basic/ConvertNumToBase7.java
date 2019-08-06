/**
 * 项目名：  steping
 * 文件名：  ConvertNumToBase7.java
 * 模块说明：
 * 修改历史：
 * 2019-08-06 - Songyanyan - 创建。
 */
package leetCode.basic;

import java.util.Stack;

/**
 * 转换为七进制数 leetcode504
 *
 * @author Songyanyan
 */
public class ConvertNumToBase7 {
	public String convertToBase7(int num) {
		boolean isBinus = false;
		if (num < 0) {
			isBinus = true;
			num = -num;
		}

		Stack s = new Stack();
		while (num > 0) {
			s.push(num % 7);
			num /= 7;
		}
		StringBuilder sb = new StringBuilder();
		if (isBinus) {
			sb.append("-");
		}
		while (!s.empty()) {
			sb.append(s.pop());
		}
		String result = sb.toString();
		return result.equals("") ? "0" : result;

//		或者调库函数
//		return Integer.toString(num, 7);
	}
}
