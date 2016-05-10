package middle.array;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/10.
 * 77. Combinations
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p/>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * <p/>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * 求 C(n, k) 的所有组合。
 * <p/>
 * <p/>
 * 1,取或不取， 回溯，
 * <p/>
 * 流程：
 * 1，元素开头，
 * 2，递归，向后取，或不取
 */
public class Combinations {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showList(s.combine(4, 2));
    }

    public static class Solution {

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        public List<List<Integer>> combine(int n, int k) {

            search(new ArrayList<Integer>(), 1, n, k);
            return result;

        }

        public void search(List<Integer> list, int index, int n, int k) {
            if (list.size() == k) {
                List<Integer> newList = new ArrayList<Integer>(list);
                result.add(newList);
                return;
            }

            for (int i = index; i <= n; i++) {
                list.add(i);
                search(list, i + 1, n, k);
                list.remove(new Integer(i)); // 直接int 会被当成位置，可以转换对象，或最后一个元素，list.remove(comb.size()-1);
            }

        }
    }

}
