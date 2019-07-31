package math;
/**
 * 无符号的乘法运算规则为：U * V mod w
 * U代表了b1，V代表了b2，而w代表了计算结果能够表示的位数，因为这里byte只能表示8个二进制位
 * 因此，w表示8。最后计算的结果也为12，自动截取了高位
 * @author huangyejun
 *
 */
public class UnsignedMultiplication
{
    public static void main(String[] args)
    {
        byte b1 = (byte)254;
        byte b2 = (byte)250;
        System.out.println(b1 * b2);
    }
}
