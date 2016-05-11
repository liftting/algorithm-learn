package middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/10.
 * 39. Combination Sum
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 * <p/>
 * 1,没有重复，排序的
 * 解决：
 * 1，回溯
 * 2，要考虑几点特别的，判断时，因为可以重复选择当前的元素，那么不能+1操作，  还有判断是要区间判断，
 */
public class CombinationsSum {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showList(s.combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {

            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            search(result, new ArrayList<Integer>(), candidates, 0, target);
            return result;
        }

        public void search(List<List<Integer>> result, List<Integer> list, int[] nums, int index, int target) {
            if (target <= 0) { // 这里判断不能用是否等于目标，要判断好区间，因为重复选择时，会不断增加的，导致一直不等于
                if (target == 0) {
                    List<Integer> newList = new ArrayList<Integer>(list);
                    result.add(newList);
                }
                return;
            }

            for (int i = index; i < nums.length; i++) {
                //因为元素选择时，是可以重复的，所以
                List<Integer> p = new ArrayList<Integer>(list);
                p.add(nums[i]);
                //下面的递归时， i 是没有向前添加的， 不断添加当前，直到不符合条件，
                search(result, p, nums, i, target - nums[i]);
            }

        }

    }

}
