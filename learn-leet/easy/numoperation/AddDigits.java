package easy.numoperation;

/**
 * Created by wm on 16/1/18.
 */

public class AddDigits {

    public static void main(String[] args) {


    }

    /**
     * O(1)时间
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num == 0)
            return 0;

        return num % 9 == 0 ? 9 : num % 9;
    }

}

