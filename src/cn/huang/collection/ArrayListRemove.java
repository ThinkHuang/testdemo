package collection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 测试ArrayList删除元素时，使用forEach语法糖导致的异常。
 * 强调使用普通循环还是迭代器进行遍历时，取决于是否实现了RandomAccess接口，对于顺序访问来说，使用迭代器循环进行遍历的效果可能更好，
 * 因为，迭代器是使用指针标识下一个元素的，而如果是随机访问那么使用普通循环效果会更好，使用数组能够通过下标进行快速查找
 * @author Administrator
 *
 */
public class ArrayListRemove
{
    @Test
    public void test()
    {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        // for (String item : list) // 使用Iteratable的语法糖，这个时候会抛出java.util.ConcurrentModificationException
        for(int i = 0; i < list.size(); i++)// 使用该种方式是正常的遍历，所以不会导致出现异常。
        {
            if ("2".equals(list.get(i)))
            {
                list.remove(list.get(i));
            }
        }
        assert list.size() == 2:"删除失败";
    }
}
