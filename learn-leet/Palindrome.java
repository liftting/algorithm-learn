/**
 * Created by wm on 16/1/26.
 */
public class Palindrome {

    public static void main(String[] args) {
        int data[] = new int[]{1, 1221, 121, 123321, 12321, 12, 1234, 4321, 0};
        showMsg(data);
    }

    public static void showMsg(int data[]) {

        int length = data.length;
        for (int i = 0; i < length; i++) {
            if (calue(data[i])) {
                CommonUtil.show(data[i] + ":is param data");
            } else {
                CommonUtil.show(data[i] + ":is not not param data");
            }
        }

    }

    public static boolean calue(int x) {

        if (x < 0) return false;

        if (x == 0) return true;

        //多少位
        int digit = 0;
        int d = x;
        while (d != 0) {
            digit++;
            d = d / 10;
        }

        if (digit == 1) return true;

        int mid = digit / 2;
        for (int i = 1; i <= mid; i++) {
            if (getPosition(x, i, digit) != getPosition(x, (digit - i + 1), digit)) return false;
        }

        return true;

    }

    public static int getPosition(int number, int pos, int totalDigit) {

        if (totalDigit == pos) return number % 10;

        return number / (int) Math.pow(10, totalDigit - pos) % 10;
    }


}
