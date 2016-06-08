package middle.array;

/**
 * Created by wm on 16/6/6.
 * 122. Best Time to Buy and Sell Stock II
 * <p/>
 * 可以多次买卖交易，
 * 1，贪心
 * 只要后面比前面大，就可以算作收益，
 * <p/>
 * 解法：
 * <p/>
 * 我们可以这么考虑，对于每一笔交易 假设是从i天买入 j天卖出 ，如果有j+1天的价格高于j天，所以肯定是从i到j+1利润更高，
 * 同样的如果有第i-1天价格比第i天更低，肯定是从i-1天到j天利润更高。所以是不是对于每一段我们只用找到波谷和波峰，相减就可以了呢？
 * <p/>
 * 再分析一下，可以推出一个非常简单的式子，只要后项比前项大，这部分值就一定可以变为利润，即：
 * <p/>
 * sum = sum+a[i+1]-a[i](if a[i+1]>a[i])
 * <p/>
 * <p/>
 * 贪心，只要有利润就可以进行操作，
 */
public class BuySellStockTwo {

    public static void main(String[] args) {

    }

    public static class Solution {
        public int maxProfit(int[] prices) {

            if (prices == null || prices.length <= 0) return 0;

            int sum = 0;

            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    sum += (prices[i] - prices[i - 1]);
                }
            }

            return sum;
        }
    }

}
