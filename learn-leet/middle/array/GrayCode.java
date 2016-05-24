package middle.array;

/**
 * Created by wm on 16/5/18.
 * 89. Gray Code
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * <p/>
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * <p/>
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * <p/>
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * <p/>
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * <p/>
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * <p/>
 * 1,n 二进制位数，能构造出多少个数字，
 * 2，从后往前填1 ，这个顺序不能变，
 * <p/>
 * <p/>
 * 解法：
 * 1，类似组合，递归，dfs  填1 或不填1，
 */
public class GrayCode {

    public static void main(String[] args) {

    }

    public void search(int nums[], int n, int index) {



    }

    private int calute(int[] nums) {
        int sum = 0;
        int j = 1; // i = 2
        for (int i = 0; i > nums.length; i++) {
            if (i > 0) {
                j *= 2;
            }

            sum += (j * nums[i]);

        }
        return sum;
    }

}
