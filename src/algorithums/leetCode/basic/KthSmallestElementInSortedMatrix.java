/**
 * 项目名：  steping
 * 文件名：  KthSmallestElementInSortedMatrix.java
 * 模块说明：
 * 修改历史：
 * 2019-08-06 - Songyanyan - 创建。
 */
package leetCode.basic;

/**
 * 有序矩阵中第K小的元素 【LeetCode-378】
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 *
 * @author Songyanyan
 */
public class KthSmallestElementInSortedMatrix {

	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length;

		int[] ptrs = new int[n]; //每一行指针位置，即列索引
		int[] currentComparors = new int[n];//各行指针所指的数据
		int count = 0;// 已扫描过多少个值

		for (int x = 0; x < n; x++) {
			ptrs[x] = -1;
		}

		while (count < k) {
			// i为行 ptrs[i]为列 ptrs[i]+1 下一列
			for (int i = 0; i < n; i++) {
				if (ptrs[i] + 1 < n) {
					currentComparors[i] = matrix[i][ptrs[i] + 1];
				} else {
					currentComparors[i] = Integer.MAX_VALUE;
				}
			}

			int lineIndex = findMinPtrIndex(currentComparors);
			if (++count == k) {
				return currentComparors[lineIndex];
			}

			ptrs[lineIndex]++;
		}

		return -1;
	}

	private int findMinPtrIndex(int[] comparors) {
		int minPtr = 0;
		for (int i = 1; i < comparors.length; i++) {
			minPtr = comparors[i] <= comparors[minPtr] ? i : minPtr;
		}

		return minPtr;
	}

	public static void main(String[] args) {
		KthSmallestElementInSortedMatrix sortedMatrix = new KthSmallestElementInSortedMatrix();
		int[][] matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
		System.out.println(sortedMatrix.kthSmallest(matrix, 8));
		;
	}
}
