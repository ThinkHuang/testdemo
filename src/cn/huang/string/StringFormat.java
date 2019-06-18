package string;

import org.junit.Test;

/**
 * 测试String的format方法
 * @author Administrator
 *
 */
public class StringFormat
{
    
    @Test
    public void test()
    {
        double str = 1.2356;
        System.out.println(String.format("%.2f", str));
    }
}
