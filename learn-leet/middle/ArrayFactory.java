package middle;

/**
 * Created by wm on 16/4/1.
 */
public class ArrayFactory {

    public static char[][] buildCharArray(int x, int y, char[] data) {
        char[][] array = new char[x][y];
        // x 行 y列

        for (int i = 0; i < data.length; i++) {
            array[i / y][i % y] = data[i];
        }

        return array;

    }

}
