package easy.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wm on 16/3/28.
 * <p/>
 * 205. Isomorphic Strings
 * Given "egg", "add", return true.
 * <p/>
 * <p/>
 * Given "foo", "bar", return false.
 * <p/>
 * Given "paper", "title", return true.
 */
public class IsomorphicStr {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.print(s.isIsomorphic("ab", "aa"));
    }

    public static class Solution {
        public boolean isIsomorphic(String s, String t) {
            if (s == null || t == null) return false;

            Map<Character, Integer> sMap = new HashMap<Character, Integer>();
            Map<Character, Integer> tMap = new HashMap<Character, Integer>();
            for (int i = 0; i < s.length(); i++) {
                Character sc = s.charAt(i);
                Character tc = t.charAt(i);
                if (!sMap.containsKey(sc)) {
                    sMap.put(sc, i);
                } else {
                    //
                    boolean isContain = check(t, i, sMap.get(sc));
                    if (!isContain) return false;
                }

                if (!tMap.containsKey(tc)) {
                    tMap.put(tc, i);
                } else {
                    boolean isContain = check(s, i, tMap.get(tc));
                    if (!isContain) return false;
                }
            }
            return true;
        }

        private boolean check(String s, int i, int j) {
            if (s.charAt(i) == s.charAt(j)) return true;
            return false;
        }

    }

}
