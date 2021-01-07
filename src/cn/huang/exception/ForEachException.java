package exception;

import org.junit.Test;

public class ForEachException {
    
    @Test
    public void method() {
        for (int i = 0; i < 10; i++) {
            if(i % 2 == 1) {
                throw new RuntimeException("异常了");
            }
            System.out.println(i);
        }
    }
}
