package middle.numoperation;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/30.
 * 338. Counting Bits
 * For num = 5 you should return [0,1,1,2,1,2].  0-num
 * 比5小的数， 然后二进制中包含几个一
 * 5 -- 0，1，2，3，4，5
 * <p/>
 * 时间：O（n）空间 should be O(n).
 */
public class CountBits {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.countBits(0));
        CommonUtil.show(s.countBits(1));
        CommonUtil.show(s.countBits(10));
    }

    /**
     * 暴力，
     * 遍历， 每个计算二进制  O(n2)
     */
    public static class Solution {
        public int[] countBits(int num) {
            int[] result = new int[num + 1];

            for (int i = 0; i <= num; i++) {
                result[i] = getOneBit(i);
            }

            return result;
        }

        private int getOneBit(int n) {
            int count = 0;
            while (n != 0) {
                count++;
                n ^= n & ~(n - 1);
            }
            return count;
        }
    }

}
