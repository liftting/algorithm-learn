package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/29.
 * 48. Rotate Image
 * <p/>
 * <p/>
 * You are given an n x n 2D matrix representing an image.
 * <p/>
 * Rotate the image by 90 degrees (clockwise).  顺时针 90度
 * <p/>
 * Follow up:
 * Could you do this in-place?
 * <p/>
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * <p/>
 * 7 4 1
 * 8 5 2
 * 9 6 3
 * 只在这一个空间中，
 * 10 11 12 -- 01 11 21
 * 00 01 02 -- 02 12 22 // 行列交换，
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * 就是 第一行，变成最后一列，  n * n 不用空间
 */
public class RotateImage {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.rotate(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}

        });
    }

    public static class Solution {
        //下面的是错误的，
        public void rotate(int[][] matrix) {
            int[][] data = new int[matrix.length][matrix.length];


            for (int i = 0; i < matrix.length; i++) {
                int[] row = matrix[i];
                int t = i;

                if (i == 0) t = matrix.length-1;
                if (i == matrix.length - 1) t = 0;

                for (int j = 0; j < row.length; j++) {
                    int k = j;

                    if (j == 0) k = row.length-1;
                    if (j == row.length - 1) k = 0;

                    data[k][t] = matrix[i][j];
                }

            }

            CommonUtil.show(data);


        }
    }

}
