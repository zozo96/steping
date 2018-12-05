package leetCode.basic;

/**
 * 二维数组原地顺时针旋转90度
 * LeetCode48
 */
public class RotateImage {
    private int N = -1;

    public void rotate(int[][] matrix) {
        // 归纳法：二维数组原地旋转90度 (x,y)->(y,n-1-x);
        // n*n的矩阵 最外层有n-1组待排至其相应位置，次外层有n-1-2组待排，次次外层 n-1 - 2x待排
        // 需要总共rotate n*n / 4 组数据

        // N * N的矩阵
        N = matrix.length;

        //起始坐标
        int begin = 0;
        for (int n = N - 1; n > 0; n -= 2) { // n为当前行执行的次数
            for (int i = begin; i < begin + n; i++) {
                exch(matrix, i, begin);
            }
            begin++;
        }
    }

    private void exch(int[][] matrix, int x1, int y1) {
        int tmp0 = matrix[x1][y1];
        int x = y1;
        int y = N - 1 - x1;
        int tmp;
        for (int i = 0 ; i < 3; i++){
            tmp = matrix[x][y];
            matrix[x][y] = tmp0;
            tmp0 = tmp;
            int t = x;
            x = y;
            y = N - 1 - t;
        }
        matrix[x1][y1] = tmp0;
    }
}
