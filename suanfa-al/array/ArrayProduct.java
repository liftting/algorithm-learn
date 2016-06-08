package array;

import easy.CommonUtil;

/**
 * Created by wm on 16/6/7.
 * 两个矩阵相乘，
 * m *n -- n * p == m * p  2 - 3 * 3 - 2  = 2 - 2
 * 1 2 3
 * 4 5 6
 * <p/>
 * *
 * <p/>
 * 1 2
 * 3 4
 * 5 6
 */
public class ArrayProduct {

    public static void main(String[] args) {
        CommonUtil.show(MulMatrix(new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        }, new int[][]{
                {1, 2},
                {3, 4},
                {5, 6}
        }));
    }

    public static int[][] MulMatrix(int[][] matrixA, int[][] matrixB) {

        int arow = matrixA.length;// 行
        int acol = matrixA[0].length;// lie

        int bcol = matrixB[0].length; //列
        int brow = matrixB.length;// 行，就是A的列

        int[][] matrixC = new int[arow][bcol];

        for (int i = 0; i < arow; i++) {
            for (int j = 0; j < bcol; j++) {

                matrixC[i][j] = 0;

                for (int k = 0; k < acol; k++) {
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }

            }
        }

        return matrixC;


    }

}
