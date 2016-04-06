package middle.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import easy.CommonUtil;
import easy.tree.TreeNodeFactory;

/**
 * Created by wm on 16/4/5.
 * 127. Word Ladder
 * <p/>
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * <p/>
 * 每次只能变换一个词，
 * 而且这个词是在这个数组集合中的，
 * <p/>
 * find the length of shortest transformation sequence
 * 查找到最短的序列
 * <p/>
 * 1,return 0
 * 2,所有单词长度相同
 * 3,都只有小写的
 * <p/>
 * === 问题：
 * 在写广搜时，最好不要用递归，那是深搜
 * 广搜是 集合遍历的
 */
public class WordLadder {

    public static void main(String[] args) {
        Solution s = new Solution();
        Set<String> res = new HashSet<String>();
        res.add("hot");
        res.add("dot");
        res.add("dog");
        res.add("lot");
        res.add("log");

        CommonUtil.show(s.search2("hit", "cog", res));

    }


    public static class Solution {

        private int result = 0;

        public class WordNode {
            public String word;
            public int distance;

            public WordNode(String word, int distance) {
                this.word = word;
                this.distance = distance;
            }
        }

        /**
         * 广搜
         *
         * @param beginWord
         * @param endWord
         * @param wordList
         * @return
         */
        public int ladderLength(String beginWord, String endWord, Set<String> wordList) {

            result = wordList.size();

            wordList.add(beginWord);
            wordList.add(endWord);

            Map<String, Boolean> map = new HashMap<String, Boolean>();
            for (String s : wordList) {
                map.put(s, false);
            }

            List<String> path = new ArrayList<String>();
            path.add(beginWord);
            map.put(beginWord, true);

            search(beginWord, endWord, wordList, path, map);

            return result;
        }

        public void search(String okData, String endWord, Set<String> wordList, List<String> path, Map<String, Boolean> map) {
            if (okData.equals(endWord)) {
                if (path.size() < result) {
                    result = path.size();
                }
//                CommonUtil.show(path);
                return;
            }

            Set<String> find = findWord(okData, wordList, map);
            for (String s : find) {
                map.put(s, true);
                path.add(s);
                search(s, endWord, wordList, path, map);
                map.put(s, false);
                path.remove(s);
            }
            return;
        }

        public int search2(String beginWord, String endWord, Set<String> wordList) {

            if (beginWord.equals(endWord)) return 1;

            wordList.add(endWord);

            Set<WordNode> set = new HashSet<WordNode>();
            set.add(new WordNode(beginWord, 1));

            while (!wordList.isEmpty()) {

                Set<WordNode> create = new HashSet<WordNode>();
                for (WordNode node : set) {
                    Set<String> findSet = findWord2(node.word, wordList); // 修正后还是超时,内部是两次循环超时
                    for (String s : findSet) {
                        if (s.equals(endWord)) return node.distance + 1;

                        if (wordList.contains(s)) {
                            wordList.remove(s);
                            create.add(new WordNode(s, node.distance + 1));
                        }
                    }
                }

                if (create.isEmpty()) return 0;

                set = create;
            }

            return 0;
        }

        //在查询可适配的地方，很容易出现超时 ，
        public Set<String> findWord2(String word, Set<String> wordList) {
            Set<String> result = new HashSet<String>();

            int len = word.length();
            int index = 0;
            String temp = word;
            while (index < len) {
                temp = delete(index, word);
                for (String s : wordList) {
                    if (delete(index, s).equals(temp)) {
                        result.add(s);
                    }
                }
                index++;
            }

            return result;

        }


        //在查询可适配的地方，很容易出现超时
        public Set<String> findWord(String word, Set<String> wordList, Map<String, Boolean> map) {
            Set<String> result = new HashSet<String>();

            int len = word.length();
            int index = 0;
            String temp = word;
            while (index < len) {
                temp = delete(index, word);
                for (String s : wordList) {
                    if (!map.get(s) && delete(index, s).equals(temp)) {
                        result.add(s);
                    }
                }
                index++;
            }

            return result;

        }

        public String delete(int pos, String value) {
            if (pos <= 0) return value.substring(pos + 1, value.length());

            if (pos >= value.length() - 1) return value.substring(0, pos);

            StringBuilder sb = new StringBuilder();

            sb.append(value.substring(0, pos)).append(value.substring(pos + 1, value.length()));

            return sb.toString();


        }

    }

    //这个是过的解决办法
    public static class OtherSolution {

        public class WordNode {
            public String word;
            public int distance;

            public WordNode(String word, int distance) {
                this.word = word;
                this.distance = distance;
            }
        }

        public int ladderLength(String start, String end, Set<String> dict) {
            if (start.equals(end))
                return 1;

            Set<WordNode> set = new HashSet<WordNode>();
            set.add(new WordNode(start, 1));

            while (!dict.isEmpty()) {
                Set<WordNode> create = new HashSet<WordNode>();

                for (WordNode node : set) {
                    for (int i = 0; i < node.word.length(); i++) {
                        for (char ch = 'a'; ch <= 'z'; ch++) {
                            char[] arr = node.word.toCharArray();
                            arr[i] = ch;
                            //
                            String changeWord = new String(arr);
                            if (end.equals(changeWord)) {
                                return node.distance + 1;
                            }

                            if (dict.contains(changeWord)) {
                                dict.remove(changeWord); //类似标记已经访问的，
                                create.add(new WordNode(changeWord, node.distance + 1));
                            }
                        }
                    }
                }

                if (create.isEmpty()) {
                    return 0;
                }

                set = create;

            }

            return 0;

        }

    }

}
