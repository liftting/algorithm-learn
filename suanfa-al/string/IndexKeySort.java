package string;

/**
 * Created by wm on 16/2/26.
 * 这个是最基本的 键值索引排序方式
 */
public class IndexKeySort {

    private static class StringNode {
        String data;
        int key;

        public StringNode(String d, int k) {
            data = d;
            key = k;
        }
    }

    public static void main(String[] args) {

        sort(create());
    }

    private static StringNode[] create() {
        String[] s = new String[]{"111", "555", "222", "333", "888", "777"};

        StringNode[] data = new StringNode[s.length];

        int i = 0;
        for (String str : s) {
            data[i++] = new StringNode(str, str.charAt(0));
        }

        return data;


    }

    public static void sort(StringNode data[]) {
        int n = data.length;
        int r = 256;

        String[] sort = new String[n];
        int count[] = new int[r + 1];

        //计算出现频率
        //排序是按照key大小来进行的
        for (int i = 0; i < n; i++) {
            count[data[i].key + 1]++;
        }

        //转换，
        for (int k = 0; k < r; k++) {
            count[k + 1] += count[k];
        }

        for (int j = 0; j < n; j++) {
            sort[count[data[j].key]++] = data[j].data;
        }

        for (String s : sort) {
            System.out.print(s + "  ");
        }


    }

}
