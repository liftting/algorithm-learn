package easy.string;

import java.util.Stack;

import easy.CommonUtil;

/**
 * Created by wm on 16/7/21.
 * <p/>
 * 38. Count and Say
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * <p/>
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * 解法：
 * 1，从后向前
 */
public class CountAndSay {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.countAndSay(111221));
    }

    public static class Solution {
        public String countAndSay(int n) {
            int old = n % 10;
            n = n / 10;
            int t = -1;
            Stack<Integer> stack = new Stack<Integer>();
            int csize = 1;
            while (n > 0) {
                t = n % 10;
                n = n / 10;
                if (t == old) {
                    csize++;
                } else {

                    stack.push(old);
                    stack.push(csize);

                    old = t;
                    csize = 1;
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.empty()) {
                sb.append(stack.pop());
            }
            return sb.toString();
        }


    }


}
