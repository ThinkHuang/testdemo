package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试map的负索引
 * @author Administrator
 *
 */
public class MapNegativeIndex {
    
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");
        
        map.get(-1);
    }
}
