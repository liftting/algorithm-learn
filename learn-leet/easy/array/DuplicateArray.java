package easy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wm on 16/3/24.
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
 * and the difference between i and j is at most k.
 * <p/>
 * 219. Contains Duplicate II
 * <p/>
 * 一个 array，查找 i j ，满足nums[i] == nums[j], 这两个距离最大是k,
 * <p/>
 * 使用map存储值，key 值， value 位置，
 * 先遍历，顺序遍历，
 * 如果发现之前存在这个值，那么 算距离
 * <p/>
 * <p/>
 * 数据的位置，值，必须存储起来，在后面的遍历过程中
 */
public class DuplicateArray {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.containsNearbyDuplicate(new int[]{1, 2, 3, 4, 5, 2, 11, 12, 13, 14, 15, 16, 3}, 5);
    }

    public static class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            if (nums == null || nums.length <= 0 || k <= 0) return false;

            Map<Integer, Integer> map = new HashMap<Integer, Integer>();

            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], i);
                } else {
                    //包含
                    int oldPos = map.get(nums[i]);
                    if ((i - oldPos) <= k) {
                        System.out.print("dist:" + i + "--" + oldPos);
                        return true;
                    }
                    //更新新的位置，因为之前如果不符合，后面更不会符号
                    map.put(nums[i], i);
                }
            }

            return false;

        }
    }

}
