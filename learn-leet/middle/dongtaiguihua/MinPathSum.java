package middle.dongtaiguihua;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm on 16/4/13.
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p/>
 * Note: You can only move either down or right at any point in time.
 * <p/>
 * <p/>
 * 经典动态规划：
 * <p/>
 * 使用动态规划求解问题，最重要的就是确定动态规划三要素：问题的阶段,每个阶段的状态以及从前一个阶段转化到后一个阶段之间的递推关系。
 * 递推关系必须是从次小的问题开始到较大的问题之间的转化，从这个角度来说，动态规划往往可以用递归程序来实现，不过因为递推可以充分利用前面保存的子问题的解来减少重复计算，
 * 所以对于大规模问题来说，有递归不可比拟的优势，这也是动态规划算法的核心之处。确定了动态规划的这三要素，整个求解过程就可以用一个最优决策表来描述，最优决策表是一个二维表，
 * 其中行表示决策的阶段，列表示问题状态，表格需要填写的数据一般对应此问题的在某个阶段某个状态下的最优值（如最短路径，最长公共子序列，最大价值等），填表的过程就是根据递推关系，
 * 从1行1列开始，以行或者列优先的顺序，依次填写表格，最后根据整个表格的数据通过简单的取舍或者运算求得问题的最优解。
 * <p/>
 * //递推公式
 * dp[i][j] = Min(dp[i-1][j],dp[i][j-1]) + val[i][j]
 * <p/>
 * 左边界， 和上边界，要计算好，因为是边界，所以没有两种策略，
 */
public class MinPathSum {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.minPathSum(new int[][]{
                {1, 2}, {1, 1}
        });
    }

    public static class Solution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length <= 0) return 0;
            // 00 nn 最小，
            int row = grid.length; // 行
            int col = grid[0].length;// 列

            int[][] min = new int[row][col];

            min[0][0] = grid[0][0];

            for (int i = 1; i < col; i++) {
                min[0][i] = grid[0][i] + min[0][i - 1];
            }

            for (int j = 1; j < row; j++) {
                min[j][0] = grid[j][0] + min[j - 1][0];
            }

            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    int cp = Math.min(min[i - 1][j], min[i][j - 1]);
                    min[i][j] = cp + grid[i][j];
                }
            }


            return min[row - 1][col - 1];
        }
    }

}
