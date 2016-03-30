package middle.numoperation;

/**
 * Created by wm on 16/3/30.
 * <p/>
 * 亦或运算妙处，
 * <p/>
 * 1，两数交换
 * 2，计算一个整数中，包含的1的个数
 * 3，判断两个数是否相等，见SingleNumber
 * <p/>
 * 异或运算有两个特性：
 * 1、一个数异或本身恒等于0，如5^5恒等于0；
 * 2、一个数异或0恒等于本身，如5^0恒等于5。
 * <p/>
 * 算式一：a=b^(a^a)=a^(a^b);
 * 算式二：b=a^(b^b)^(a^a)=a^(a^b)^(a^b);
 */
public class YihuoOperate {

    public static void main(String[] args) {
        swap(3, 5);
    }

    public static void swap(int x, int y) {
        System.out.println("x:" + x + "y:" + y);

        x = x ^ y;
        y = y ^ x;
        x = x ^ y;

        System.out.println("x:" + x + "y:" + y);

    }

}
