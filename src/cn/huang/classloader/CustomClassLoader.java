package classloader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class CustomClassLoader extends ClassLoader{
    /** 不需要双亲委派加载的类集合 */
    private Set<String> customClasses = new HashSet<>();
    
    {
        customClasses.add("classloader.Foo");
    }
 
    @Override
    protected Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
        if (customClasses.contains(className)) {
            return findClass(className);
        }
        return super.loadClass(className, resolve);
    }
    
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        return defineAppPathClass(className);
    }
    
    private Class<?> defineAppPathClass(String name) throws ClassNotFoundException {
        BufferedInputStream bis = null;
        try {
            //加载类路径下的类
            URL url = ClassLoader.getSystemClassLoader().getResource("");
            String path = url.getPath();
            if (path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }
            String packagePath = name.replaceAll("\\.", "/");
            path = path + "/" + packagePath + ".class";
            bis = new BufferedInputStream(new FileInputStream(path));
            byte[] data = new byte[bis.available()];
            bis.read(data);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            return super.findClass(name);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            
            @Override
            public void run() {
                try {
                    //创建新的类加载器，加载Foo，执行方法
                    CustomClassLoader loader = new CustomClassLoader();
                    Class<?> clz = loader.loadClass("classloader.Foo");
                    Object instance = clz.getConstructor().newInstance();
                    Method method = instance.getClass().getMethod("hello");
                    method.invoke(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("自定义加载器加载类异常");
                }
            }
        }, 0, 1000);
    }
}
