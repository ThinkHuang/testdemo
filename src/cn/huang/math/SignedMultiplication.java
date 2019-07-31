package math;

/**
 * 计算有符号的乘法运算法则：使用补码来对有符号的数字进行计算，做到了再各种平台上统一的实现。
 * @author huangyejun
 *
 */
public class SignedMultiplication
{
    public static void main(String[] args)
    {
        byte b1 = (byte)-300;
        /**
         *  解释这里为什么b1=-44?
         *  -300的byte位为：1101 0100，前面所有的未舍弃，然后计算
         *  第一位为符号位同时也是数字位，代表了-128，然后+64+16+4=-44
         */
        System.out.println(b1);
        b1 = (byte)-254;
        System.out.println(b1);
        byte b2 = (byte)250;
        int i = b1 * b2;
        /**
         *  相当于 2 * 250 = 500然后其二进制位为：0000 0001 1111 0100
         *  接下来舍弃前8位，计算后面8位，计算规则为：
         *  -128 + 64 + 32 + 16 + 4 = -12
         */
        System.out.println(i);
    }
}
