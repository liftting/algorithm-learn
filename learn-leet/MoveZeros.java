/**
 * 283
 * MoveZeros
 */
public class MoveZeros {

    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 0, 3, 0, 4, 5};
        MoveSolution solution = new MoveSolution();
        solution.moveZeroes(num);

        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i]);
        }

    }

    public static class MoveSolution {
        public void moveZeroes(int[] nums) {
            if (nums == null) return;
            int size = nums.length;
            if (size <= 1) return;

            int i = 0;
            int j = 1;

            while (i <= j && j < size) {

                if (nums[i] == 0 && nums[j] != 0) {
                    //change
                    change(i, j, nums);
                }

                if (nums[i] != 0) {
                    i++;
                }
                j++;

            }
        }

        public void change(int x, int y, int[] nums) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }

    }

}
