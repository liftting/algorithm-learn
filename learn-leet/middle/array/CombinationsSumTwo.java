package middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/10.
 * 40. Combination Sum II
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * <p/>
 * Each number in C may only be used once in the combination.
 * <p/>
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 * <p/>
 * 1, 每个元素只能使用一次
 * 2, 元素有重复，会导致获取时，出现重复，
 */
public class CombinationsSumTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showList(s.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {

            Arrays.sort(candidates);

            List<List<Integer>> result = new ArrayList<List<Integer>>();
            search(result, new ArrayList<Integer>(), candidates, 0, 0, target);
            return result;
        }

        public void search(List<List<Integer>> result, List<Integer> list, int[] nums, int index, int total, int target) {
            if (total == target) {
                List<Integer> newList = new ArrayList<Integer>(list);
                result.add(newList);
                return;
            }

            for (int i = index; i < nums.length; i++) {

                if (i > index && nums[i] == nums[i - 1]) continue; //这里就保证了重复作为起点
                int item = nums[i];
                if (total + item <= target) { // 避免重复的递归，
                    list.add(item);
                    search(result, list, nums, i + 1, total + item, target);
                    list.remove(list.size() - 1);
                }
            }

        }

    }

}
