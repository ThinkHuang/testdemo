package string;

import java.text.DecimalFormat;
import java.text.Format;

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
        Format format = new DecimalFormat("0.00");
        System.out.println(format.format(600D / 7));
    }
}
