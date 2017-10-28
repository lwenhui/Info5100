public class MinMoves { // score 7
    public static void main(String args[]) {
        int[] nums = {3, 6, 7};
        MinMoves move = new MinMoves();
        int n = move.minMoves(nums);
        System.out.println(n);
    }
    public int minMoves(int[] nums){
        //your code
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        return sum - min * nums.length;
    }
}
