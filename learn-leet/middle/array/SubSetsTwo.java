package middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/16.
 * 90. Subsets II
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * <p/>
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * <p/>
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * <p/>
 * 1,包含重复元素，去重，
 */
public class SubSetsTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showList(s.subsetsWithDup(new int[]{1, 2, 2}));
    }

    public static class Solution {

        public List<List<Integer>> subsetsWithDup(int[] nums) {

            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (nums == null || nums.length <= 0) return result;

            Arrays.sort(nums);

            List<Integer> list = new ArrayList<Integer>();
            search(nums, 0, list, result);

            return result;
        }

        private void search(int[] nums, int pos, List<Integer> list, List<List<Integer>> result) {

            result.add(list);
            for (int i = pos; i < nums.length; i++) {
                //去重，
                if (i > pos && nums[i] == nums[i - 1]) {
                    continue;
                }
                List<Integer> p = new ArrayList<Integer>(list);
                p.add(nums[i]);
                search(nums, i + 1, p, result);
            }


        }
    }

}
