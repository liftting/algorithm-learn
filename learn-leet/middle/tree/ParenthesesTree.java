package middle.tree;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/5.
 * 22. Generate Parentheses
 * <p/>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p/>
 * For example, given n = 3, a solution set is:
 * <p/>
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * <p/>
 * todo:
 * 二叉树结构处理，
 * (
 * ((       ()
 * <p/>
 * 左边添加左括号，  右边添加 右括号处理
 * 什么时候添加左边括号：可以先一直添加，到n
 * <p/>
 * 什么时候添加右括号呢？当左括号个数大于右括号的个数时添加右括号
 * <p/>
 * 地推方案，参考图片
 */
public class ParenthesesTree {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.generateParenthesis(1));
    }

    public static class Solution {
        /**
         * 穷举法，方式不合理，没有具体解决方案
         * tree, -
         *
         * @param n
         * @return
         */
        public List<String> generateParenthesis(int n) {
            List<String> dataList = new ArrayList<String>();
            if (n <= 0) return dataList;
            search(1, 1, n, "", dataList);
            return dataList;
        }

        public void search(int left, int right, int n, String s, List<String> data) {

            if (left > n && right > n) {
                data.add(s);
                return;
            }

            if (left <= n) {
                search(left + 1, right, n, s + "(", data);
            }

            if (right <= n && left > right) {
                search(left, right + 1, n, s + ')', data);
            }

        }
    }

}
