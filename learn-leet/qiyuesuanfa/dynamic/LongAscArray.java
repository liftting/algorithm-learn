package qiyuesuanfa.dynamic;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/8.
 * 在一个无序的序列a1,a2,.....,am里，找到一个最长的序列，满足ai<=aj...<=ak; 且i<j<k;
 * 最长非降 子序列 问题
 * <p/>
 * lis
 * 最长单增子序列
 * 10 4 20 10 15 13
 * 4 10 15  {4 10 13} 这两个都是解法
 * <p/>
 * f(i) = 第i项的最长子序列
 * f(0) = 0
 * f(i) = max{f(x) | x<i & ai>ax} + 1
 * <p/>
 * 1 只与x有关
 */
public class LongAscArray {

    public static void main(String[] args) {
        search(new int[]{10, 4, 20, 10, 15});
    }

    public static int search(int[] nums) {
        if (nums == null || nums.length <= 0) return 0;
        int data[] = new int[nums.length + 1];
        data[0] = 1;

        int sum = 1;
        int index = 0;

        //时间复杂度 O(n2) 空间复杂度
        for (int i = 1; i < nums.length; i++) {
            data[i] = 1;
            int max = data[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    if (data[j] + 1 > max) {
                        max = data[j] + 1;
                    }
                }
            }

            data[i] = max;

            if (data[i] > sum) {
                sum = data[i];
                index = i;
            }
        }

        CommonUtil.show(sum);
        CommonUtil.show(index);

        return sum;


    }

}
