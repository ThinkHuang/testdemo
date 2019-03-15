package cn.huang.gc;

public class DumpWhileDemo
{
    public static void main(String[] args)
    {
        new Thread(new WhileThread()).start();
        System.out.println("");
    }
}

class WhileThread implements Runnable
{

    @Override
    public void run()
    {
        while(true)
        {
            System.out.println("Thread");
        }
    }
    
}
