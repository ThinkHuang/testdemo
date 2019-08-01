package selfabuse;

import java.util.HashSet;
import java.util.Set;


public class ShortSet {
    
    public static void main(String[] args) {
        Set<Short> s = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            s.add(i);
            /*
             *  运算符在java环境中都会转换为int型，因此，其hashCode生成的方法不会一样
             *  remove方法接受的参数时Object而不是E，类似的还有Map.get()和Collections.contains()
             */
            s.remove(i - 1); 
        }
        System.out.println(s.size());
    }
    
}
