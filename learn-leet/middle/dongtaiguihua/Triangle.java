package middle.dongtaiguihua;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;
import easy.list.ListNodeFactory;

/**
 * Created by wm on 16/4/13.
 * <p/>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p/>
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * <p/>
 * o(n) 每次移动到相邻的下一个行的元素中，
 * <p/>
 * 1, 递归，每次选择附近元素
 * 2,贪心，每次选取最小的，  可能会出差错 ([[-1],[2,3],[1,-1,-3]] 数据这种情况时， 其实最优解是-1 3 + -3 最小，)
 * 3, 动态规划，
 */
public class Triangle {

    public static void main(String[] args) {
//        TanSolution s = new TanSolution();
        DpSolution s = new DpSolution();
        CommonUtil.show(s.minimumTotal(ListNodeFactory.buildTwoList()));
    }

    public static class TanSolution {
        public int minimumTotal(List<List<Integer>> triangle) {


            if (triangle == null || triangle.size() <= 0) return 0;

            //
            List<Integer> one = triangle.get(0);
            int min = Integer.MAX_VALUE;
            int ct = -1;
            for (int i = 0; i < one.size(); i++) {
                if (one.get(i) < min) {
                    min = one.get(i);
                    ct = i;
                }
            }

            int data = search(1, ct, triangle) + min;

            return data;
        }

        public int search(int rindex, int cindex, List<List<Integer>> triangle) {
            int sum = 0;

            if (rindex >= triangle.size()) return sum;

            List<Integer> data = triangle.get(rindex);
            int min = Integer.MAX_VALUE;
            int ct = 0;
            int i = (cindex - 1) < 0 ? 0 : cindex - 1;
            for (; i <= cindex + 1 && i >= 0; i++) {
                if (data.get(i) < min) {
                    min = data.get(i);
                    ct = i;
                }
            }

            sum += min;
            sum += search(rindex + 1, ct, triangle);

            return sum;

        }

    }

    /**
     * 法一：从上到下， 下一行的结果根据上一行的路径累计和而计算。
     * triangle[i][j] += min(triangle[i-1][j-1],triangle[i-1][j] ) ，这样需要处理j=0和j=最大值。  计算本行时，都是拿上一行的数据，即之前的数据计算
     * 法二：从下往上，每一行的结果根据下面一行的路基累计和而计算。（参考大神才晓得）
     * triangle[i][j] += min(triangle[i + 1][j], triangle[i + 1][j + 1])
     * <p/>
     * copy from net
     */
    public static class DpSolution {
        public int minimumTotal(List<List<Integer>> triangle) { // 可以通过， 那么我上面构造的数据是有问题的，可能之前的里面填充有别的数据
            List<Integer> dp = new ArrayList<Integer>(); //存储的上一次
            int minTotal = Integer.MAX_VALUE;

            //init first
            for (int i = 0; i < triangle.size(); i++) {
                List<Integer> list = triangle.get(i);
                List<Integer> create = new ArrayList<Integer>(); // 用来存储这一次

                for (int j = 0; j < list.size(); j++) {
                    int left = Integer.MAX_VALUE;
                    if (j != 0) {
                        left = dp.get(j - 1) + list.get(j);
                    }

                    int right = Integer.MAX_VALUE;
                    if (j != list.size() - 1) // 因为下面数组长度比上一个数组长度大于1，所以这里到最后时，不从上次的取，即，j-1 j就可以啦
                        right = dp.get(j) + list.get(j);

                    int min;
                    if (list.size() == 1)
                        min = list.get(0);
                    else
                        min = left < right ? left : right;

                    if (i == triangle.size() - 1) {
                        //最后一次
                        if (min < minTotal) {
                            minTotal = min;
                        }
                    } else {
                        create.add(min);
                    }

                }

                dp = create;

            }

            return minTotal;
        }
    }

}
