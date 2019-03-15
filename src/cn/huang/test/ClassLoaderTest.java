package test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceof关键字演示
 * 
 * @author huangyejun
 *
 */
public class ClassLoaderTest {
	public static void main(String[] args) throws Exception{
		ClassLoader myLoader = new ClassLoader(){
			public Class<?> LoadClass(String name) throws ClassNotFoundException{
				try {
					String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(filename);
					if (is == null){
						return super.loadClass(name);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name ,b ,0 ,b.length);
				} catch (IOException e) {
					// TODO: handle exception
					throw new ClassNotFoundException(name);
				}
			}
		};
		Object obj = myLoader.loadClass("cn.huang.test.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof test.ClassLoaderTest);
	}
}
