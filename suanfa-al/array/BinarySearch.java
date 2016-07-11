package array;

import easy.CommonUtil;

/**
 * Created by wm on 16/6/8.
 * <p/>
 * 数组的二分查询，写法注意点：
 * 1，right的判断注意
 * <p/>
 * right = middle -1 >> while(left <= right)
 * <p/>
 * right = middle  >> while(left < right)
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        CommonUtil.show(binarySearchTwo(data, 9, 2));
        CommonUtil.show(binarySearchTwo(data, 9, 3));
        CommonUtil.show(binarySearchTwo(data, 9, 7));
        CommonUtil.show(binarySearchTwo(data, 9, 8));
    }

    public static int binarySearchOne(int array[], int n, int value) {
        int left = 0;
        int right = n - 1;
        //如果这里是int right = n 的话，那么下面有两处地方需要修改，以保证一一对应：
        //1、下面循环的条件则是while(left < right)
        //2、循环内当 array[middle] > value 的时候，right = mid

        while (left <= right)  //循环条件，适时而变
        {
            int middle = left + ((right - left) >> 1);  //防止溢出，移位也更高效。同时，每次循环都需要更新。

            if (array[middle] > value) {
                right = middle - 1;  //right赋值，适时而变
            } else if (array[middle] < value) {
                left = middle + 1;
            } else
                return middle;
            //可能会有读者认为刚开始时就要判断相等，但毕竟数组中不相等的情况更多
            //如果每次循环都判断一下是否相等，将耗费时间
        }
        return -1;
    }

    public static int binarySearchTwo(int array[], int n, int value) {
        int left = 0;
        int right = n - 1;
        while (left < right) { //如果只有一个元素时，这里判断就需要在进行一次查找判断，
            int middle = (left + right) / 2;

            if (array[middle] > value) {
                right = middle;  //right赋值，适时而变
            } else if (array[middle] < value) {
                left = middle + 1;
            } else
                return middle;

        }

        return -1;
    }


}
