package collection;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

/**
 * 中文排序算法
 *
 */
public class ChineseSort {
    
    @Test
    public void test() {
        List<String> names = new ArrayList<>(Arrays.asList("张小刘", "zab", "李循环", "aa", "红旗"));
        Collections.sort(names, (String o1,String o2) -> Collator.getInstance(Locale.CHINESE).compare(o1,o2));
        names.forEach(System.out::println);
    }
    
}
