package easy.array;

/**
 * Created by wm on 16/3/28.
 * <p/>
 * Range Sum Query
 * 303. Range Sum Query - Immutable
 * <p/>
 * <p/>
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * <p/>
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 */
public class RangeSumQuery {

    public class NumArray {

        //缓存
        private int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length];
            int s = 0;
            for (int i = 0; i < nums.length; i++) {
                s += nums[i];
                sum[i] = s;
            }
        }

        public int sumRange(int i, int j) {
            int min = 0;
            //因为这个方法多次调用，直接获取前面数组收集的值，计算即可，
            if (i > 0) min = sum[i - 1];
            return sum[j] - min;
        }
    }

}
