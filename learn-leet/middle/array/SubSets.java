package middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/24.
 * <p/>
 * 78. Subsets
 * If nums = [1,2,3], a solution is:
 * <p/>
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * <p/>
 * 取数据集合， 取，或不取，
 * 按顺序，
 * 1,递归，添加 ，不添加
 */
public class SubSets {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showList(s.subsetsOther(new int[]{1, 2, 2}, 0, 3));
    }

    public static class Solution {
        public List<List<Integer>> subsets(int[] nums) {

            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (nums == null || nums.length <= 0) return result;

            Arrays.sort(nums);

            Stack<Integer> stack = new Stack<Integer>();
            search(nums, 0, stack, result);

            return result;
        }

        private void search(int[] nums, int pos, Stack<Integer> list, List<List<Integer>> result) {

            if (pos == nums.length) {
                List<Integer> data = new ArrayList<Integer>(list);
                result.add(data);
                return;
            }

            list.push(nums[pos]);

            search(nums, pos + 1, list, result);

            list.pop();
            search(nums, pos + 1, list, result);

        }


        //===
        public List<List<Integer>> subsetsOther(int[] nums, int idx, int n) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (idx == n) {
                List<Integer> temp = new ArrayList<Integer>();
                result.add(temp);
            } else {
                //递归到子集
                List<List<Integer>> dataList = subsetsOther(nums, idx + 1, n);
                int a = nums[idx];
                for (int i = 0; i < dataList.size(); i++) {
                    //子集
                    List<Integer> v = dataList.get(i);
                    result.add(v); // 不添加当前元素，直接添加

                    List<Integer> addList = new ArrayList<Integer>(v);
                    addList.add(a); // 添加元素，add a
                    //要排序
                    Collections.sort(addList);
                    result.add(addList);
                }
            }
            return result;
        }
    }


}
