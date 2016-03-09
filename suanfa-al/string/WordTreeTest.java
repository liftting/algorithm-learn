package string;

import java.util.Iterator;

/**
 *
 */
public class WordTreeTest {

    public static void main(String[] args) {

        WordTree<Integer> wordTree = new WordTree<Integer>();

        wordTree.put("she", 1);
        wordTree.put("shell", 2);
        wordTree.put("short", 3);
        wordTree.put("sheet", 31);
        wordTree.put("sht", 32);
        wordTree.put("ban", 4);
        wordTree.put("co", 5);
        wordTree.put("bali", 6);
        wordTree.put("ct", 7);

        System.out.print(wordTree.get("she"));

        wordTree.delete("she");

        System.out.print(wordTree.get("she"));

//        Iterable<String> iterable = wordTree.keyWithPre("c");
//
//        Iterator itor = iterable.iterator();
//        while (itor.hasNext()) {
//            System.out.println(itor.next());
//        }
//
//        System.out.println(wordTree.keyWithLongest("sheddd"));

    }

}
