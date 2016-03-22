package easy.numoperation;

/**
 * Created by wm on 16/3/10.
 */
public class NimStone {

    public class Solution {

        /**
         * 4: 1,2,3  我操作后，剩下的如果大于三，且小于7，我赢
         * 因为4是不可能赢的，所以减掉一个数后到4 ，给对方，那就能赢，
         * <p/>
         * 每次减掉1 或 2 或 3
         *
         * @param n
         * @return
         */
        public boolean canWinNim(int n) {

            return (n - 1) % 4 == 0 || (n - 2) % 4 == 0 || (n - 3) % 4 == 0;
        }
    }

}
