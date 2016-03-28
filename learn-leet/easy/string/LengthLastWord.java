package easy.string;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/28.
 * 58. Length of Last Word
 */
public class LengthLastWord {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.lengthOfLastWord("a "));
    }

    public static class Solution {
        public int lengthOfLastWord(String s) {
            String []sts = s.split(" +");
            int len = sts.length;
            if(len<1) return 0;
            return sts[len-1].length();
        }
    }

}
