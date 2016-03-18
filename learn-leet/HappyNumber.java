/**
 * Created by wm on 16/3/18.
 * <p/>
 * 202. Happy Number
 * <p/>
 * Example: 19 is a happy number
 * <p/>
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber {

    public static void main(String[] args) {
    }

    public class Solution {
        public boolean isHappy(int n) {
            return caulte(n) == 1;
        }

        private int caulte(int num) {

            if (num <= 1) return num;

            //cai
            int sum = 0;
            int n = 0;
            while (num != 0) {

                n = num % 10;
                sum += n * n;

                num /= 10;
            }

            if (sum / 10 > 0) return caulte(sum);

            return sum;

        }
    }


}
