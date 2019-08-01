package selfabuse;

import java.util.Random;

public class Hamlet {
    
    public static void main(String[] args) {
        Random random = new Random();
        boolean toBe = random.nextBoolean();
        // 在三目运算符中，不要使用混合类型。会导致意想不到的结果
        Number result = (toBe || !toBe) ? new Integer(3) : new Float(1);
        System.out.print(result);
    }
}
