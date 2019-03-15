package inheritance;

public class Son extends Father
{
    
    public void switchon()
    {
        super.switchon();
    }
    
    public static void main(String[] args)
    {
        new Son().switchon();
    }
}
