/**
 * Created by wm on 16/3/15.
 */
public class SortUtils {

    //返回调整后基准数的位置
    public static int divide(int s[], int l, int r) {
        int i = l;
        int j = r;
        int x = s[l];//参考点
        while (i < j) {
            while (i < j && s[j] > x) j--;

            if (i < j) {
                s[i] = s[j]; //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                i++;
            }

            while (i < j && s[i] < x) i++;

            if (i < j) {
                s[j] = s[i]; //将s[i]填到s[j]中，s[i]就形成了一个新的坑
                j--;
            }
        }
        s[i] = x;
        return i;
    }


    public static void quick_sort(int s[], int l, int r) {
        if (l < r) {
            int i = divide(s, l, r);//先成挖坑填数法调整s[]
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }


}
