public class ArrangeCoins { // score 7
    public static void main(String args[]) {
        ArrangeCoins coins = new ArrangeCoins();
        //int n = Integer.MAX_VALUE;
        int n = 22;
        //System.out.println(n);
        System.out.println(coins.arrangeCoins(n));
    }
    public int arrangeCoins(int n){
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
            if (sum == n) {
                return i;
            }
            if (sum > n) {
                return i - 1;
            }
        }
        return 0;
    }
}
