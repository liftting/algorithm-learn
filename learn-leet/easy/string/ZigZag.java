package easy.string;

import easy.CommonUtil;

/**
 * Created by wm on 16/1/27.
 * <p/>
 * #6 code
 * 1   5
 * 2 4 6
 * 3   7
 * <p/>
 * 1     7
 * 2   6 8
 * 3 5   9
 * 4     10
 * <p/>
 * 1 3 6
 * 2 4 5
 * <p/>
 * 1       9
 * 2     8 10
 * 3   7   11
 * 4 6
 * 5
 */
public class ZigZag {

    public static void main(String[] args) {
        String data = handle("PAYPALISHIRING", 1);
        CommonUtil.show(data);
    }


    private static String handle(String s, int numRows) {

        if (s == null || s.length() == 0 || numRows <= 0) return "";

        if (numRows == 1) return s;

        int length = s.length();

        int pageNum = numRows + numRows - 2;
        int totalPage = length / pageNum;

        if (s.length() % pageNum != 0)
            totalPage++;

        //多构造出来page
        // each unit is a rectangle
        int rows = numRows; // hang
        int cols = totalPage * (numRows - 1); // lie
        char[][] map = new char[rows][cols];

        int x = 0;

        int digt = 0;

        for (int k = 1; k <= totalPage; k++) {

            int startlie = (k - 1) * (numRows - 1);

            x = 0;

            for (int i = 0; i < pageNum; i++) {
                //每个有这多数
                if (i <= numRows - 1) {
                    x = i;
                } else {
                    startlie++;
                    x = x - 1;
                }

                if (digt < length) {
                    map[x][startlie] = s.charAt(digt);
                    digt++;
                }
            }


        }


        StringBuilder sb = new StringBuilder();
        for (int dd = 0; dd < rows; dd++) {
            for (int yy = 0; yy < cols; yy++) {
                if (map[dd][yy] != 0) {
                    sb.append(map[dd][yy]);
                }
            }

        }

        return sb.toString();
    }

}
