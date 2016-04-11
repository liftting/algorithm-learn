package qiyuesuanfa.dynamic;

import easy.CommonUtil;

/**
 * Created by wm on 16/4/8.
 * <p/>
 * 1 3 5 硬币，凑够 m 钱，所用的最少的方式
 * <p/>
 * dynamic
 * d(i) = j  凑够 i 元 所要花费最少的硬币数
 * 1， d(0) = 0
 * 2, d(1 ) = d(1-1) +1 拿一个一元的硬币即可
 * 3，d(2) = d(2-1) +1 还是拿一个一元的硬币
 * <p/>
 * 4， d(3) = d(3-1) + 1// 直接拿一个一元的 或
 * d(3) = d(3-3) + 1 // 直接拿一个3元的
 * <p/>
 * d(3) = min{} 两者最小值
 * <p/>
 * <p/>
 * //得到状态转移方程
 * <p/>
 * d(i) = min{d(i-vj) +1} vj 表示选取的面值
 */
public class CoinSelect {

    public static void main(String[] args) {
        search(10);
    }

    public static void search(int num) {
        //从0 子问题开始计算
        int min[] = new int[num + 1];
        for (int i = 0; i < min.length; i++) {
            min[i] = Integer.MAX_VALUE; //
        }
        min[0] = 0;


        int coin[] = new int[]{1, 5, 7};

        for (int i = 1; i <= num; i++) {

            for (int j = 0; j < coin.length; j++) {
                if (coin[j] <= i && min[i - coin[j]] + 1 < min[i]) {  //判断是否是最小的，查找到最小的
                    min[i] = min[i - coin[j]] + 1;
                }
            }

        }

        CommonUtil.show(min);

    }

}
