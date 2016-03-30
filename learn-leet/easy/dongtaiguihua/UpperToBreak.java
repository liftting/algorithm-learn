package easy.dongtaiguihua;

/**
 * 这个问题是，两个一模一样的鸡蛋，某层之上扔鸡蛋就会碎。假如运气最差的话，问要测试多少次才能找出这层楼来。
 *
 * 动态规划问题
 *
 */
public class UpperToBreak {

    public static void main(String[] args) {
        System.out.print(dropEgg(80, 2));
    }

    // times[i]表示N取值为i的时候，需要扔多少次
    private static int[][] times;

    public static int dropEgg(int N, int m) {
        // 初始化数组
        times = new int[m + 1][N + 1];
        return dp(N, m);
    }

    private static int dp(int N, int m) {
        if (1 == m || 1 == N || 2 == N)
            return N;

        for (int time = 2; time <= m; time++)
            for (int x = 2; x < N; x++) {
                int max = maxTimes(N, x, time);
                if (0 == times[time][N] || times[time][N] > max)
                    times[time][N] = max;
            }

        return times[m][N];
    }

    // 碎和不碎的次数最大值
    private static int maxTimes(int N, int x, int m) {
        int sum = dp(N - x, m) + 1;
        if (x < sum)
            return sum;
        else
            return x;
    }

}
