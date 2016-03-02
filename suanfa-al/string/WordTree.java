package string;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * key value
 * shell  2
 * short  3
 */
public class WordTree<Value> {

    //适配256个字符
    public static final int R = 256;

    private Node root;

    public static class Node {
        Object value;
        Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node node = get(root, key, 0);
        if (node == null) return null;

        return (Value) node.value;
    }

    private Node get(Node node, String key, int index) {

        if (node == null) return null;

        if (index == key.length()) return node;

        char c = key.charAt(index);
        return get(node.next[c], key, index + 1);

    }

    public void put(String key, Value value) {
        //取第一个字符进行比较
        root = put(root, key, value, 0);
    }

    /**
     * 对每个字符串的字符取出，进行递归查询
     *
     * @param node
     * @param key
     * @param value
     * @param index
     * @return
     */
    private Node put(Node node, String key, Value value, int index) {

        //递归到最后，字符串
        if (node == null) node = new Node();
        if (index == key.length()) {
            node.value = value;
            return node;
        }

        char c = key.charAt(index);
        node.next[c] = put(node.next[c], key, value, index + 1);//本字符中查找到了，继续查找key中下一个字符，递归

        return node;

    }

    /**
     * 查找所有以特定字符匹配的串集合
     *
     * @param pre
     * @return
     */
    public Iterable<String> keyWithPre(String pre) {

        Queue<String> queue = new ArrayDeque<String>();
        collect(get(root, pre, 0), pre, queue);
        return queue;

    }

    private void collect(Node node, String pre, Queue<String> q) {
        if (node == null) return;

        if (node.value != null) q.add(pre);
        for (char c = 0; c < R; c++) {
            collect(node.next[c], pre + c, q);
        }

    }

    /**
     * 最长前缀串匹配
     * 注意，这个匹配的是前醉
     *
     * @param str
     * @return
     */
    public String keyWithLongest(String str) {
        int length = search(root, str, 0, 0);

        return str.substring(0, length);
    }

    private int search(Node node, String str, int d, int len) {

        if (node == null) return len;// 查找到了最底部

        if (node.value != null) len = d;

        if (d == str.length()) return len;//已经全部匹配了

        char c = str.charAt(d);
        return search(node.next[c], str, d + 1, len);
    }

}
