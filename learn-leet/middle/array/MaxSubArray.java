package middle.array;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/12.
 * 53. Maximum Subarray
 * <p/>
 * 求最大子数组问题，
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * <p/>
 * 1，暴力，遍历，
 */
public class MaxSubArray {

    public static void main(String[] args) {
//        Solution s = new Solution();
//        MidSolution midSolution = new MidSolution();
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        CommonUtil.show(midSolution.search(array, 0, array.length - 1));

        DpSolution dpSolution = new DpSolution();
        CommonUtil.show(dpSolution.maxSubArray(array));

    }

    public static class Solution {
        public int maxSubArray(int[] nums) {
            return search1(nums);
        }

        public int search1(int[] nums) {
            int sum = 0;
            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                sum = 0;

                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum > max) {
                        max = sum;
                    }
                }
            }

            return max;
        }

    }

    public static class MidSolution {

        public int findMid(int[] array, int left, int mid, int right) {

            int leftMax = Integer.MIN_VALUE;
            int leftSum = 0;
            for (int i = mid; i >= left; i--) {
                leftSum += array[i];
                if (leftSum > leftMax) {
                    leftMax = leftSum;
                }
            }


            //上面等于了，下面要向前加+1，不能重复计算
            int rightMax = Integer.MIN_VALUE;
            int rightSum = 0;
            for (int i = mid + 1; i <= right; i++) {
                rightSum += array[i];
                if (rightSum > rightMax) {
                    rightMax = rightSum;
                }
            }

            return leftMax + rightMax;

        }

        public int search(int[] array, int left, int right) {
            int mid = (left + right) / 2;
            if (left >= right) {
                return array[left];
            }

            int leftMax = search(array, left, mid);
            int rightMax = search(array, mid + 1, right);

            int midMax = findMid(array, left, mid, right);

            if (leftMax >= rightMax && leftMax >= midMax) {
                return leftMax;
            } else if (rightMax >= leftMax && rightMax >= midMax) {
                return rightMax;
            } else {
                return midMax;
            }
        }

        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            return search(nums, 0, nums.length - 1);
        }
    }

    public static class DpSolution {
        //如果A[i+1]+f(i)>0，那么f(i+1)=A[i+1]+f(i)，因为f(i+1)要以A[i+1]结尾；
//        但是如果A[i+1]+f(i)<0，那我可以扔掉这个子串，也就是说，就是一个空串（""）的和0都比这个子串的sum大。
        public int maxSubArray(int[] A) {
            if (null == A)
                throw new IllegalArgumentException();

            int max = Integer.MIN_VALUE;
            int sum = 0;
            // think about every maximum subarray ended with i
            for (int i : A) {
                sum += i;

                // keep it
                if (sum > max)
                    max = sum;

                // deprecate it, the worst case: every member in A are negative, then the result is 0
                if (sum < 0) //因为已经小于<0 了，那么前缀 0都比这个前缀大，直接=0即可
                    sum = 0;
            }

            return max;
        }

    }


}
