package easy.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm on 16/3/23.
 * <p/>
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> data = s.generate(2);
        for (List<Integer> li : data) {
            System.out.print("[");
            for (Integer i : li) {
                System.out.print(i + ",");
            }
            System.out.println("]");
        }
    }

    public static class Solution {
        public List<List<Integer>> generate(int numRows) {
            //
            List<List<Integer>> dataList = new ArrayList<List<Integer>>();
            if (numRows <= 0) return dataList;

            List<Integer> one;
            if (numRows == 1) {
                one = getFirst();
                dataList.add(one);
                return dataList;
            }

            one = getFirst();
            List<Integer> two = getSecond();
            List<Integer> old = two;
            dataList.add(one);
            dataList.add(two);

            for (int i = 3; i <= numRows; i++) {
                List<Integer> createList = new ArrayList<Integer>();
                for (int k = 0; k < i; k++) {
                    if (k == 0 || k == i - 1) {
                        createList.add(1);
                    } else {
                        createList.add(old.get(k - 1) + old.get(k));
                    }
                }
                dataList.add(createList);

                old = createList;

            }

            return dataList;

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
