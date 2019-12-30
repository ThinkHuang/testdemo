package classloader;

public class Foo {
    public void hello() {
        System.out.println("hello i am a foo" + "加载该类的类加载器为：" + this.getClass().getClassLoader());
    }
}
