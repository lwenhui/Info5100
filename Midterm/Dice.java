import java.util.ArrayList;

public class Dice {
    public static void main(String args[]) { // score 10
        Dice dice = new Dice();
//        int m = 22;
//        int n = 20;
//        int x = 32;
        int m = 2;
        int n = 13;
        int x = 24;
        System.out.println("countNumberOfPossibleWays: " + dice.countNumberOfPossibleWays(m, n, x));
    }

    public int countNumberOfPossibleWays(int m, int n, int x) { //DP
        int[][] dice = new int[n + 1][x + 1];
        for (int j = 1; j <= m && j <= x; j++)
            dice[1][j] = 1;

        for (int i = 2; i <= n; i++)
            for (int j = 1; j <= x; j++)
                for (int k = 1; k <= m && k < j; k++)
                    dice[i][j] += dice[i-1][j-k];

        return dice[n][x];
    }


//    public int countNumberOfPossibleWaysByDFS(int m, int n, int x){
//        // your code
//        if (x < n || x > m * n) {
//            return 0;
//        }
//        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//        ArrayList<Integer> ways = new ArrayList<>();
//        helper(m, n, result, ways, 0, x, 1);
//        return result.size();
//    }
//    private void helper(int m, int n,
//                        ArrayList<ArrayList<Integer>> result,
//                        ArrayList<Integer> ways,
//                        int current,
//                        int target,
//                        int value) {
//
//        if (target == 0 && current == n) {
//            result.add(new ArrayList<Integer>(ways));
//            return;
//        }
//
//        for (int i = value; i <= m; i++) {
//            if (target < i) {
//                break;
//            }
//            ways.add(i);
//            helper(m, n, result, ways, current + 1, target - i, value);
//            ways.remove(ways.size() - 1);
//        }
//    }
}
