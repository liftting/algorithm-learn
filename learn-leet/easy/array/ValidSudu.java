package easy.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wm on 16/3/24.
 * <p/>
 * 36. Valid Sudoku
 * <p/>
 * 检查行
 * 检查列
 * 在每个子数独检查
 * <p/>
 * 保证检查时，往set中添加数据时，添加成功即没有重复的
 */
public class ValidSudu {

    public class Solution {
        public boolean isValidSudoku(char[][] board) {

            Set<Character> set = new HashSet<Character>();
            //先检查行
            for (int i = 0; i < board.length; i++) {
                set.clear();
                for (int j = 0; j < board[i].length; j++) {
                    //如果数已经存在了，那么添加时返回错误
                    if (board[i][j] != '.' && !set.add(board[i][j])) return false;
                }
            }

            for (int i = 0; i < board.length; i++) {
                set.clear();
                for (int j = 0; j < board[i].length; j++) {
                    //如果数已经存在了，那么添加时返回错误
                    if (board[j][i] != '.' && !set.add(board[j][i])) return false;
                }
            }

            //每个子项目进行检查
            int length = board.length; //几行
            int child = length / 3; // 每行，每列能等分几个

            for (int r = 0; r < child; r++) { //行

                for (int i = 0; i < child; i++) { //列
                    set.clear();
                    for (int k = 3 * r; k < 3 * (r + 1); k++) {
                        //3行
                        for (int j = 3 * i; j < 3 * (i + 1); j++) {
                            //列的递归
                            if (board[k][j] != '.' && !set.add(board[k][j])) return false;
                        }
                    }
                }
            }

            return true;
        }
    }

}
