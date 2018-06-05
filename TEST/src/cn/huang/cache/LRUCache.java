package cn.huang.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用LinkedHashMap实现LRU缓存
 * @author huangyejun
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V>
{
    
    private int cacheSize;
    
    public LRUCache(int cacheSize)
    {
        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
    }
    
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
    {
        return size() >= cacheSize;
    }
}