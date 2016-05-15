package middle.dongtaiguihua;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/15.
 */
public class UniquePathTwo {


    public static void main(String[] args) {
        Solution solution = new Solution();
        CommonUtil.show(solution.uniquePathsWithObstacles(CommonUtil.buildArrayMap()));
    }

    public static class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            return uniquePaths(obstacleGrid);
        }


        public int uniquePaths(int[][] grid) {

            if (grid.length <= 0) return 0;
            if (grid[0].length <= 0) return 0;

            int m = grid.length;
            int n = grid[0].length;

            if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return 0;

            Integer[][] res = new Integer[m][n];
            return search(grid, res, 0, 0, m, n);
        }

        public int search(int visited[][], Integer[][] res, int x, int y, int m, int n) {
            if (x == m - 1 && y == n - 1) {
                return 1;
            }

            int sum = 0;

            // 因为是直接往前走，所以不用特殊回溯，直接判断，不用标记访问过的，
            if (x < m - 1 && visited[x + 1][y] == 0) {
                Integer val = res[x + 1][y];
                if (val == null) {
                    val = search(visited, res, x + 1, y, m, n);
                    res[x + 1][y] = val; // 缓存，避免重复递归
                }
                sum += val;
            }

            if (y < n - 1 && visited[x][y + 1] == 0) {
                Integer val = res[x][y + 1];
                if (val == null) {
                    val = search(visited, res, x, y + 1, m, n);
                    res[x][y + 1] = val;
                }
                sum += val;
            }

            return sum;

        }
    }

}
