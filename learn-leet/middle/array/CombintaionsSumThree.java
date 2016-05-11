package middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/11.
 * 216. Combination Sum III
 * Input: k = 3, n = 9
 * <p/>
 * Output:
 * <p/>
 * [[1,2,6], [1,3,5], [2,3,4]]
 * <p/>
 * 1,given that only numbers from 1 to 9 can be used 就是给的数只能使用 1- 9
 * 2, 回溯要特殊处理，避免超时，
 */
public class CombintaionsSumThree {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showList(s.combinationSum3(2, 18));
    }

    public static class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {

            List<List<Integer>> result = new ArrayList<List<Integer>>();
            search(result, new ArrayList<Integer>(), 1, 0, n, k);
            return result;
        }

        public void search(List<List<Integer>> result, List<Integer> list, int index, int total, int target, int size) {
            if (list.size() == size && total == target) {
                List<Integer> newList = new ArrayList<Integer>(list);
                result.add(newList);
                return;
            }

            // 要判断是否继续为起始点，

            for (int i = index; i <= target; i++) {
                if (i <= 9 && list.size() < size && total + i <= target) { //避免重复递归操作
                    list.add(i);
                    search(result, list, i + 1, total + i, target, size);
                    list.remove(list.size() - 1);
                }
            }

        }

    }

}
