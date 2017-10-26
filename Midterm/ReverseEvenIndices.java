public class ReverseEvenIndices {
    public static void main(String args[]) {
        ReverseEvenIndices reverse = new ReverseEvenIndices();
        //int[] nums = {9, 4, 8, 7, 5, 1, 3};
        //int[] nums = {6, 4, 1, 0, 3, 2};
        int[] nums = {1, 2, 3};
        reverse.reverseEvenIndices(nums);
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }
    public int[] reverseEvenIndices(int[] nums){
        // your code
        if (nums == null || nums.length == 0) {
            return nums;
        }
        for (int i = 0,j = nums.length - 1; i < j; i += 2, j --) {
            if (j % 2 != 0) {
                j -= 1;
            }
            swap(nums, i, j);

        }
        return nums;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
