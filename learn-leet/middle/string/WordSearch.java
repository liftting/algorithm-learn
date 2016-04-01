package middle.string;

import java.util.ArrayList;
import java.util.List;

import easy.CommonUtil;
import middle.ArrayFactory;
import sun.dc.pr.PRError;

/**
 * Created by wm on 16/4/1.
 * 79. Word Search
 * <p/>
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p/>
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p/>
 * For example,
 * Given board =
 * <p/>
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * <p/>
 * 回溯遍历，
 */
public class WordSearch {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.exist(ArrayFactory.buildCharArray(3, 4, new char[]{'A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E'}), "ABCCED"));
    }

    public static class Solution {

        /**
         * 先查找到首字母的位置， 然后以这些点来进行回溯遍历
         *
         * @param board
         * @param word
         * @return
         */
        public boolean exist(char[][] board, String word) {
            if (word == null || word.length() <= 0) return false;

            if (board == null || board.length <= 0) return false;

            List<Point> dataList = getPointList(board, word.charAt(0));

            for (Point p : dataList) {
                boolean[][] flag = getFlag(board);
                if (search(board, flag, p, 0, word)) return true;
                //标记访问，
            }
            return false;
        }

        private List<Point> getPointList(char[][] board, char c) {
            List<Point> points = new ArrayList<Point>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == c) {
                        points.add(new Point(i, j));
                    }
                }
            }
            return points;
        }

        private boolean[][] getFlag(char[][] board) {
            int x = board.length;
            boolean[][] flag = new boolean[x][];
            for (int i = 0; i < x; i++) {
                flag[i] = new boolean[board[i].length];
            }
            return flag;
        }


        public boolean search(char[][] board, boolean[][] flag, Point point, int index, String word) {
            int x = point.x;
            int y = point.y;

            if (index == word.length()) {
                return true;
            }

            char c = board[x][y];
            if (flag[x][y] || c != word.charAt(index)) return false;

            if (index == word.length() - 1)
                return true;

            flag[x][y] = true;

            List<Point> create = new ArrayList<Point>();
            if (x - 1 >= 0) create.add(new Point(x - 1, y));
            if (x + 1 <= board.length - 1) create.add(new Point(x + 1, y));
            if (y - 1 >= 0) create.add(new Point(x, y - 1));
            if (y + 1 <= board[x].length - 1) create.add(new Point(x, y + 1));

            boolean temp;

            for (Point p : create) {
                temp = flag[p.x][p.y];
                //index + 1  这里不能用 ++index 会给index加后赋值
                if (search(board, flag, p, index + 1, word)) return true;
                flag[p.x][p.y] = temp;
            }

            return false;
        }

        public static class Point {
            int x;
            int y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

    }

}
