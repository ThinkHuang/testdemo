package concurrency;
/**
 * 漏桶算法
 * @author huangyejun
 *
 */
public class LeakyBucket
{
    // 时间刻度
    private static long time = System.currentTimeMillis();
    
    // 桶里面现在的水
    private static int water = 0;
    // 桶的大小
    private static final int size = 10;
    // 出水率,这个出水率是每秒3滴
    private static final int rate = 3;
    
    public static boolean grant()
    {
        //计算1秒内的出水量
        long now = System.currentTimeMillis();
        int out = (int)((now - time) / 1000 * rate);
        
        //漏水后桶内剩余量
        water = Math.max(0, water - out);
        time = now;
        if(water + 1 < size)
        {
            water++;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 漏桶算法的缺陷在于，如果出现瞬时大流量的话，会造成大量请求的丢失。所以，使用令牌桶来解决。
     * 疑问在于：如何保证请求处理速率？使用队列？
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
