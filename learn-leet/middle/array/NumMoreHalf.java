package middle.array;

import java.util.Arrays;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/26.
 * 重复数字超过一半
 * <p/>
 * 题目：数组中有一个数字出现的次数超过了数组长度的一半，找出这个数字
 * from： 编程之法中的问题
 * <p/>
 * 解法：
 * 1，快速排序， 然后直接定位
 * 2，hash表， 以空间换取时间，
 * 3，如果每次删除两个不同的数（不管是不是我们要查找的那个出现次数超过一半的数字），
 * 那么，在剩下的数中，我们要查找的数（出现次数超过一半）出现的次数仍然超过总数的一半。
 * 通过不断重复这个过程，不断排除掉其它的数，最终找到那个出现次数超过一半的数字。这个方法，免去了排序，也避免了空间O(n)的开销，
 * 总得说来，时间复杂度只有O(n)，空间复杂度为O(1)，貌似不失为最佳方法。
 * <p/>
 * 即：每次删除两个 （不同 ）数， 出现的次数依旧超过一半，
 * <p/>
 * 1 1 2 0 1 3 1 --》 1 0 1 3 1 --》 1 3 1 --》 1
 * <p/>
 * <p/>
 * 4，问题特殊性的分析，
 * 更进一步，考虑到这个问题本身的特殊性，
 * 我们可以在遍历数组的时候保存两个值：一个candidate，用来保存数组中遍历到的某个数字；一个nTimes，表示当前数字的出现次数，其中，nTimes初始化为1。当我们遍历到数组中下一个数字的时候：
 * <p/>
 * 如果下一个数字与之前candidate保存的数字相同，则nTimes加1；
 * 如果下一个数字与之前candidate保存的数字不同，则nTimes减1；
 * 每当出现次数nTimes变为0后，用candidate保存下一个数字，并把nTimes重新设为1。
 * 直到遍历完数组中的所有数字为止。
 */
public class NumMoreHalf {


    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.getTwo(new int[]{1, 2, 3, 2, 4, 2, 2, 5, 2, 6, 2, 7, 2}));
    }

    public static class Solution {

        /**
         * 排序，快排，
         * 中间那个直接定位就是， O(nlongn + 1)
         *
         * @param nums
         * @return
         */
        public int getOne(int[] nums) {
            Arrays.sort(nums);

            int len = nums.length;
            return nums[len];

        }

        public int getTwo(int[] nums) {
            int ct = 0;
            int data = -1;
            for (int i = 0; i < nums.length; i++) {
                if (ct == 0) {
                    data = nums[i];
                    ct = 1;
                } else {
                    if (data == nums[i]) {
                        ct++;
                    } else {
                        ct--;
                    }
                }
            }

            return data;
        }

    }

}
