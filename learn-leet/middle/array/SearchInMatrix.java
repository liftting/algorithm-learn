package middle.array;

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
 */
public class SearchInMatrix {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] data = new int[][]{
                {1}
        };
        s.searchMatrix(data, 1);
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
    }


}
