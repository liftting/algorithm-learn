import java.util.Comparator;

/**
 * Created by wm on 16/2/19.
 * this is cha zhao tree
 */
public class ChazhaoTree {


    public static class BST<Key extends Comparable<Key>, Value> {

        private Node root;

        private class Node {

            public Node left;

            public Node right;

            public Value value;

            public Key key;

            public int N;

            public Node(Key k, Value v, int n) {
                this.key = k;
                this.value = v;
                this.N = n;
            }
        }

        public void put(Key key, Value value) {

        }

        public Value get(Key key) {
            return get(root, key);
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) return 0;
            return node.N;
        }

        private Value get(Node node, Key key) {
            if (node == null) return null;

            int cmp = key.compareTo(node.key);

            if (cmp < 0) {
                //left
                return get(node.left, key);
            } else if (cmp > 0) {
                //right
                return get(node.right, key);
            } else {
                return node.value;
            }

        }

        private Node put(Node node, Key key, Value value) {
            if (node == null) return new Node(key, value, 1);

            int cmp = key.compareTo(node.key);

            if (cmp < 0) {
                node.left = put(node.left, key, value);
            } else if (cmp > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }

            node.N = size(node.left) + size(node.right) + 1;

            return node;

        }

        public Key min() {
            return min(root).key;
        }

        public Key max() {
            return max(root).key;
        }

        private Node min(Node node) {
            if (node.left == null) return node;
            return min(node.left);
        }

        private Node max(Node node) {
            if (node.right == null) return node;
            return max(node.right);
        }

        private Node deleteMin(Node node) {
            if (node.left == null) return node.right;
            node.left = deleteMin(node.left);

            node.N = size(node.left) + size(node.right) + 1;
            return node;

        }

        private Node delete(Node node, Key key) {
            if (node == null) return null;
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                return delete(node.left, key);
            } else if (cmp > 0) {
                return delete(node.right, key);
            } else {
                if (node.right == null) return node.left;
                if (node.left == null) return node.right;

                Node t = node;

                //替换
                node = min(t.right);

                node.right = deleteMin(t.right);
                node.left = t.left;

            }
            node.N = size(node.left) + size(node.right) + 1;
            return node;

        }


    }


}
