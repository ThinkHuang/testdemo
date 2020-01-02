package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 自定义类加载器来实现class的热加载
 */
public class MyClassLoader extends ClassLoader {
    
    private Set<String> customClasses = new HashSet<>();
    
    /**
     * 要加载的java类的classpath路径
     */
    private String classpath;
    
    public MyClassLoader(String classpath, String className) {
        // 注意在这里需要指定父加载器
        super(ClassLoader.getSystemClassLoader());
        this.classpath = classpath;
        customClasses.add(className);
    }
    
    /**
     * 官方文档明确提示使用者不要去重写loadClass方法，该方法的前提条件是目标class不能被父加载器加载。
     * 而如果父加载器能够加载到，那么仅仅去重写findClass方法是无法完成自定义加载器加载class的，这个时候只能去重写loadClass方法，例如下面的形式。
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (customClasses.contains(name)) {
            return findClass(name);
        }
        return super.loadClass(name, resolve);
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }
    
    private byte[] loadClassData(String name) {
        FileInputStream inputStream = null;
        try {
            // name 是全限定名的类路径
            String classname = name.replace(".", "\\");
            String qualifiedName = classpath + classname + ".class";
            inputStream = new FileInputStream(new File(qualifiedName));
            // 定义字节数组输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = inputStream.read()) != -1) {
                baos.write(b);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
}
