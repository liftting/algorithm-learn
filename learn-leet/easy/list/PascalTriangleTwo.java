package easy.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm on 16/3/23.
 * 119. Pascal's Triangle II
 * <p/>
 * <p/>
 * <p/>
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3, Return [1,3,3,1].
 * 返回第几行的杨辉三角，从0开始计数
 * <p/>
 * <p/>
 * <p/>
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalTriangleTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<Integer> data = s.getRow(3);
        System.out.print("[");
        for (Integer i : data) {
            System.out.print(i + ",");
        }
        System.out.println("]");
    }

    public static class Solution {

        public List<Integer> getRow(int rowIndex) {
            List<Integer> data = new ArrayList<Integer>();

            if (rowIndex < 0) return data;

            int ctRowIndex = rowIndex + 1;

            if (ctRowIndex == 1) return getFirst();

            List<Integer> two = getSecond();
            List<Integer> old = two;

            for (int i = 3; i <= ctRowIndex; i++) {
                List<Integer> createList = new ArrayList<Integer>();
                for (int k = 0; k < i; k++) {
                    if (k == 0 || k == i - 1) {
                        createList.add(1);
                    } else {
                        createList.add(old.get(k - 1) + old.get(k));
                    }
                }

                old = createList;
            }

            return old;

        }

        private List<Integer> getFirst() {
            List<Integer> first = new ArrayList<Integer>();
            first.add(1);
            return first;
        }

        private List<Integer> getSecond() {
            List<Integer> second = new ArrayList<Integer>();
            second.add(1);
            second.add(1);
            return second;
        }
    }

}
