package easy.numoperation;

/**
 * Created by wm on 16/3/21.
 * <p/>
 * 326. Power of Three
 * <p/>
 * 判断一个数是否是3 的几次方，不使用循环和递归 即时间复杂度是 O(1)
 */
public class PowerThree {

    public static void main(String[] args) {



    }


    public class Solution {
        /**
         * 计算出最大值， copy from net
         *
         * @param n
         * @return
         */
        public boolean isPowerOfThree(int n) {
            // 1162261467 is 3^19,  3^20 is bigger than int
            int maxThreeInt = 1162261467;
            return (n > 0 && maxThreeInt % n == 0);
        }
    }

}
