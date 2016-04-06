package middle.dongtaiguihua;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/5.
 * <p/>
 * 环形道路， 有N 个gas ,
 * 每个能添加的油量是 gas[i]
 * <p/>
 * 每次从i 到 i+1 ，消耗的 是cost[i]
 * <p/>
 * 查找到哪个点，能够周游一次
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * <p/>
 * === 优化点：
 * 回过头来看一下问题，冠冕堂皇地搞了一个gas数组，又搞了一个cost数组，其实，对于环上的每一个点i而言，我关心的只有gas[i]-cost[i]而已，我把它定义为diff[i]。
 * <p/>
 * 1. 显而易见的一点是，假设sum(p,q)表示从diff[p]到diff[q]这些项求和，
 * 那么如果p为0，q为n-1，即所有的数之和如果小于零的话，sum(0, n-1) = ∑diff[i]<0（i∈[0, n-1]），根本是无法做到有一点让轿车跑一圈的。
 * <p/>
 * 那么对于这个和大于0的情况，如何找到这一个出发点呢？
 * <p/>
 * 2. 这个出发点假设是k的话，我可以说，那么对于k前面的任意一点p，sum(p,k-1)必须满足sum(p,k-1)<0，
 * 否则的话，一定可以从[p,k-1]中找到一点，作为新的出发点，这个出发点可以使得也一样可以成功绕一圈，
 * 而注意到题中提示，“The solution is guaranteed to be unique.”，既然只有一个解，那么这个点就只能不存在了。
 * 所以可以得到：对于k前面的任意一点p，sum(p,k-1)必须满足sum(p,k-1)<0，现在取p=0，即所有的sum(0,k-1)全部小于零。
 * <p/>
 * 3. 再来看k后面的情况，对于k后面的任意一点q，一定有sum(k+1,q)≥0，否则的话，从k+1到q就走不了了。
 */
public class GasStation {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.canCompleteCircuit(new int[]{1, 2}, new int[]{2, 1}));
    }

    public static class Solution {
        /**
         * 最基本的暴力查找， 时间复杂度 O(n2)
         *
         * @param gas
         * @param cost
         * @return
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            for (int start = 0; start < gas.length; start++) {
                int g = 0;
                int i = start;
                boolean flag = false;

                //基于当前位置进行查找
                do {
                    if (flag && i == start) return start;

                    flag = true;
                    g += gas[i];
                    g -= cost[i];

                    if (i == gas.length - 1) {
                        i = 0; // 到头
                    } else {
                        i++;
                    }

                } while (g >= 0);

            }

            return -1;
        }

    }
}
