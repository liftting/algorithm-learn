package classcode;

/**
 * Created by wm on 17/5/22.
 */

public class JvmCodeMain {

    private int age;
    private String name;

    private static String text = "this is";
    private static String desc;

    public JvmCodeMain() {
        age = 10;
        name = "whats name";
        desc = "this is what";
    }

    public JvmCodeMain(int age) {
        this.age = age;
    }

    private void add() {
        int i = 12;
        age += i;
    }

}
