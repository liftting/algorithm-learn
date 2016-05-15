package middle.array;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/15.
 * 54. Spiral Matrix
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p/>
 * For example,
 * Given the following matrix:
 * <p/>
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5]
 * <p/>
 * <p/>
 * {1, 2, 3, 4, 5, 6},
 * {7, 8, 9, 10, 11, 12},
 * {13, 14, 15, 16, 17, 18}
 * <p/>
 * 1 2 3 4 5 6 12
 * <p/>
 * <p/>
 * 1, 回环遍历，
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showInteger(s.spiralOrder(CommonUtil.buildArrayMap()));
    }


    public static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> result = new ArrayList<Integer>();
            if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return result;

            int i = 0, j = 0;
            int m = matrix.length;
            int n = matrix[0].length;

            boolean visited[][] = new boolean[m][n];

            while (i < m && j < n) {
                result.addAll(search(matrix, visited, i, j, m, n));
                i++;
                j++;
                m -= 1;
                n -= 1;
            }

            return result;

        }


        // 一次右边 下边， 左边，上边 ，递归
        public List<Integer> search(int[][] matrix, boolean[][] visited, int beginX, int beginY, int endX, int endY) {

            List<Integer> result = new ArrayList<Integer>();

            int px = beginX;
            int py = beginY;

            while (py < endY) {
                if (!visited[px][py]) {
                    result.add(matrix[px][py]);
                    visited[px][py] = true;
                }
                py++;
            }
            py--;

            while (px < endX) {
                if (!visited[px][py]) {
                    result.add(matrix[px][py]);
                    visited[px][py] = true;
                }

                px++;
            }
            px--;

            while (py >= beginX) {
                if (!visited[px][py]) {
                    result.add(matrix[px][py]);
                    visited[px][py] = true;
                }
                py--;
            }
            py++;

            while (px >= beginY) {
                if (!visited[px][py]) {
                    result.add(matrix[px][py]);
                    visited[px][py] = true;
                }
                px--;
            }
            px++;

            return result;

        }
    }

}
