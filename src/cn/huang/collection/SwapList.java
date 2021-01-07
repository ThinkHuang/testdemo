package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SwapList {
    
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("张小刘", "zab", "李循环", "aa", "红旗"));
        Collections.swap(names, 2, 0);
        names.forEach(System.out::println);
        names.add(0, "张三");
        names.forEach(System.out::println);
    }
}
