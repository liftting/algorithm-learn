package string;

/**
 * //该算法基于键索引计数法的思想，进行了扩展，使得该算法可以
 * //处理不等长的字符串排序，其中涉及两个关键点。
 * //1、采用分治法，从高位向低位的方向，依次选取关键字做为排序
 * //   的键字进行排序，每一轮排序后，将字符串组进行拆分，对拆
 * //   分后的每个子组分别进行排序，这里子组拆分的依据就是本轮
 * //   键索引计数法排序之后的分类组。
 * //
 * //   如下示例，最初只有一个字符串组，其中组成员数为5个字符串
 * //   首先，选择第0列做为排序的链字进行键索引计数法排序，paixu
 * //   完成后，按分类组划分，此时分为了两组（见第一次后的情况）,
 * //   这时候对这两组分别进行链索引计数法排序，注意这时每组第
 * //   0列已经为相同字符，所以此时选择第1做为排序的链字进行键
 * //   索引计数法排序，在第二次排序后，此时已经分为了4组字符串
 * //   组，依次类推，直到所有子组仅含有一个成员，所有子组排序
 * //   处理完后，即整个字符串排序算法完成。
 * //
 * //   原始：    第一次后：    第二次后：
 * //   abcd      abcd          abcd
 * //   ddba      acca
 * //   daca                    acca
 * //   acca      ddba
 * //   daab      daca          daca
 * //             daab          daab
 * //
 * //                           ddba
 * //
 * //2、刚才提到了该算法可以处理不等长的字符串排序，该算法采用一
 * //   种比较巧妙的方法，将短字符串长度已经满足不了排序的处理也
 * //   做为键值比较处理了，同时如果短字符串长度满足不了排序处理
 * //   时，该键值优先级最高，所以就会出现排在最上方。
 * //
 * //   如下示例，当第2列(字符c的位置)处理完后，开始进行第3列比较
 * //   处理，此时第一个条目abcd的第3列键值为d、第二个条目abc的第
 * //   3列键值已经不存在，长度已经满足不了排序，但此时键值优先级
 * //   为最高，第三个条目abcde的第3列键值为d，所以本轮最终将第二
 * //   个条目abc排在了最上面，相同原理，abcd条目就会比abcde条目的
 * //   优先级高。
 * //
 * //   原始：    排序后：
 * //   abcd      abc
 * //   abc       abcd
 * //   abcde     abcde
 */
public class MSD {

    //有效字符基数
    private static final int R = 256; // 比较字符整数值，即小组分类的数目

    private static String[] aux;

    public static void main(String[] args) {
        String[] s = new String[]{"asd", "defs", "ab", "ecfffff", "bgig", "asff", "asc", "ass", "e"};
        aux = new String[s.length];
        sort(s, 0, s.length - 1, 0);

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }

    private static int sortCharAt(String s, int pos) {
        int length = s.length();
        if (pos >= length) return -1;

        return s.charAt(pos);
    }

    public static void sort(String[] a, int low, int upper, int d) {

        if (low >= upper) return;

        //以d位置的a[d]的字符进行排序，排列 a[low] - a[upper]
        int[] count = new int[R + 2];

        for (int i = low; i <= upper; i++) {
            count[sortCharAt(a[i], d) + 2]++;
        }

        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        for (int i = low; i <= upper; i++) {
            aux[count[sortCharAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = low; i <= upper; i++) {
            a[i] = aux[i - low];
        }

        for (int r = 0; r < R; r++) {
            sort(a, low + count[r], low + count[r + 1] - 1, d + 1);
        }


    }

}
