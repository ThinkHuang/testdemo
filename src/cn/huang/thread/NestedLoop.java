package thread;

/**
 * 如果不处理InterruptedException，将可能会导致线程无法停止
 * 当线程处于sleep,wait或者被占用的状态时，另外的线程中断了该线程，那么就会立刻抛出InterruptedException
 * 
 * @author Administrator
 *
 */
public class NestedLoop extends Thread {
    private static boolean correct = true;
    
    public void run() {
        while (true) {
            System.out.print(".");
            System.out.flush();
            for (int i = 0; i < 10; i++) {
                System.out.print("#");
                System.out.flush();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    if (correct)
                        Thread.currentThread().interrupt();
                    System.out.println();
                    System.out.println("Shut down inner loop");
                    break;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                if (correct)
                    Thread.currentThread().interrupt();
                System.out.println();
                System.out.println("Shut down outer loop");
                break;
            }
        }
        System.out.println("Shutting down thread");
    }
    
    private static void test() throws InterruptedException {
        Thread t = new NestedLoop();
        t.start();
        Thread.sleep(6500);
        t.interrupt();
        t.join();
        System.out.println("Shutdown the thread correctly");
    }
    
    public static void main(String[] args) throws InterruptedException {
        test();
        correct = false;
        test();
    }
}
