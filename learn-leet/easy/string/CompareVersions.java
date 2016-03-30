package easy.string;

import easy.CommonUtil;

/**
 * Created by wm on 16/3/29.
 * 165. Compare Version Numbers
 * <p/>
 * 0.1 < 1.1 < 1.2 < 13.37
 * <p/>
 * 2.5不是“两个半”或“半版三个“的方式,这是第五个二级修订第二个一级修正。
 */
public class CompareVersions {

    public static void main(String[] args) {
        Solution s = new Solution();
        CommonUtil.show(s.compareVersion("1.0.1", "1"));
    }

    public static class Solution {
        public int compareVersion(String version1, String version2) {

            if ((version1 == null || version1.isEmpty()) && (version2 == null || version2.isEmpty()))
                return 0;

            if ((version1 == null || version1.isEmpty()) && (version2 != null || !version2.isEmpty())) {
                return -1;
            }

            if ((version2 == null || version2.isEmpty()) && (version1 != null || !version1.isEmpty())) {
                return 1;
            }


            String[] left = version1.split("\\.");
            String[] right = version2.split("\\.");

            int len = left.length > right.length ? right.length : left.length;
            int i;
            for (i = 0; i < len; i++) {

                int lci = Integer.valueOf(left[i]);
                int rci = Integer.valueOf(right[i]);

                if (lci < rci) {
                    return -1;
                } else if (lci > rci) {
                    return 1;
                }

            }

            if (left.length == right.length) {
                return 0;
            } else if (i < left.length && !isZero(left, i)) {
                return 1;
            } else if (i < right.length && !isZero(right, i)) {
                return -1;
            }

            return 0;
        }

        private boolean isZero(String[] data, int index) {
            for (int i = index; i < data.length; i++) {
                if (Integer.valueOf(data[i]) != 0) return false;
            }
            return true;
        }
    }

}
