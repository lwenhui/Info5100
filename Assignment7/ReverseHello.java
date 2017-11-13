public class ReverseHello {
    public static void main(String args[])throws InterruptedException  {
        creatThread(1);
    }
    public static void creatThread(int index) throws InterruptedException {
        if (index > 50) {
            return;
        }
        ReverseHelloThread reverseHello = new ReverseHelloThread(index);
        creatThread(index + 1);

        reverseHello.start();
        reverseHello.join();
        
    }
}
class ReverseHelloThread extends Thread {
    private int index;
    public ReverseHelloThread(int index) {
        this.index = index;
    }
    public void run() {
        System.out.println("Hello from Thread " + this.index);
    }
}