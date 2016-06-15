package list;

import java.util.Iterator;
import java.util.List;

/**
 * Created by wm on 16/6/15.
 *
 * 数组链表结构，
 *
 */
public class ListLearn {


    /**
     * 算法效率分析
     *
     */

    /**
     * 这个效率很低，不管是 ArrayList  还是  LinkedList，
     * ArrayList： remove 效率低， 要移动 O(N)
     * LinkedList： get() 效率低， remove效率低，因为需要到达i 的位置，
     * <p/>
     * <p/>
     * 优化：
     * 使用迭代器，对于链表不用再进行查找耗时，
     *
     * @param list
     */
    public static void removeElements(List<Integer> list) {

        int i = 0;
        while (i < list.size()) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            } else {
                i++;
            }
        }

    }

    public static void removeOneNotGood(List<Integer> list) {
        for (Integer x : list) {
            if (x % 2 == 0) {
                list.remove(x); // 使用迭代遍历时，直接删除有异常， 这个也不高效，因为remove还要重新定位查询
            }
        }
    }

    public static void removeGood(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove(); // 删除时，已经定位到了删除点附近了，
            }
        }
        // 这个对于数组来说，还是O(N2) 因为删除涉及到数组的移动操作，
    }

}
