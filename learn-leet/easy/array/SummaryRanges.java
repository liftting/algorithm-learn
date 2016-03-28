package easy.array;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/28.
 * 228.Summary Ranges
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * 连续的数字，
 * <p/>
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class SummaryRanges {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.summaryRanges(new int[]{0, 1, 2, 3, 5, 7, 8}));
    }

    public static class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> list = new ArrayList<String>();
            if (nums == null || nums.length == 0) return list;

            if (nums.length == 1) {
                list.add("" + nums[0]);
                return list;
            }

            int start = 0;
            int end = 0;
            int pre = nums[0];

            for (int i = 1; i < nums.length; i++) {
                int cur = nums[i];
                end = i;
                if (cur > pre + 1) {
                    end = end - 1;
                    String s = "";
                    if (start == end) {
                        s += nums[start];
                    } else {
                        s += nums[start] + "->" + nums[end];
                    }

                    list.add(s);

                    start = i;
                    end = i;
                }
                pre = cur;

            }
            //到结束了，但是是连贯的，要显示
            if (end - start >= 0) {
                String s = "";
                if (start == end) {
                    s += nums[start];
                } else {
                    s += nums[start] + "->" + nums[end];
                }
                list.add(s);
            }

            return list;
        }
    }

}
