package cn.huang.gc;

public class DiedsynchronizedTest
{
    public static void main(String[] args)
    {
        Thread a = new ThreadRunA();
        Thread b = new ThreadRunB();
        a.start();
        b.start();
    }
}

class ThreadRunA extends Thread
{
    public void run()
    {
        System.out.println("================A===================");
        synchronized (A.A)
        {
            System.out.println("我要开始执行任务A。。。。" + Thread.currentThread().getName());
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            synchronized (B.B)
            {
            }
            System.out.println("我在执行任务结束了A。。。。" + Thread.currentThread().getName() + ":" + B.B.hashCode() + ":" + A.A.hashCode());
        }
    }
}

class ThreadRunB extends Thread
{
    public void run()
    {
        System.out.println("================B===================");
        synchronized (B.B)
        {
            System.out.println("我要开始执行任务B。。。。" + Thread.currentThread().getName());
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            synchronized (A.A)
            {
            }
            System.out.println("我在执行任务结束了B。。。。" + Thread.currentThread().getName() + ":" + B.B + ":" + A.A);
        }
    }
}

class A
{
    static Integer A = new Integer(1);
}

class B
{
    static Integer B = new Integer(1);
}
