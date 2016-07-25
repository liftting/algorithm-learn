package tree;

/**
 * 链表结构，hash结构中可以使用的
 */
public class SequenceSearch<Key, Value> {

    private int N;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs`

    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {

        if (key == null) throw new NullPointerException("argument to get() is null");
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;

    }

    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null");

        first = delete(first, key);
    }

    //TODO 这个删除方式记住
    private Node delete(Node node, Key key) {
        if (key.equals(node.key)) {
            N--;
            return node.next;
        }
        //递归，不断的修改节点的后继节点，
        node.next = delete(node.next, key);
        return node;
    }


    public void put(Key key, Value value) {

        if (key == null) throw new NullPointerException("argument to get() is null");

        if (value == null) {
            //delete
            delete(key);
        }
        for (Node x = first; x != null; x = x.next) {
            if (key == x.key) {
                x.val = value;
                return;
            }
        }
        N++;
        first = new Node(key, value, first);
    }

    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("argument to contains() is null");
        return get(key) != null;
    }


}
