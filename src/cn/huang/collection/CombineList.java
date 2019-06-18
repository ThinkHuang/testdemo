package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

/**
 * 合并两个列表的元素，获取并集
 *
 */
public class CombineList
{
    
    @Test
    public void test()  
    {
        // 注意这里的Arrays.asList()返回的是Arrays中的内部类，不具备java.util中ArrayList的方法和属性
        List<String> list1 = new ArrayList<>(Arrays.asList("1", "2", "3"));
        List<String> list2 = new ArrayList<>(Arrays.asList("1", "2", "4", "5", "6"));
        list2.addAll(list1);
        System.out.println(new HashSet<>(list2));
    }
}
