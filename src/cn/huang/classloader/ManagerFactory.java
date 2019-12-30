package classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载 manager的工厂类
 *
 */
public final class ManagerFactory {
    
    /**
     * 存放类加载信息的map
     */
    private static final Map<String, LoaderInfo> loadTimeMap = new HashMap<>();
    
    /**
     * 要加载类的类路径
     */
    public static final String CLASS_PATH = "D:\\Code\\workspace\\testdemo\\bin\\";
    
    /**
     * 要加载的类的全限定名称
     */
    public static final String MY_MANAGER = "classloader.MyManager";
    
    public static BaseManager getManager(String className) {
        String qualifiedName = CLASS_PATH + className.replace(".", "\\") + ".class";
        System.out.println("类的全限定名称为：" + qualifiedName);
        File loadFile = new File(qualifiedName);
        // 获取最后一次修改的时间
        long lastModified = loadFile.lastModified();
        System.out.println("当前的类时间：" + lastModified);
        // loadTimeMap 不包含ClassName为key的信息，需要将该类进行加载
        if(loadTimeMap.get(className) == null) {
            System.out.println("路径1");
            load(className, lastModified);
        } else if(loadTimeMap.get(className).getLoadTime() != lastModified) {
            System.out.println("路径2");
            load(className, lastModified);
        } 
        return loadTimeMap.get(className).getManager();
    }

    /**
     * 加载类，并将加载信息保存
     * @param className
     * @param lastModified
     */
    private static void load(String className, long lastModified) {
        MyClassLoader myClassLoader = new MyClassLoader(CLASS_PATH, className);
        Class<?> loadClass = null;
        try {
            loadClass = myClassLoader.loadClass(className);
            System.out.println("重新加载的class信息为：" + loadClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        BaseManager manager = newInstance(loadClass);
        LoaderInfo loaderInfo = new LoaderInfo(myClassLoader, lastModified);
        loaderInfo.setManager(manager);
        System.out.println("manager的类加载器为：" + manager.getClass().getClassLoader() + " 最晚修改时间：" + lastModified);
        loadTimeMap.put(className, loaderInfo);
    }

    private static BaseManager newInstance(Class<?> loadClass) {
        try {
            return (BaseManager)loadClass.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
