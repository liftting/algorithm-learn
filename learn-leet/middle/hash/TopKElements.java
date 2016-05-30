package middle.hash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/17.
 * 347. Top K Frequent Elements
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p/>
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * <p/>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * <p/>
 * 1,重复次数，元素排序，返回数组
 * 当0<n<10 的时候 nlogn<n
 * 当n=10 的时候 nlogn=n
 * 当n>10 的时候 nlogn>n
 * <p/>
 * 当log 以10 为底部时，
 * <p/>
 * 解决方案:
 * 1,hash 存储元素重复次数， 遍历数组，再遍历map排序，  o(n) 堆的创建 大顶堆， 小顶堆
 * 2，快速排序， n*logn
 * 3，hash map ，+ 桶排序，
 * 4，hash map + 优先队列，  -- 最终都是数据排序的问题，
 * <p/>
 * ===
 * 再分析：
 * 1，其实这个题，就是部分排序的问题，选择排序，或插入排序， 就排K个元素即可 时间复杂度 o(n * k)
 * 2，快速排序时，思考的划分方案， 每次划分，都是左右有部分，
 * 3，k 的最小顶堆， 时间复杂度 更新小顶堆的时间复杂度是O(log2K)
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 */
public class TopKElements {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.showInteger(s.topKFrequentOther(new int[]{4, 1, -1, 2, -1, 2, 3}, 2));
    }

    public static class Solution {
        public List<Integer> topKFrequent(int[] nums, int k) {

            List<Integer> result = new ArrayList<Integer>();

            if (null == nums || nums.length <= 0 || k <= 0) return result;

            HashMap<Integer, Integer> dataMap = new HashMap<Integer, Integer>();

            for (int i = 0; i < nums.length; i++) {
                if (dataMap.containsKey(nums[i])) {
                    dataMap.put(nums[i], dataMap.get(nums[i]) + 1);
                } else {
                    dataMap.put(nums[i], 1);
                }
            }

            //建立k 堆， （小顶堆） 每次有比其大的，更新进去， i<=2i i<=2i+1
            //i节点的  左右子结点下标分别为2*i+1和2*i+2
            // 选取k元素初始化，然后，再k+1往后来更新，
            //1，map TODO 因为可以使用堆来处理，

            //2， or  数组索引方式  -- 其实这个就是水桶排序的方式
            // 值如果是负数呢？ //TODO 负数，+ 大数
            List<Integer>[] cache = new List[nums.length + 1];
            for (Integer kt : dataMap.keySet()) {
                int tot = dataMap.get(kt);
                List<Integer> c = cache[tot];
                if (c == null) {
                    //
                    c = new ArrayList<Integer>();
                    cache[tot] = c;
                }
                c.add(kt);
            }

            int t = 0;
            for (int i = cache.length - 1; i >= 0; i--) {
                List<Integer> list = cache[i];
                if (list != null && t < k) {
                    t++;
                    result.addAll(list);
                }
            }

            return result;


        }

        public List<Integer> topKFrequentOther(int[] nums, int k) {

            List<Integer> result = new ArrayList<Integer>();

            if (null == nums || nums.length <= 0 || k <= 0) return result;

            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int num : nums) {
                map.put(num, map.containsKey(num) ? map.get(num) + 1 : 1);
            }

            //优先队列方式，添加时，其内部就已经进行了排序，不用我们处理
            PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<Map.Entry<Integer, Integer>>(map.size() + 1,
                    new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            return o2.getValue() - o1.getValue();
                        }
                    }
            );

            queue.addAll(map.entrySet());
            for (int i = 0; i < k; i++) {
                result.add(queue.poll().getKey());
            }
            return result;
        }

        public void topDui(int[] values, int x, int k) {
            if (x > values[0]) {
                //比k 个元素中的最小的元素要大，
                values[0] = x;

                int p = 0;
                while (p < k) {
                    int q = 2 * p + 1;
                    if (q >= k) break;
                    if (q < k - 1 && values[q + 1] < values[q]) {
                        q = q + 1;
                    }

                    if (values[q] < values[p]) {
                        int t = values[p];
                        values[p] = values[q];
                        values[q] = t;
                        p = q;
                    } else break;

                }

            }
        }


    }


}
