package tree;

/**
 * Created by wm on 16/2/24.
 *
 * hash表，使用链式结构，
 * java中的是顺序和链接相结合方式
 *
 */
public class SepateHashTree<Key, Value> {

    private static final int INIT_CAPACITY = 100;

    //多条链表结构
    private SequenceSearch<Key, Value>[] st;

    private int N; // 一个数组项对应链表

    private int size; // 长度大小

    public SepateHashTree() {
        this(INIT_CAPACITY);
    }

    // create separate chaining hash table with M lists
    public SepateHashTree(int N) {
        this.N = N;
        st = (SequenceSearch<Key, Value>[]) new SequenceSearch[N];
        for (int i = 0; i < N; i++)
            st[i] = new SequenceSearch<Key, Value>();
    }

    public void delete(Key key) {

        int i = hash(key);
        if (st[i].contains(key)) size--;
        st[i].delete(key);

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    }


    public void put(Key key, Value value) {
        //先hash,计算索引位置，
        int i = hash(key);

        if (!st[i].contains(key)) size++;

        st[i].put(key, value);

    }

    public Value get(Key key) {
        int i = hash(key);


        return st[i].get(key);
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % N;
    }


}
