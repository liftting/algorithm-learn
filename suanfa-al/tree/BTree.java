package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import middle.numoperation.IntegerBreak;

/**
 * Created by wm on 16/7/25.
 * <p/>
 * 3  6   10
 * <p/>
 * 1   4   8    11 （其他位置都是相同索引，最后面的位置+1）
 * 下面的B tree实现是按照度的衡量来实现的，
 * 也有使用阶 的来表示的，
 */
public class BTree<E extends Comparable> {

    private static final int DEFAULT_T = 2;

    private BTNode root;

    // 最小度数
    private int t; //最小度数表示法，

    // 非根节点的最少关键字个数
    private int minKeyNum;

    // 非根节点的最大关键字个数
    private int maxKeyNum;

    public BTree() {
        this(DEFAULT_T);
    }

    public BTree(int degree) {
        if (degree < 2) {
            t = DEFAULT_T;
        }
        this.t = degree;
        this.minKeyNum = degree - 1; // 2 就一个元素
        this.maxKeyNum = 2 * degree - 1; // 3个元素
        BTNode node = new BTNode();
        this.root = node;
    }

    /**
     * 层级遍历，进行打印
     */
    public void print() {
        Queue queue = new LinkedBlockingQueue<BTNode>();
        queue.add(root);
        queue.add(createFailNode());
        while (!queue.isEmpty()) {
            BTNode node = (BTNode) queue.poll();

            if (node.isFailNode) {
                System.out.println();
                continue;
            }

            for (int i = 0; i < node.n; i++) {
                System.out.print(node.datas.get(i) + "|");
            }
            System.out.print("  ");
            if (!node.isLeaf) {
                for (int i = 0; i < node.n + 1; i++) {
                    queue.add(node.children.get(i));
                }
                queue.add(createFailNode());
            }
        }

    }

    public BTNode createFailNode() {
        BTNode node = new BTNode();
        node.isFailNode = true;
        return node;
    }


    // ====  插入流程
    /**
     * 1，根节点开始插入数据，如果数据没有超越时
     * 2，根节点满了，那么就要对其进行升级，同时设置其不为叶子节点了， 升级完后再直接插入数据
     * 3，从根节点开始查找位置，如果不为叶子节点，那么开始不断的递归查找
     *
     */

    /**
     * 插入一个数据
     *
     * @param key
     */
    public void insert(E key) {
        //先要判断节点是否已经满了，如果满了要进行拆分，
        BTNode r = root;
        if (root.n == maxKeyNum) {
            //跟节点已经满了，
            BTNode newRoot = new BTNode();
            root = newRoot;

            newRoot.isLeaf = false;
            newRoot.insertChild(0, r);
            sliptChild(newRoot, 0); //对哪个节点的子节点进行升级，


            // 上面是进行调整，调整完后直接插入数据
            insertNotFull(newRoot, key);


        } else {
            // 根节点没有问题，从跟节点开始判断，
            insertNotFull(r, key);
        }


    }

    /**
     * 对于节点的判断，是在这里进行处理的，
     * TODO 索引位置有问题，会越界
     * 处理时，出现越界是因为在对node进行分割时出现问题，
     *
     * @param node
     * @param key
     */
    public void insertNotFull(BTNode node, E key) {
        //
        int i = node.n - 1;
        if (node.isLeaf) {
            //到跟节点了

            //1，叶子节点到了，查找插入位置
            while (i >= 0 && key.compareTo(node.datas.get(i)) < 0) {
                i--;
            }
            i = i + 1;
            // 2,数据区域插入值，
            node.insertKey(i, key);

        } else {
            //没有到根节点，向下查找
            while (i >= 0 && key.compareTo(node.datas.get(i)) < 0) {
                //如果插入的元素比最后一个小，往以前的找，插入位置
                i--;
            }
            // 找到了后，
            i = i + 1; // 非叶子节点时，数据是要填入到下个节点的子节点中的，（要插入到i - i+1）
            if (node.children.get(i).n == maxKeyNum) {
                //如果要插入的节点，子节点已经填满了，要进行分割节点
                sliptChild(node, i); // 这里传的是 这个提升的节点在列表中的索引，
                //进行分割，然后查找到位置，后面会直接插入数据
                if (key.compareTo(node.datas.get(i)) > 0)
                    i = i + 1;

            }
            // 不分割节点，直接插入进去插入还是要递归操作，一遍
            insertNotFull(node.children.get(i), key); // 这里递归了操作，
        }

    }

    /**
     * 当要插入的数据节点，已经达到最大时，先进行分割操作，
     * 将这个节点的，哪个位置的孩子，进行分割成两节点
     * <p/>
     * 当一个节点 4 5 6 7 8 插入10时，
     * node 为这个节点的父亲节点，而index 是为了查找到要进行分割节点作用的索引，
     * <p/>
     * 分割策略是根据配置大小来进行的，
     * <p/>
     * 将node的 第i个孩子分割成两个节点，
     *
     * @param node  被分割节点的父亲节点，
     * @param index 第i个孩子，
     */
    private void sliptChild(BTNode node, int index) {
        BTNode oldNode = node.children.get(index);// 这个是已经满的要进行分割的节点，

        BTNode createNode = new BTNode(); //

        createNode.isLeaf = oldNode.isLeaf;
        // 因为 minKeyNum = t - 1;
        for (int j = 0; j < minKeyNum; j++) {
            createNode.insertKey(j, oldNode.datas.get(j + t));
        }
        if (!oldNode.isLeaf) {
            for (int j = 0; j < t; j++) { // 因为子集合是要多一个位置的，
                createNode.insertChild(j, oldNode.children.get(j + t));
            }
        }

        //处理完新创建的节点复制后，
        oldNode.n = minKeyNum; //
        createNode.n = minKeyNum;

        //开始处理这个节点的上移处理
        //新节点添加进去，因为之前的old节点已经在父亲的列表里面了
        node.insertChild(index + 1, createNode);
        node.insertKey(index, oldNode.datas.get(minKeyNum));// ?


    }

    // ==== 插入流程结束，

    // === 节点删除开始 ====

    /**
     * 删除逻辑流程
     * 1, 插入的时候，我们保证递归某个节点之前，该节点的不能是满的。删除的时候，我们要保证，当递归调用到某结点x时，x中关键字的个数至少为最小度数t
     *
     *
     *
     * 1. 如果关键字k在结点x中，并且x是叶结点，则从x中删除k。
     * 2. 如果关键字k在结点x中，并且x是内部结点，则做以下操作：
     *
     *
     *
     *
     *
     *
     *
     *
     */

    /**
     * @param key
     */
    public void delete(E key) {
        delete(root, key);
    }

    /**
     * @param node
     * @param key
     */
    public void delete(BTNode node, E key) {

    }

    // === 节点删除结束操作====


    /**
     * 数据节点
     */
    private class BTNode {

        // 关键字个数
        public int n = 0;

        private List<E> datas = new ArrayList<E>(maxKeyNum); //数据区域

        private List<BTNode> children = new ArrayList<BTNode>(); //子孩子的指针区域，

        private boolean isLeaf = true;

        private boolean isFailNode = false;

        public void insertKey(int i, E key) {
            //在数据区域某一位置插入数据，
            datas.add(i, key);
            n++;
            if (datas.size() > maxKeyNum) {
                //数据区域大了，
                datas.remove(maxKeyNum);//直接删除最后插入的数据
            }

        }

        public void insertChild(int index, BTNode child) {
            children.add(index, child);
            if (children.size() > maxKeyNum + 1) {
                children.remove(maxKeyNum + 1);
            }
        }

    }

    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<Integer>(3);
        int[] array = new int[]{6, 1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 3, 2, 12, 1, 7, 8, 8, 6, 3, 6, 21, 5, 15, 15, 6, 32, 23, 45, 65, 7, 8, 6, 5, 4};
        for (int i : array) {
            bTree.insert(i);
        }
        bTree.print();
    }

}
