package easy;

import java.util.List;

/**
 * Created by wm on 16/1/26.
 */
public class CommonUtil {

    public static void show(String data) {
        System.out.println(data);
    }

    public static void show(int data) {
        System.out.println(data);
    }

    public static void show(boolean data) {
        System.out.println(data);
    }

    public static void show(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void show(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            int[] col = data[i];
            for (int j = 0; j < col.length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void show(int[] data, int index) {
        for (int i = index; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void show(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ",");
        }
        System.out.println();
    }

    public static void show(double a) {
        System.out.println(a);
    }

    public static void showInteger(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ",");
        }
        System.out.println();
    }

    public static void showList(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<Integer> cur = list.get(i);
            for (Integer in : cur) {
                System.out.print(in + ",");
            }
            System.out.println();
        }

    }

    public static void showStringList(List<List<String>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<String> cur = list.get(i);
            for (String s : cur) {
                System.out.print(s + ",");
            }
            System.out.println();
        }

    }

    public static int[][] buildArrayMap() {
        int[][] data = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        return data;
    }

}
