package middle.array;

/**
 * Created by wm on 16/3/30.
 * 136. Single Number
 * <p/>
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * 一个array， 每个数字都出现两次， 只有一个出现一次，find
 * <p/>
 * 线性时间， 不使用额外存储
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.singleNumber(new int[]{1, 5, 9, 8, 9, 5, 1});
    }

    /**
     * 1,排序，
     * 2，map存储
     * <p/>
     * 3,利用亦或  规则：一个数异或本身恒等于0，如5^5恒等于0；
     * a^b^c^d ^c^b^a = d
     * a^a=0;
     * result of 1^2^3^4^5^1^2^3^4^5^6 is the same as 1^1^2^2^3^3^4^4^5^5^6.
     */
    public static class Solution {
        public int singleNumber(int[] nums) {
            if (nums == null) return -1;
            int total = 0;
            for (int x : nums) {
                total ^= x;
//                System.out.println("x:" + x + "-total:" + total);
            }
            return total;
        }
    }

}
