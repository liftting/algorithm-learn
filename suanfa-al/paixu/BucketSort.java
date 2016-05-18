package paixu;

/**
 * Created by wm on 16/5/18.
 * <p/>
 * 1,水桶排序，
 * 桶排序就是直接整个足够大的临时数组,把待排序的元素全部映射过来.其索引为待排序元素数值.
 * 所以为了确定临时数组的大小得先算出数组中最大数.
 * <p/>
 * so:
 * 如果为负数时， 可以处理加上一个足够大的数转换下，
 */
public class BucketSort {


    static int[] bucket_sort(int[] unsorted, int maxNumber) {
        int[] sorted = new int[maxNumber + 1];
        for (int i = 0; i < unsorted.length; i++) {
            sorted[unsorted[i]] = unsorted[i]; //就是建立索引
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[] x = {99, 65, 24, 47, 50, 88, 33, 66, 67, 31, 18};
        int[] sorted = bucket_sort(x, 99);
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i] > 0) {
                System.out.print(sorted[i] + ",");
            }

        }
    }


}
