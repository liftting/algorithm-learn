package qiyuesuanfa.string;

import java.util.Arrays;
import java.util.HashMap;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/30.
 * <p/>
 * 字符串的全排列
 */
public class Pailie {

    public static void main(String[] args) {
        SortDigui s1 = new SortDigui();
        s1.calute();

        CommonUtil.show("======");

        SortLine s2 = new SortLine();
        s2.calute();

        CommonUtil.show("======");

//        OtherPai s3 = new OtherPai();
//        s3.combination(str, str.length);

    }

    public static int[] str = new int[]{1, 2, 3};

    public static void show() {
        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i]);
        }
        System.out.println();
    }

    /**
     * 递归排列
     */
    public static class SortDigui {

        private HashMap<Integer, Boolean> dupMap = new HashMap<Integer, Boolean>();

        public void sort(int from, int to) {
            if (from == to) {
                show();
                return;
            }
            //每次进行一次递归 都创建一个新的，为下面的循环使用，因为一个循环里面会包含多个重复的，
//            dupMap = new HashMap<Integer, Boolean>();

            //每次取出一个字符作为第一个位置，
            for (int i = from; i <= to; i++) {

//                if (dupMap.containsKey(from)) continue;
//
//                dupMap.put(from, true);
                swap(i, from); // from记录开始位置，
                sort(from + 1, to);
                swap(i, from);
            }
        }


        public void calute() {
            sort(0, str.length - 1);
        }
    }

    public static void swap(int x, int y) {
        int temp = str[x];
        str[x] = str[y];
        str[y] = temp;
    }

    /**
     * 线性的
     * 要考虑全排列的非递归实现，先来考虑如何计算字符串的下一个排列。如"1234"的下一个排列就是"1243"。只要对字符串反复求出下一个排列，全排列的也就迎刃而解了。
     * <p/>
     * 如何计算字符串的下一个排列了？来考虑"926520"这个字符串，我们从后向前找第一双相邻的递增数字，"20"、"52"都是非递增的，"26 "即满足要求，称前一个数字2为替换数，
     * 替换数的下标称为替换点，再从后面找一个比替换数大的最小数（这个数必然存在），0、2都不行，5可以，将5和2交换得到"956220"，然后再将替换点后的字符串"6220"颠倒即得到"950226"。
     */
    public static class SortLine {


        public void calute() {
            //从大到小
            Arrays.sort(str);

            int len = str.length;
            int pend = len - 1;

            // 123  替换点 2 -- 查找到3  -- 交换 132 -- 反转 替换点之后的 132
            // 132      1          2       231                       213
            // 213      1          3        231

            int t = findReplacePoint();
            while (true) {
                show();
                int ct = findMaxValue(str[t], t + 1);
                swap(t, ct);
                reverse(t + 1);

                t = findReplacePoint();
                if (t < 0) { //排到最后了，
                    show();
                    break;
                }
            }


        }

        public int findReplacePoint() {
            int last = str[str.length - 1];
            for (int i = str.length - 2; i >= 0; i--) {
                if (last > str[i]) {
                    return i;
                } else {
                    last = str[i];
                }
            }
            return -1;
        }

        private void reverse(int index) {
            int i = index;
            int j = str.length - 1;
            int mid = (i + j) / 2;
            while (i <= mid) {
                swap(i, j);
                i++;
                j--;
            }
        }

        /**
         * 比替换数大的最小数
         *
         * @return
         */
        public int findMaxValue(int te, int index) {
            int max = 10;
            int d = -1;
            for (int i = index; i < str.length; i++) {
                if (str[i] > te && str[i] < max) {
                    max = str[i];
                    d = i;
                }
            }

            return d;
        }

    }

    public static class OtherPai {

        public void swap(int[] data, int i, int j) {
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        //将数组a中的下标i到下标j之间的所有元素逆序倒置
        void reverse(int a[], int i, int j) {
            for (; i < j; ++i, --j) {
                swap(str, i, j);
            }
        }

        void print(int a[], int length) {
            for (int i = 0; i < length; ++i)
                System.out.print(a[i]);
            System.out.println();
        }

        //求取全排列,打印结果
        void combination(int a[], int length) {
            if (length < 2) return;

            boolean end = false;
            while (true) {
                print(a, length);

                int i, j;
                //找到不符合趋势的元素的下标i
                for (i = length - 2; i >= 0; --i) {
                    if (a[i] < a[i + 1]) break;
                    else if (i == 0) return;
                }

                for (j = length - 1; j > i; --j) {
                    if (a[j] > a[i]) break;
                }

                swap(a, i, j);
                reverse(a, i + 1, length - 1);
            }
        }
    }


}
