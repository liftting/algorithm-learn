package middle.dongtaiguihua;

import java.util.HashSet;
import java.util.Set;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/6.
 * 139. Word Break
 * 动态规划，对于字符串[0,i)取子串，如果子串[0,k)存在解，而[k,i)又在字典里面的话，那么[0,i)就是有解的
 * <p/>
 * s = "leetcode",
 * dict = ["leet", "code"].
 * <p/>
 * Return true because "leetcode" can be segmented as "leet code".
 * <p/>
 * DP
 * <p/>
 * DFS 深度搜索:
 * 技巧是，如果前面一部分符合条件，继续移动游标到下一个起始位置，在进行搜索，都满足就符合条件
 */
public class WordBreaker {

    public static void main(String[] args) {
        Solution s = new Solution();
        Set<String> dic = new HashSet<String>();
        dic.add("leet");
        dic.add("code");
        s.wordBreak("leetcode", dic);
    }

    public static class Solution {
        public boolean wordBreak(String s, Set<String> wordDict) {
            if (s == null || s.isEmpty()) {
                return false;
            }
            boolean[] visited = new boolean[s.length() + 1];

            return search(s, visited, 0, wordDict);


        }

        // pos 第一个指针
        private boolean search(String s, boolean[] visited, int pos, Set<String> wordDict) {
            if (pos == s.length()) return true;

            visited[pos] = true;

            for (int i = pos + 1; i <= s.length(); i++) {
                //包含第一个，然后，i作为第一个位置，继续进行索引查询
                // contains  这个做为第一个条件来过滤
                if (!visited[i] && wordDict.contains(s.substring(pos, i))) {
//                    CommonUtil.show(s.substring(pos, i));
                    if (search(s, visited, i, wordDict)) {
                        return true;
                    }
                }
            }
            return false;

        }
    }

    // DP 动态规划
    public class OtherSolution {

        public boolean wordBreak(String s, Set<String> dict) {
            // boolean数组mem[i]表示[0,i)是否存在通路（0<=i<=str.length）
            boolean[] mem = new boolean[s.length() + 1];
            mem[0] = true;

            // mem[i] = {所有 mem[k] && ([k,i)∈dict) 中只要有一个为true，就为true}（其中0<=k<i）
            for (int i = 0; i <= s.length(); i++) {
                for (int k = 0; k < i; k++) {
                    String str = s.substring(k, i);
                    mem[i] = mem[k] && dict.contains(str);

                    if (mem[i])
                        break;
                }
            }

            return mem[s.length()];
        }

    }


}
