package string;

public class SpanStringPool {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Test2 test2 = new Test2();
        
        System.out.print(test1.s == test2.s);
    }
    
}

class Test1 {
    String s = "abc";
}

class Test2 {
    String s = "abc";
}
