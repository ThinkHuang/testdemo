package string;

import org.junit.Test;

public class StringConstants
{
    
    @Test
    public void test()
    {
        String str1 = "str";
        String str2 = "ing";
        
        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        System.out.println(str3 == str4);// false
        String str5 = new String("string");
        System.out.println(str3 == str5);// false
        System.out.println(str4 == str5); // false
        String str6 = str3.intern();
        System.out.println(str3 == str6);// true
        /*
         * 分析上面的str3为何不和str4相等？
         * String str1 = "str"，将会在堆上产生一个字面量"str"，并且在字符串常量池中的StringTable中维持str1到"str"的引用关系。
         * String str2 = "ing"，将会在堆上产生一个字面量"ing"，并且在字符串常量池中的StringTable中维持str2到"ing"的引用关系。
         * String str3 = "str" + "ing"，由于+这样的连接操作符的关系，将会直接在堆上面生成一个字面量"string",然后再字符串常量池中StringTable中维持Str3到"string"的引用关系。
         * String str4 = str1 + str2，虽然都是使用+这样的连接操作符，但是，str1和str2属于变量，直到在运行时才能确定，其实质是生成了StringBuilder对象将字符串进行拼接后返回不可变的String对象。
         * 也就是说String str4 = str1 + str2的效果类似于String str4 = new String(str1) + new String(str2);因此str3 == str4不等。
         * 至于str4 == str5返回false，等效于String str4 = new String("string");和String str5 = new String("string");
         * 然后可以看到String str6 = str5.intern();当调用该方法后，表示的是StringTable到字面量"string"的映射关系。因此当str6改写为String str6 = str3.intern(),
         * String str6 = str4.intern();和String str6 = str5.intern()，然后str3 == str6都返回的true。
         * 因此，StringTable只是维护引用到具体字面量的映射，不会维护实例到引用的映射，例如不会维护str5到new String("string")之间的映射。
         */
        
        
        String s3 = new String("1") + new String("1");
        String s5 = s3.intern(); // s5指向的是StringTable中的11字符串值，而s3指向的是对中new String("11")对象
        System.out.println(s3 == s5);// false 
        String s4 = "11";
        System.out.println(s4 == s5);// true
        System.out.println(s3 == s4); // false
        
        
        /**
         * 澄清几个概念：
         * 字符串池：使用形如String s = "hello"或者String s = "he" + "llo";的表达式都会在字符串池中的StringTable中保留引用到hello的映射。这里的映射是全局共享的
         * class文件常量池：class文件常量池中的内容很多，且是每个类都有的。包含的内容为字面量和符号引用。
         * 运行时常量池：运行时常量池是class文件常量池的运行时表现，同样是每个类都存在的。运行时常量池相对于class文件常量池的特点是，它是可扩充的，
         * 在运行阶段可以动态的加入字面量和直接引用。并且不在存在符号引用，都会被替换为直接引用。
         */
    }
}
