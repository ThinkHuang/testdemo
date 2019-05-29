package algorithm;

import org.junit.Test;

/**
 * 场景是：一个字符串进行算数运算后输出指定位数的字符串
 * @author Administrator
 *
 */
public class StringToNumber
{
    
    @Test
    public void test()
    {
        String str = "0002";
        Integer seq = Integer.valueOf(str);
        seq++;
        System.out.println(seq);
        String second = String.format("%04d", seq);
        System.out.println(second);
    }
}
