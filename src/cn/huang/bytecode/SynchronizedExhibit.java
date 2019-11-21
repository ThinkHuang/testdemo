package bytecode;

public class SynchronizedExhibit {
    
    /**
     * 同步方法，标识方法为ACC_SYNCHRONIZED
     */
    public synchronized void method() {
        
    }
    
    /**
     * 同步代码块,添加了monitorenter和monitorexit指令
     */
    public void method1() {
        synchronized (this) {
            
        }
        
        synchronized (SynchronizedExhibit.class) {
            
        }
    }
}
