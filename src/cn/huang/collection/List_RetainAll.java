package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试List的retainAll方法
 * @author Administrator
 *
 */
public class List_RetainAll
{
    
    public static void main(String[] args)
    {
        List<String> source = new ArrayList<>();
        source.add("a");
        source.add("b");
        source.add("c");
        source.add("d");
        List<String> target = new ArrayList<>();
        target.add("c");
        target.add("d");
        target.add("e");
        target.add("f");
        
        // 保留两个集合中存在的重叠元素，并删除掉源集合中的非重叠元素
        target.retainAll(source);
        for (String s : source)
        {
            System.out.println("s:" + s);
        }
        
        for (String t : target)
        {
            System.out.println("t:" + t);
        }
        
    }
}
