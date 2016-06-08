package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/29.
 * <p/>
 * 74. Search a 2D Matrix
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p/>
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * <p/>
 * Consider the following matrix:
 * <p/>
 * [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 * <p/>
 * 1，行 列都是递增的，
 * <p/>
 * ：：
 * 1，先查找在哪一行，（最后一个元素，）
 * 2，找到后再到列中查找，
 * <p/>
 * 二分，
 * <p/>
 * 解法：add by 编程之法书上的逻辑
 * 这种行和列分别递增的矩阵，有一个专有名词叫做杨氏矩阵
 * 1， 分治， 在对角线上查找其处于哪两个元素中间，这就定位出两个矩形，缩小了范围，
 * <p/>
 * 2，定位法， 先将位置定位到右上角位置i其值为a
 * 在循环判断大小，看是往左边走，还是下边走的策略，
 */
public class SearchInMatrix {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] data = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        CommonUtil.show(s.searchMatrixTwo(data, 9));
    }

    public static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {

            if (matrix == null) return false;

            int rowNum = matrix.length;

            int i = 0, j = rowNum - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                int rowData[] = matrix[mid];

                int colLen = rowData.length;
                int last = rowData[colLen - 1];
                if (last > target) {
                    j = mid;
                } else if (last < target) {
                    i = mid + 1;
                } else {
                    return true;
                }
            }

            int rowSelect = j;

            int[] rowSelectData = matrix[rowSelect];

            int k = 0, t = rowSelectData.length - 1;

            while (k < t) {
                int mid = (k + t) / 2;
                int val = rowSelectData[mid];
                if (val > target) {
                    t = mid;
                } else if (val < target) {
                    k = mid + 1;
                } else {
                    return true;
                }
            }

            if (k == t && rowSelectData[k] == target) return true;

            return false;

        }


        /**
         * 使用定位法，
         *
         * @param matrix
         * @param target
         * @return
         */
        public boolean searchMatrixTwo(int[][] matrix, int target) {
            // 先定位右上角
            int row = matrix.length;
            int col = matrix[0].length;

            int i = 0, j = col - 1;
            int first = matrix[i][j];

            while (true) {
                if (target == first) {
                    return true;
                } else if (target > first && i < row - 1) {
                    //往下走
                    first = matrix[++i][j];
                } else if (target < first && j > 0) {
                    first = matrix[i][j--];
                } else return false;
            }

        }
    }


}
