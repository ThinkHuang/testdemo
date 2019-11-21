package bytecode;

/**
 * 本例测试try-catch-finally字节码生成，并去重看java中的丢失的“违例”。
 * @author Administrator
 *
 */
public class TryFinallyExhibit {
    
    
    public static void main(String[] args) {
        System.out.print(foo());
    }
    
    public static int foo() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            return x;
        }
    }
}
