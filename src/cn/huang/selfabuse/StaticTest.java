package selfabuse;

public class StaticTest {    
    
    private static StaticTest st = new StaticTest();    
    public static int count1;    
    public static int count2 = 0;    
        
    public static StaticTest getInstance()    
    {    
        return st;    
    }    
        
    private StaticTest()    
    {    
        count1++;    
        count2++;    
    }    
   
    public static void main(String[] args) {    
        //静态变量的执行顺序在程序中是从上到下顺序执行的，因此在调用StaticTest.getInstance()时执行过程是：    
        //private static StaticTest st = new StaticTest();-->private StaticTest(){}-->public static int count1;-->public static int count2 = 0;    
        StaticTest st = StaticTest.getInstance();    
        System.out.println("count1:" + st.count1);//输出为1    
        System.out.println("count2:" + st.count2);//输出为0    
    }    
   
}    