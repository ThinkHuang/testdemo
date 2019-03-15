package concurrency;

public class Counter
{
    private static long timeStamp = System.currentTimeMillis();
    
    // 限制为1s内请求的次数为100次
    private static final long limitCount = 100;
    
    private static final long interval = 1000;
    
    // 已请求数
    private volatile static long reqCount = 0;
    
    public static boolean grant()
    {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval)
        {
            if (reqCount < limitCount)
            {
                reqCount++;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            timeStamp = System.currentTimeMillis();
            reqCount = 0;
            return false;
        }
    }
   
    /**
     * 限流法存在临界点的问题，假如上面的时间限制是在1分钟内只能请求100次，那么有可能在前59秒都没有请求，而最后一秒突发全部请求；
     * 而如果在第一秒的存在恶意用户过多的消耗请求次数，那么，后面的59秒都不会接受任何请求，即使是正常用户的请求也会被拒绝，这是不可行的。
     * 采用滑动窗口可以解决这个问题，
     * @param args
     */
    public static void main(String[] args)
    {
        for (int i = 0; i < 500; i++)
        {
            new Thread(new Runnable()
            {
                
                @Override
                public void run()
                {
                    if (grant())
                    {
                        System.out.println("执行业务逻辑");
                    }
                    else
                    {
                        System.out.println("限流！");
                    }
                }
                
            }).start();
        }
    }
}
