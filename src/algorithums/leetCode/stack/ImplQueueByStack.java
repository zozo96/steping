/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：  steping
 * 文件名：  ImplQueueByStack.java
 * 模块说明：
 * 修改历史：
 * 2019-08-05 - Songyanyan - 创建。
 */
package leetCode.stack;

import java.util.Stack;

/**
 * 用栈实现队列操作
 * leetcode-232
 *
 * @author Songyanyan
 */
public class ImplQueueByStack {

	Stack<Integer> inStack = new Stack<>();
	Stack<Integer> outStack = new Stack<>();

	class MyQueue {

		/**
		 * Initialize your data structure here.
		 */
		public MyQueue() {
		}

		/**
		 * Push element x to the back of queue.
		 */
		public void push(int x) {
			inStack.push(x);
		}

		/**
		 * Removes the element from in front of queue and returns that element.
		 */
		public int pop() {
			if (inStack.empty()) {
				return -1;
			}

			while (!inStack.empty()) {
				outStack.push(inStack.pop());
			}

			int result = outStack.pop();

			while (!outStack.empty()) {
				inStack.push(outStack.pop());
			}

			return result;
		}

		/**
		 * Get the front element.
		 */
		public int peek() {
			if (inStack.empty()) {
				return -1;
			}

			while (!inStack.empty()) {
				outStack.push(inStack.pop());
			}

			int result = outStack.peek();

			while (!outStack.empty()) {
				inStack.push(outStack.pop());
			}

			return result;
		}

		/**
		 * Returns whether the queue is empty.
		 */
		public boolean empty() {
			return inStack.empty();
		}
	}
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */