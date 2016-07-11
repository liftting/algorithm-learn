package string;

/**
 * Created by wm on 16/6/23.
 */
public class JavaChar {

    public static void main(String[] args) {
        show();
    }

    public static void show() {
        int max = 65535;


        for (int i = 0; i <= max; i++) {
            System.out.println(i + " = " + (char) i);
        }

    }


}
