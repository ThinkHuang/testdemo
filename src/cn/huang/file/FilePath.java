package file;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 本类专注做java项目路径测试
 * @author wrd
 *
 */
public class FilePath
{
    public static void main(String[] args) throws IOException
    {
        showpath();
    }

    /**
     * 展示类路径
     * @throws IOException 
     */
    private static void showpath() throws IOException
    {
        // 第一种：获取类加载的根路径  D:\eclipse\workspace\concreteproject\bin
        File rootClass = new File(FilePath.class.getResource("/").getPath());
        System.out.println(rootClass);
        
        // 第二种：获取当前类所在工程的完整绝对路径  D:\eclipse\workspace\concreteproject\bin\cn\huang\file
        File absolutePath = new File(FilePath.class.getResource("").getPath());
        System.out.println(absolutePath);
        
        // 第三种：获取项目根路径  D:\eclipse\workspace\concreteproject
        File root = new File(""); 
        String coursePath = root.getCanonicalPath();
        System.out.println(coursePath);
        
        // 第四种：获取系统属性user.dir  D:\eclipse\workspace\concreteproject
        System.out.println(System.getProperty("user.dir"));
        
        // 第五种：获取加载器所在的相对路径，也就是文件系统能够访问的url地址  file:/D:/eclipse/workspace/concreteproject/bin/
        URL xmlpath = FilePath.class.getClassLoader().getResource("");
        System.out.println(xmlpath);
        
        // 第六种：获取系统属性java.class.path,所有的类加载路径
        System.out.println(System.getProperty("java.class.path"));
    }
}
