package string;

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

    public Node get(Node node, String key, int index) {

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
    public Node put(Node node, String key, Value value, int index) {

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

}
