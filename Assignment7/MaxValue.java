public class MaxValue{

    public static int findMaxValue(int[] arr) throws InterruptedException {
        int len = arr.length;
        int max = Integer.MIN_VALUE;

        MaxValueThread[] maxValueThread = new MaxValueThread[4];
        for (int i = 0; i < 4; i++) {
            maxValueThread[i] = new MaxValueThread(arr, (i * len) / 4, ((i + 1) * len / 4));
            maxValueThread[i].start();
        }
        for (int i = 0; i < 4; i++) {
            maxValueThread[i].join();
            max = Math.max(max, maxValueThread[i].getMax());
        }
        return max;
    }
    
    public static void main(String args[]) throws InterruptedException {
        int len = 100;
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            int random = (int)(Math.random() * len);
            arr[i] = random;
            //System.out.println(arr[i]);
        }
        int max = findMaxValue(arr);
        System.out.println("1. Max value is : " + max);
    }
}
class MaxValueThread extends Thread {
    private int low;
    private int high;
    private int[] arr;
    private int max = Integer.MIN_VALUE;
    public int getMax() {
        return this.max;
    }
    public MaxValueThread(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = high;
    }
    public void run() {
        for (int i = low; i < high; i++) {
            max = Math.max(max, arr[i]);
        }
    }
}