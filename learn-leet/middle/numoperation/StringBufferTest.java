package middle.numoperation;

/**
 * Created by wm on 16/5/12.
 */
public class StringBufferTest {

    public static void method(StringBuffer s1, StringBuffer s2) {
        s1.append(s2);
        s2 = s1;
    }

    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer("A");
        StringBuffer s2 = new StringBuffer("B");

        method(s1, s2);

        System.out.print(s1 + ":" + s2);
    }

}
