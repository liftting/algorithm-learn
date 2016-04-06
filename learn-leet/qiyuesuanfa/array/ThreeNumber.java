package qiyuesuanfa.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/6.
 * 荷兰国旗， 三个数 排列
 */
public class ThreeNumber {

    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 0, 2, 0, 2, 1, 2, 1};
        CommonUtil.show(array);
        handle(array);
        CommonUtil.show(array);
    }

    /**
     * 数据对比，划分，
     *
     * @param array
     */
    public static void handle(int[] array) {
        int len = array.length;
        int begin = 0;
        int cur = 0;
        int end = len - 1;

        while (cur <= end) {
            if (array[cur] == 2) {
                swap(array, cur, end);
                end--;
            } else if (array[cur] == 1) {
                cur++;
            } else {
                // 这里的比较可以优化处理
                if (begin == cur) {
                    begin++;
                    cur++;
                } else {
                    swap(array, begin, cur);
                    begin++;
                    cur++;
                }
            }
        }

    }

    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

}
