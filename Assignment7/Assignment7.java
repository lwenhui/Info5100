/* Good Work
 * Total score 10
 */
import java.util.ArrayList;

public class Assignment7 {
    public static void main (String args[]) {
        //4. Pascal’s triangle
        PascalTriangle pascal = new PascalTriangle();
        System.out.println("4. Pascal’s triangle");
        pascal.printPascalTriangle(6);

        //5. Determine whether a given array can be partitioned into two subsets such that the sum of elements in both subsets is same.(score 2) Examples
        int[] arr = {1, 5, 11, 5};
        Partition partition = new Partition();
        System.out.print("5. Array can be partitioned? : ");
        System.out.println(partition.findPartition(arr));
    }
}
////4. Pascal’s triangle
class PascalTriangle {
    public void printPascalTriangle(int n){ // score 2
        //your code
        if (n < 1) {
            return;
        }
        ArrayList<ArrayList<Integer>> pascal = new ArrayList<>();
        ArrayList<Integer> preline = new ArrayList<>();
        preline.add(1);
        pascal.add(preline);
        for (int i = 1; i < n; i++) {
            ArrayList<Integer> curLine = new ArrayList<>();
            curLine.add(1);
            for (int j = 1; j <= preline.size() - 1; j++) {
                curLine.add(preline.get(j - 1) + preline.get(j));
            }
            curLine.add(1);
            pascal.add(curLine);
            preline = curLine;
        }
        for (ArrayList<Integer> line : pascal) {
            for (Integer num : line) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
//5.Determine whether a given array can be partitioned into two subsets
class Partition{
    //Method1: used for when arr's length and sum are not very big
    public boolean findPartition (int arr[]) {
        
        //your code
        if (arr == null || arr.length == 0) {
            return true;
        }
        if (arr.length == 1 && arr[0] != 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        return isSubsum(arr, arr.length, sum);
    }
    private boolean isSubsum (int[] nums, int len, int sum) {
        if (sum == 0) {
            return true;
        }
        if (len == 0 && sum != 0) {
            return false;
        }

        if (nums[len - 1] > sum) {
            return isSubsum(nums, len - 1, sum);
        }
        return isSubsum(nums, len - 1, sum) || isSubsum(nums, len - 1, sum - nums[len - 1]);
    }


//    //Method2: used when all numbers in arr are positive
//    public boolean findPartition (int arr[]) {
//        if (arr == null || arr.length == 0) {
//            return true;
//        }
//        int sum = 0;
//        for (int i = 0; i < arr.length; i++) {
//            sum += arr[i];
//        }
//        if (sum % 2 != 0) {
//            return false;
//        }
//        sum = sum / 2;
//        boolean[] dp = new boolean[20000];
//        for (int i = 0; i <= sum; i++) {
//            dp[i] = false;
//        }
//        dp[0] = true;
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = sum; j >= arr[i]; j--) {
//                dp[j] = dp[j] || dp[j - arr[i]];
//            }
//        }
//        return dp[sum];
//    }

}
