package string;

public class Intern
{
    public static void main(String[] args) {

//        test1();
        test2();
//        test3();
    }
    
    private static void test3()
    {
        /**
         * 在运行时常量池上面创建两个字面量he和llo,在堆上面创建三个对象，new String("he")、new String("llo")和new String("hello")
         * 并且在字符串常量池中保持了对he和llo字面量的引用，s1指向new String("hello")对象
         */
        String s1 = new String("he") + new String("llo");
        /**
                    * 创建两个字面量h和ello，在堆上创建三个对象，new String("h")、new String("ello")和new String("hello")，这是由于
        * 并且在字符串常量池中保持了对h和ello字面量的引用，s1指向了新的new String("hello")对象。
         */
        String s2 = new String("h") + new String("ello");
        System.out.println(s1 == s2);//false
        /**
         * 此时在字符串常量池中不包含对new String("hello")的引用，这个时候直接将StringTable指向s1的new String("hello")，然后s3就指向了s1的new String("hello")
         * 并且是字符串常量池的引用
         */
        String s3 = s1.intern();
        /**
         * 此时包含了指向new String("hello")的引用，直接返回s1的new String("hello")引用。
         */
        String s4 = s2.intern();
        System.out.println(s1 == s3);// true
        System.out.println(s1 == s4);// true
        System.out.println(s2 == s3);// false
        System.out.println(s2 == s4);// false
        System.out.println(s3 == s4);// true
    }

    private static void test1()
    {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }

    private static void test2()
    {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        // 如果是s = s.intern()那么s == s2返回为true
        System.out.println(s == s2);// false

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        // 如果 s3 = s3.intern()那么s3 == s4返回为true
        System.out.println(s3 == s4); // false
    }
    
    
}
