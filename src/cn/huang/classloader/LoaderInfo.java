package classloader;

/**
 * 类加载信息记录
 *
 */
public class LoaderInfo {
    
    /**
     * 自定义加载器实例
     */
    private ClassLoader myClassLoader;
    
    /**
     * 记录每次加载的时间
     */
    private long loadTime;
    
    /**
     * 要加载的类接口
     */
    private BaseManager manager;
    
    public LoaderInfo(ClassLoader myClassLoader, long loadTime) {
        super();
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }
    
    public ClassLoader getMyClassLoader() {
        return myClassLoader;
    }
    
    public void setMyClassLoader(ClassLoader myClassLoader) {
        this.myClassLoader = myClassLoader;
    }
    
    public long getLoadTime() {
        return loadTime;
    }
    
    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }
    
    public BaseManager getManager() {
        return manager;
    }
    
    public void setManager(BaseManager manager) {
        this.manager = manager;
    }
    
}
