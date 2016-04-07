package middle.numoperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/7.
 * 18. 4Sum
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 */
public class FourSum {

    public static void main(String[] args) {
        OtherSolution s = new OtherSolution();
        CommonUtil.showList(s.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    public static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();

            if (nums == null || nums.length <= 0) return result;

            Arrays.sort(nums);

            boolean[] flag = new boolean[nums.length];
            search(nums, flag, 0, 0, target, result);
            return result;
        }

        public void search(int data[], boolean flag[], int index, int cur, int total, List<List<Integer>> result) {

            if (index >= data.length) return;

            //查找到一个继续寻找其他的最优解
            if (cur + data[index] == total) {
                flag[index] = true;
                show(data, flag, result);
                flag[index] = false;
            }

            flag[index] = true;
            search(data, flag, index + 1, cur + data[index], total, result);
            flag[index] = false;
            search(data, flag, index + 1, cur, total, result);

        }

        public void show(int data[], boolean flag[], List<List<Integer>> result) {
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < data.length; i++) {
                if (flag[i]) {
                    list.add(data[i]);
                }
            }
            if (list.size() == 4) {
                result.add(list);
            }
        }
    }


    //循环非递归
    public static class OtherSolution {
        public List<List<Integer>> fourSum(int[] nums, int target) {

            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 4) return result;

            Arrays.sort(nums);

            int len = nums.length;
            int left;
            int right;
            int sum;
            // -4 -1 -1 0 1 2
            for (int i = 0; i <= len - 3; i++) {

                if (i != 0 && nums[i] == nums[i - 1])
                    continue;

                for (int j = i + 1; j <= len - 2; j++) {
                    if (j != i + 1 && nums[j] == nums[j - 1])
                        continue;

                    left = j + 1; // 因为i已经作为标准了，所以left向前 添加即可  这要能避免耗时处理
                    right = len - 1;

                    //都从开头，知道数组结尾
                    while (left < right) {
                        if (left != j + 1 && nums[left] == nums[left - 1]) {
                            left++;
                            continue;
                        }
                        if (right != nums.length - 1 && nums[right] == nums[right + 1]) {
                            right--;
                            continue;
                        }
                        sum = nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target) {
                            List<Integer> item = new ArrayList<Integer>();
                            item.add(nums[i]);
                            item.add(nums[j]);
                            item.add(nums[left]);
                            item.add(nums[right]);
                            result.add(item);

                            left++;
                            right--;
                        } else if (sum > target) {
                            right--;
                        } else {
                            left++;
                        }
                    }
                }
            }

            return result;
        }
    }

}
