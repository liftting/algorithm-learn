package string;

/**
 * 低位字符排序，基于之前的索引键位排序
 */
public class LSD {

    public static void main(String[] args) {
        String[] s = new String[]{"111", "555", "222", "333", "888", "777", "123", "234", "745"};
        sort(s, 3);

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }

    }

    //数组字符串长度都是w
    public static void sort(String[] array, int w) {

        int length = array.length;
        int R = 256;
        String[] aux = new String[length];

        for (int i = w - 1; i >= 0; i--) {
            int[] count = new int[R + 1];

            for (int j = 0; j < length; j++) {
                count[array[j].charAt(i) + 1]++;
            }

            for (int k = 0; k < R; k++) {
                count[k + 1] += count[k];
            }

            for (int h = 0; h < length; h++) {
                aux[count[array[h].charAt(i)]++] = array[h];
            }

            for (int ii = 0; ii < length; ii++) {
                array[ii] = aux[ii];
            }

        }

    }

}
