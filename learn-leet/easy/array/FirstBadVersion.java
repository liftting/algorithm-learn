package easy.array;

/**
 * Created by wm on 16/3/29.
 * 278. First Bad Version
 * <p/>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p/>
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p/>
 * 二分，中间判断， 好的，前面就都是好的，坏的划分
 */
public class FirstBadVersion {

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            long left = 1;
            long right = n;

            long mid = 0;
            while (left < right) {
                mid = (left + right) / 2;
                if (isBadVersion((int) mid)) {
                    //左边
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return (int) right;

        }
    }

    public class VersionControl {
        public boolean isBadVersion(int v) {
            return true;
        }
    }

}
