package easy;

/**
 * Created by wm on 16/1/26.
 */
public class CommonUtil {

    public static void show(String data) {
        System.out.println(data);
    }

    public static void show(int data) {
        System.out.println(data);
    }

    public static void show(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

}
