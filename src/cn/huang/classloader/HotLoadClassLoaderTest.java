package classloader;

public class HotLoadClassLoaderTest {
    
    public static void main(String[] args) {
        new Thread(new MsgHandler()).start();
    }
    
    /**
     * 这里暂时无法完成类的热加载，因为，由于双亲委派模型的存在，因为sun.misc.Launcher$AppClassLoader能够加载到MyManager的类class.
     * 要想热更新，必须要让AppClassLoader不能加载到MyManager
     */
}
