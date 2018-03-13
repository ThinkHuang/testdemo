package cn.huang.test;

public class InitTest {  
    public static int k = 0;  
    public static TestVar t1 = new TestVar("t1");  
    public static TestVar t2 = new TestVar("t2");  
    public static int i = print("i");  
    public static int n = 99;  
    public int j = print("j");  
    {  
        print("¹¹Ôì");  
    }  
    static {  
        print("¾²Ì¬");  
    }  
  
    public TestVar(String str) {  
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