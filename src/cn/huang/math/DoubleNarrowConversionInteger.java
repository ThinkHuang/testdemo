package math;


import org.junit.Test;

/**
 * double强制转换为integer，只会获取整数部分，看来double的存储使用的是IEEE 754浮点数存储标准，并且32位和64为会有不同的表现形式
 *
 */
public class DoubleNarrowConversionInteger
{
    
    @Test
    public void test()
    {
        Double d = 1.9999d;
        System.out.println(d.intValue());
        Double d1 = 1.0d;
        int i = 1;
        System.out.println(d1 == i);
        
        int size = 1000;
        System.out.println(size % 1000);
    }
    
    
}
