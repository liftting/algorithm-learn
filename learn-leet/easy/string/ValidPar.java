package easy.string;

import java.util.Stack;

/**
 * Created by wm on 16/3/25.
 * <p/>
 * 20. Valid Parentheses
 * ()[]{}
 * ([])
 * ((  false
 */
public class ValidPar {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.print(s.isValid("(("));
    }

    public static class Solution {
        public boolean isValid(String s) {
            if (s == null || s.length() == 0) return false;


            String str = s.trim();

            if (str.length() % 2 > 0) return false;

            Stack<Character> stack = new Stack<Character>();

            int i = 0;
            while (i < str.length()) {
                char item = str.charAt(i);
                char other = getOther(item);
                if (other > 0) { //当前添加的是右边的
                    //右边
                    if (stack.isEmpty()) return false;

                    Character top = stack.pop();
                    if (top == null || top != other) return false;

                } else {
                    stack.push(item);
                }
                i++;
            }

            //如果还有，说明左边的字符还是存在到堆栈中了，
            if (!stack.isEmpty()) return false;


            return true;

        }

        private char getOther(char c) {
            if (c == ')') return '(';
            if (c == ']') return '[';
            if (c == '}') return '{';
            return 0;
        }

    }


}
