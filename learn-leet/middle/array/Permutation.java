package middle.array;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/26.
 * <p/>
 * Given a collection of distinct numbers, return all possible permutations.
 * <p/>
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * <p/>
 * 全排列
 * 46. Permutations
 * 1，递归  同质问题
 */
public class Permutation {


    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showList(s.permute(new int[]{1, 2, 3}));
    }

    public static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();

            if (nums == null || nums.length == 0) return result;

            search(result, nums, 0, nums.length - 1);
            return result;
        }

        public void search(List<List<Integer>> result, int[] nums, int from, int to) {

            if (from == to) {
                List<Integer> list = new ArrayList<Integer>();
                for (int i : nums) {
                    list.add(i);
                }

                result.add(list);
                return;
            }

            for (int i = from; i <= to; i++) {

                swap(nums, from, i);

                //子集，
                search(result, nums, from + 1, to);

                // 交换回来，
                swap(nums, from, i);

            }

        }

        public void swap(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
    }


}
