package classloader;

/**
 * 后台监控线程，一直需要轮询查询类文件是否被修改
 *
 */
public class MsgHandler implements Runnable {
    
    @Override
    public void run() {
        while(true) {
            BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
            manager.logic();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
