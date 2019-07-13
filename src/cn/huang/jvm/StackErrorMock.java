package jvm;

/**
 * 测试栈深度不是固定的
 * @author Administrator
 *
 */
public class StackErrorMock
{
    private static int index = 1;
    
    public void call(){
        index++;
        call();
    }
 
    /**
     * 注意StackOverflowError不属于Exception，而是属于Throwable。继承体系为：Throwable->error->..->StackOverflowError
     * @param args
     */
    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        }catch (Throwable e){
            System.out.println("Stack deep : "+index);
            e.printStackTrace();
        }
    }
}
