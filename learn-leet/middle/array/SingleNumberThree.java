package middle.array;

import java.util.HashMap;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/30.
 * 260. Single Number III
 * 线性时间， 使用常数的存储空间来处理
 * 有数字出现两次
 */
public class SingleNumberThree {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.singleNumber(new int[]{1, 2, 3, 3, 2, 1, 4, 5}));
    }

    public static class Solution {
        //最基本的处理方式，
        public int[] singleNumber(int[] nums) {
            if (nums.length == 2) {
                return nums;
            }
            //
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            int[] result = new int[2];
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    map.put(nums[i], 2);
                } else {
                    map.put(nums[i], 1);
                }
            }

            int j = 0;
            for (int i = 0; i < nums.length; i++) {
                if (map.get(nums[i]) != 2) {
                    result[j++] = nums[i];

                }
            }
            return result;

        }


    }

    /**
     * 如果是只有一个数只出现一次，那可以用异或大法来解决，但是现在有两个数，就要动点脑筋了。
     * <p/>
     * 首先，使用异或大法可以找得到这两个数异或以后的值（非零），这个值取名为xor；
     * 其次，假如说我们有一个掩码mask，能够给数分类，让每个数都去和这个掩码进行与操作，得出两种结果（两个组），并且，使得这两个特殊的数，分别落在这两种结果里面，那么，就可以对么个group分别进行异或大法来得出结果了。
     * 现在来考虑一下怎么构造这个掩码。
     * <p/>
     * 看前面说的那个值xor，如果xor大于0，说明符号位是0，那么这两个特殊的数是同号的；反之则是异号的：
     * <p/>
     * 先考虑异号的情况，这种情况最好处理，我只要令掩码为Integer.MIN_VALUE，即“负零”——符号位为1，其它位都为0，那么，我肯定可以保证两个异号的数去与这个掩码的时候，会落到不同的结果里面——负数与上掩码会得到Integer.MIN_VALUE，而正数或零与上掩码会得到0；
     * 在考虑同号的情况，这种情况要动点小脑筋：不断拿xor去除以2，直到发现除不尽位置，这个时候说明在第i位上是1，也就是说在这一位上面这两个数是不同的，那么就构造一个每一位上全为0，但是第i位上为1的数作为掩码，这个掩码可以保证在和那两个数分别进行与运算的时候，会得到不同的结果，一个是0，一个不为0（第i位上为1）。
     * 好了，有了这个掩码，就可以把两个数分别扔到两个组里，再对每个组内部使用异或大法，就能得出每个组内特殊的那个数，这两个组的特殊数合起来就是所求。这个题目还是很有意思的。
     */
    public static class OtherSolution {
        public int[] singleNumber(int[] nums) {
            if (nums == null || nums.length == 0)
                return new int[]{};
            if (nums.length == 1)
                return new int[]{nums[0]};

            // get the xor result of the two single numbers
            int xor = 0;
            for (int num : nums)
                xor ^= num;

            // divide the numbers into two groups, get the mask to calculate which group will each number be put in
            int mask = 0;
            if (xor == 0) {
                return new int[]{};
            } else if (xor > 0) {
                for (int i = 1; i < 32; i++) {
                    int remainder = xor % 2;
                    if (remainder > 0) {
                        mask = 1 << (i - 1);
                        break;
                    }

                    xor = xor / 2;
                }
            } else {
                mask = Integer.MIN_VALUE;
            }

            // num1 is the xor result for group 1, and num2 is for group2
            int num1 = 0;
            int num2 = 0;
            for (int num : nums) {
                int group = num & mask;
                if (group == 0)
                    num1 ^= num;
                else
                    num2 ^= num;
            }

            return new int[]{num1, num2};
        }
    }

}
