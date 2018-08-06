package cn.huang.concurrency;

public class TokenBucket
{
    
    private static long time = System.currentTimeMillis();
    
    // 令牌产生率
    private static int createTokenRate = 3;
    // 桶的大小
    private static int size = 10;
    // 当前令牌数量
    private static int tokens = 0;
    
    public static boolean grant()
    {
        // 令牌产生速率是1ms产生三枚令牌
        long now = System.currentTimeMillis();
        int in = (int)((now - time) / 1 * createTokenRate);
        
        tokens = Math.min(size, tokens + in);
        time = now;
        if(tokens > 0)
        {
            tokens--;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 令牌的产生需要消耗额外的资源。
     * 总的来说，令牌桶对流量的限制可能是最好的，能够最大限度的限制突发的大并发量的请求
     * @param args
     */
    public static void main(String[] args)
    {
        for (int i = 0; i < 100; i++)
        {
            new Thread(new Runnable() {

                @Override
                public void run()
                {
                    if(grant())
                    {
                        System.out.println("执行业务逻辑");
                    }
                    else
                    {
                        System.out.println("限流!");
                    }
                }
                
            }).start();
        }
    }
}
