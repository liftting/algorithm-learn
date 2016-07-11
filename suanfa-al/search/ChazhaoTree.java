package search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import easy.CommonUtil;
import easy.tree.BinaryTreeOrderOne;
import easy.tree.TreeNode;
import easy.tree.TreeNodeFactory;
import string.WordTree;

/**
 * Created by wm on 16/2/19.
 * this is cha zhao tree
 * <p/>
 * 二叉查找树，
 */
public class ChazhaoTree {

    public static void main(String[] args) {

        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        int[] data = new int[]{4, 2, 3, 1, 5, 6};

        for (int d : data) {
            bst.put(d, d);
        }

        bst.show();

        bst.remove(bst.root,2);

        bst.show();


    }

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
            if (root == null) {
                root = put(root, key, value);
            } else {
                put(root, key, value);
            }

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

        private Node remove(Node node, Key key) {
            if (node == null) return node;

            int cp = key.compareTo(node.key);
            if (cp < 0) {
                return remove(node.left, key);
            } else if (cp > 0) {
                return remove(node.right, key);
            } else if (node.left != null && node.right != null) {

                node.value = min(node.right).value;
                node.right = deleteMin(node.right);

            } else {
                node = (node.left != null) ? node.left : node.right;
            }

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
                // t 先存储着要删除的节点，

                //替换  查找到要替换的节点了，
                node = min(t.right);

                node.right = deleteMin(t.right);
                node.left = t.left;

            }
            node.N = size(node.left) + size(node.right) + 1;
            return node;

        }

        private List<List<Value>> levelOrder(Node root) {
            List<List<Value>> orderList = new ArrayList<List<Value>>();
            if (root == null) return orderList;

            List<Node> oldList = new ArrayList<Node>();
            oldList.add(root);

            while (true) {
                //两个集合，不断更新
                List<Node> dataList = new ArrayList<Node>();
                List<Value> list = new ArrayList<Value>();

                for (Node node : oldList) {
                    list.add(node.value);

                    if (node.left != null) {
                        dataList.add(node.left);
                    }
                    if (node.right != null) {
                        dataList.add(node.right);
                    }

                }

                if (list.size() > 0) {
                    orderList.add(list);
                }

                if (dataList.size() == 0) break;

                oldList = dataList;


            }

            return orderList;

        }

        private void show() {
            List<List<Value>> datalist = levelOrder(root);

            for (List<Value> data : datalist) {
                for (Value t : data) {
                    System.out.print(t + " ");
                }

                System.out.print("\n");

            }

        }


    }


}
