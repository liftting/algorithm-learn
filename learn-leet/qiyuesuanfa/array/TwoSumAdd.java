package qiyuesuanfa.array;

/**
 * Created by wm on 16/4/5.
 * 一个数组， 查找到两个数 和 sum
 * 1， 排序，两索引查找
 * 2， hash 查找 （hash 存在冲突，）
 * // == 多个 add 时，
 * <p/>
 * 3, 递归 暴力
 */
public class TwoSumAdd {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.find(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 10);
    }

    public static class Solution {

        public void find(int data[], int total) {
            boolean[] flag = new boolean[data.length];
            search(data, flag, 0, 0, total);
        }

        public void search(int data[], boolean flag[], int index, int cur, int total) {

            if (index >= data.length) return;

            //查找到一个继续寻找其他的最优解
            if (cur + data[index] == total) {
                flag[index] = true;
                show(data, flag);
                flag[index] = false;
            }

            flag[index] = true;
            search(data, flag, index + 1, cur + data[index], total);
            flag[index] = false;
            search(data, flag, index + 1, cur, total);

        }

        public void show(int data[], boolean flag[]) {
            for (int i = 0; i < data.length; i++) {
                if (flag[i]) {
                    System.out.print(data[i]);
                }
            }
            System.out.println();
        }

    }

}
