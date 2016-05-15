package middle.string;

import easy.CommonUtil;

/**
 * Created by wm on 16/5/13.
 * <p/>
 * 字符串旋转
 * <p/>
 * abc  defg  -- defg  abc
 * <p/>
 * 1，k前面的字符串旋转后
 * <p/>
 * 解：
 * 1， abc -- cba
 * defg -- gfed
 * <p/>
 * cba gfed  -- defg abc
 *
 *
 */
public class RotateString {

    public static void main(String[] args) {
        CommonUtil.show(rotateString("abcdefg", 3));
    }

    public static String rotateString(String data, int k) {
        String left = data.substring(0, k);
        String right = data.substring(k, data.length());

        String roLeft = rotate(left);
        String roRight = rotate(right);

        return rotate(roLeft + roRight);

    }

    public static String rotate(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

}
