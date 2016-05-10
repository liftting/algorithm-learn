package middle.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wm on 16/5/9.
 * 73. Set Matrix Zeroes
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * //行 列 都置0
 * <p/>
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * 空间使用
 * 1，查找，存储坐标点， O(m+n)
 * 2,优化   第一行 第一列作为标准点
 * 其中一个办法就是，先把整个矩阵的第1行和第1列拿来作置零标尺。比如第k行必须置零，那么就把matrix[k-1][0]置为0，到最后在根据标尺上的信息来完成全部的置零操作。
 */
public class SetMatrixZeros {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.setZeroes(new int[][]{
                {0}
        });
    }

    public static class Solution {
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length <= 0) return;

            Set<Integer> row = new HashSet<Integer>();
            Set<Integer> col = new HashSet<Integer>();

            for (int i = 0; i < matrix.length; i++) {
                int[] array = matrix[i];
                for (int j = 0; j < array.length; j++) {

                    if (matrix[i][j] == 0) {
                        row.add(i);
                        col.add(j);
                    }

                }
            }

            for (int irow : row) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[irow][j] = 0;
                }
            }

            for (int icol : col) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][icol] = 0;
                }
            }
        }
    }

}
