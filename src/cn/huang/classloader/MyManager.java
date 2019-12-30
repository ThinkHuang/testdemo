package classloader;

import java.time.LocalDate;

public class MyManager implements BaseManager {

    @Override
    public void logic() {
        System.out.println(LocalDate.now() + "：java类的热加载111, 当前加载该类的classLoader为：" + this.getClass().getClassLoader());
    }
    
}
