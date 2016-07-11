package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/11.
 * 240. Search a 2D Matrix II
 * <p/>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p/>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * <p/>
 * Consider the following matrix:
 * <p/>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p/>
 * Given target = 20, return false.
 * <p/>
 * 和I的区别是，这个是 行 递增，列递增， I是每个元素都是递增的，
 * <p/>
 * 解法：
 * 1，二分，遍历每一行，然后进行二分，
 */
public class SearchInMatrixTwo {

    public static void main(String[] args) {
//        int[][] data = new int[][]{
//                {1, 4, 7, 11, 15},
//                {2, 5, 8, 12, 19},
//                {3, 6, 9, 16, 22}
//        };
        int[][] data = new int[][]{
                {-5}
        };

        Solution s = new Solution();

        CommonUtil.show(s.searchMatrix(data, -5));
    }

    public static class Solution {
        // 暴力遍历的方式，时间复杂度，
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length < 0) return false;
            int len = matrix.length;

            for (int i = 0; i < len; i++) {
                int array[] = matrix[i];
                if (searchBinary(array, target)) {
                    return true;
                }
            }

            return false;
        }

        public boolean searchBinary(int array[], int value) {
            int left = 0;
            int right = array.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (array[mid] < value) {
                    left = mid + 1;
                } else if (array[mid] > value) {
                    right = mid - 1;
                } else {
                    return true;
                }
            }

            return false;
        }
    }

    public class OtherSolution {
        //深度搜索，从右上角开始， 需要额外存储来标记访问的位置，
        public boolean dpSearchMatrix(int[][] matrix, int target) {

            //TODO 深搜
            return false;


        }

        public boolean searchMatrix(int[][] matrix, int target) {
            //右上角位置定位
            if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
                return false;
            }
            int col = matrix[0].length-1;
            int row = 0;
            // 因为递增，不会出现 往右走的情况，
            while(col >= 0 && row <= matrix.length-1) {
                if(target == matrix[row][col]) {
                    return true;
                } else if(target < matrix[row][col]) {
                    col--;
                } else if(target > matrix[row][col]) {
                    row++;
                }
            }
            return false;


        }

    }


}
