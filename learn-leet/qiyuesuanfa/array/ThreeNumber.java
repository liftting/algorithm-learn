package qiyuesuanfa.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/6.
 * 荷兰国旗， 三个数 排列
 * <p/>
 * 1，利用了快速排序中划分的思想，
 * <p/>
 * <p/>
 * 解法：
 * 1，
 * 只是需要用到三个指针：一个前指针begin，一个中指针current，一个后指针end，current指针遍历整个数组序列，当
 * <p/>
 * current指针所指元素为0时，与begin指针所指的元素交换，而后current++，begin++ ； // 因为交换后，begin就变成0，那就符合条件，不用再判断了，继续往前走，
 * current指针所指元素为1时，不做任何交换（即球不动），而后current++ ；
 * current指针所指元素为2时，与end指针所指的元素交换，而后，current指针不动，end-- 。 //
 *
 * 对于第三点：
 * 如果end指针之前指的元素是0，那么与current指针所指元素交换之后，current指针此刻所指的元素是0，此时，current指针能动么？
 * 不能动，因为如上述第1点所述，如果current指针所指的元素是0，还得与begin指针所指的元素交换。
 *
 *
 *
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
