package middle.dongtaiguihua;

import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/14.
 * <p/>
 * 62. Unique Paths
 * m * n表格，求唯一路径
 * <p/>
 * 左上角 到 右下角，求唯一路径， 总共数目
 * <p/>
 * 1 1 1
 * 1 1 1
 * 1 1 1
 * 1，深搜
 * visited[]
 * 2，动态规划
 * 3， 特殊判断的策略，
 */
public class UniquePath {


    public static void main(String[] args) {
        Solution s = new Solution();
//        CommonUtil.show(s.uniquePaths(3, 3));

        DpSolution dpSolution = new DpSolution();
        CommonUtil.show(dpSolution.uniquePaths(23, 12)); // 使用下面的DP也还是会超时，leetcode

        DpSolution.RangeSolution res = new DpSolution.RangeSolution();
        CommonUtil.show(res.uniquePaths(23, 12));
    }

    public static class Solution {
        //深搜会出现 超时，  需要使用DP策略
        public int uniquePaths(int m, int n) {

            if (m == 0 || n == 0) return 0;

            int[][] grid = new int[m][n];
            boolean[][] visited = new boolean[m][n];
            return search(grid, visited, 0, 0, m, n);
        }

        public int search(int[][] grid, boolean visited[][], int x, int y, int m, int n) {

            int sum = 0;
            if (x == m - 1 && y == n - 1) {
                return 1;
            }
            visited[x][y] = true;
            if (x < m - 1) {
                if (!visited[x + 1][y]) {
                    sum += search(grid, visited, x + 1, y, m, n);
                }
            }
            if (x > 0) {
                if (!visited[x - 1][y]) {
                    sum += search(grid, visited, x - 1, y, m, n);
                }
            }
            if (y < n - 1) {
                if (!visited[x][y + 1]) {
                    sum += search(grid, visited, x, y + 1, m, n);
                }
            }
            if (y > 0) {
                if (!visited[x][y - 1]) {
                    sum += search(grid, visited, x, y - 1, m, n);
                }
            }
            visited[x][y] = false;

            return sum;
        }
    }

    public static class DpSolution {
        public int uniquePaths(int m, int n) {

            if (m == 0 || n == 0) return 0;

            boolean[][] visited = new boolean[m][n];
            Integer[][] res = new Integer[m][n];
            return search(visited, res, 0, 0, m, n);
        }

        public int search(boolean visited[][], Integer[][] res, int x, int y, int m, int n) {
            if (x == m - 1 && y == n - 1) {
                return 1;
            }

            int sum = 0;

            //
            visited[x][y] = true;
            if (x < m - 1) {
                if (!visited[x + 1][y]) {
                    Integer val = res[x + 1][y];
                    if (val == null) {
                        val = search(visited, res, x + 1, y, m, n);
                        res[x + 1][y] = val;
                    }
                    sum += val;
                }
            }

            if (y < n - 1) {
                if (!visited[x][y + 1]) {
                    Integer val = res[x][y + 1];
                    if (val == null) {
                        val = search(visited, res, x, y + 1, m, n);
                        res[x][y + 1] = val;
                    }
                    sum += val;
                }
            }

            //下面是可以往回走，判断
//            if (x > 0) {
//                if (!visited[x - 1][y]) {
//                    Integer val = res[x - 1][y];
//                    if (val == null) {
//                        val = search( visited, res, x - 1, y, m, n);
//                    }
//                    sum += val;
//                }
//            }
//
//            if (y > 0) {
//                if (!visited[x][y - 1]) {
//                    Integer val = res[x][y - 1];
//                    if (val == null) {
//                        val = search( visited, res, x, y - 1, m, n);
//                    }
//                    sum += val;
//                }
//            }
            visited[x][y] = false;

            return sum;

        }

        /*
        *观察从左上角到右下角的各条路径，它们都有个共同的特点，
        * 向右走的步数都是一样的（stepRight = m-1步），向下走的步数也都是一样的（stepDown = n-1步），
        * 总步数stepAll = stepRight + stepDown——因此问题就变成了一个组合问题，
        * 即从stepAll的总步数里面，选出stepRight步来，共有几种选择的方法——C(stepAll, stepRight) = C(m+n-2, m-1)。
        * 然后再根据组合公式 C(a,b) = a!/(b!*(a-b)!) 求解。
        *
        *
         */
        public static class RangeSolution {
            public int uniquePaths(int m, int n) {
                // Compute C((m-1)+(n-1), (m-1))
                m--;
                n--;
                int mn = m + n;
                double ans = 1;
                for (int i = 0; i < m; i++)
                    ans = ans * ((double) (mn - i) / (m - i));
                return (int) Math.round(ans);
            }
        }
    }

}
