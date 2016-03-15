import java.util.Arrays;

/**
 * Created by wm on 16/3/15.
 * <p/>
 * 217. Contains Duplicate
 */
public class Duplicate {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.print(s.containsDuplicate(new int[]{1, 2, 2}));
    }

    public static class Solution {
        public boolean containsDuplicate(int[] nums) {
            if (nums == null || nums.length <= 0) return false;
//            SortUtils.quick_sort(nums, 0, nums.length - 1);
            Arrays.sort(nums);

            int i = 0;
            int j = 1;
            while (j < nums.length) {
                if (nums[i] == nums[j]) return true;
                i++;
                j++;
            }

            return false;


        }
    }

}
