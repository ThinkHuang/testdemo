package test;

public class InitTest {  
    public static int k = 0;  
    public static InitTest t1 = new InitTest("t1");  
    public static InitTest t2 = new InitTest("t2");  
    public static int i = print("i");  
    public static int n = 99;  
    public int j = print("j");  
    {  
        print("����");  
    }  
    static {  
        print("��̬");  
    }  
  
    public InitTest(String str) {  
        System.out.println((++k) + ":" + str + "    i=" + i + " n=" + n);  
        ++i;  
        ++n;  
    }  
  
    public static int print(String str) {  
        System.out.println((++k) + ":" + str + "    i=" + i + " n=" + n);  
        ++n;  
        return ++i;  
    }  
  
    public static void main(String[] args) {  
        InitTest t = new InitTest ("init");  
    }  
    
}  