package easy.dongtaiguihua;

import java.util.Arrays;

/**
 * Created by wm on 16/3/17.
 * <p/>
 * 70. Climbing Stairs
 * <p/>
 * 假设从底走到第n级的走法有f（n）种，走到第ｎ级有两个方法，一个是从（n-1)级走一步，另一个是从第（ｎ－２）级走两步，
 * 前者有f(n-1)种方法，后者有f(n-2)种方法，所以有f(n)=f(n-1)+f(n-2),还有f(0)=1,f(1)=1.
 * <p/>
 * http://blog.csdn.net/ryj111/article/details/5192969
 * <p/>
 * 动态规划方程
 */
public class StepOneTwoWay {

    public static void main(String[] args) {
        SolutionTwo s = new SolutionTwo();
        System.out.print(s.climbStairs(3));
    }

    public static class SolutionOne {
        public int climbStairs(int n) {
            return calute(n);
        }


        private int calute(int n) {
            if (n == 0 || n == 1) return 1;
            return calute(n - 1) + calute(n - 2);
        }

    }


    public static class SolutionTwo {

        private int[] result;

        public int climbStairs(int n) {
            result = new int[n + 1];
            return calute(n);
        }

        private int calute(int n) {
            if (n == 0 || n == 1) return 1;

            if (result[n] > 0) return result[n];
            int data = 0;

            data = calute(n - 1) + calute(n - 2);
            result[n] = data;

            return data;

        }

    }

}
