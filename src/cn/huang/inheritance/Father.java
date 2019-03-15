package inheritance;

public class Father
{
    /**
     * 这里测试的一个主要问题是，通过子类Son的switchon方法调用后，
     * 在example中的实例this指向的是Father还是Son，经过测试
     * 实例this指向的是Son
     */
    protected Example example = new Example(this);
    
    public void switchon()
    {
        System.out.println(example.father);
    }
}
