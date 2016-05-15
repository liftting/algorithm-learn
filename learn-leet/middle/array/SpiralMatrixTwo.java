package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/15.
 * 59. Spiral Matrix II
 * <p/>
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p/>
 * For example,
 * Given n = 3,
 * <p/>
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 * <p/>
 * 1,一步步往前填数据
 */
public class SpiralMatrixTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.generateMatrix(5));
    }

    public static class Solution {
        public int[][] generateMatrix(int n) {

            if (n <= 0) return new int[0][];

            int i = 0, j = 0;
            int m = n;
            int beginVal = 1;

            int[][] data = new int[n][n];

            while (i < m && j < n) {
                beginVal = write(data, beginVal, i, j, m, n);
                i++;
                j++;
                m -= 1;
                n -= 1;
            }

            return data;
        }


        public int write(int[][] data, int beginVal, int beginX, int beginY, int endX, int endY) {
            int px = beginX;
            int py = beginY;

            while (py < endY) {
                data[px][py] = beginVal++;
                py++;
            }
            py--;
            beginVal--;

            while (px < endX) {
                data[px][py] = beginVal++;
                px++;
            }
            px--;
            beginVal--;

            while (py >= beginX) {
                data[px][py] = beginVal++;
                py--;
            }
            py++;
            beginVal--;

            while (px > beginY) {
                data[px][py] = beginVal++;
                px--;
            }

            return beginVal;
        }
    }
}
