package middle.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/17.
 * <p/>
 * 187. Repeated DNA Sequences
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * <p/>
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * <p/>
 * For example,
 * <p/>
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * <p/>
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * <p/>
 * 1,=10 子字符串
 * <p/>
 * so:
 * 1,暴力解决遍历 ，注意结果去重，hash
 */
public class RepeatedDNA {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        CommonUtil.show(s.findRepeatedDnaSequences("AAAAAAAAAAAA"));
    }

    public static class Solution {
        public List<String> findRepeatedDnaSequences(String s) {

            HashMap<String, Boolean> map = new HashMap<String, Boolean>();
            HashSet<String> data = new HashSet<String>();//结果去重的

            List<String> result = new ArrayList<String>();

            if (s == null || s.length() <= 0) return result;

            int l = 10;
            for (int i = 0; i <= s.length() - 10; i++) {
                int endPos = i + l;
                String sub = s.substring(i, endPos);
                if (map.get(sub) != null) {
                    data.add(sub);
                } else {
                    map.put(sub, true);
                }
            }

            result.addAll(data);

            return result;

        }
    }


}
