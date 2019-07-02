package other;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import test.Person;

/**
 * 验证将Map加入到List中，然后修改Map中的属性，是否需要重新将value插入map中
 * @author Administrator
 *
 */
public class MapReference
{
    @Test
    public void test()
    {
        Map<String, Person> map = new HashMap<>();
        Person person = new Person();
        person.setUsername("张三");
        person.setPassword("123");
        map.put("11", person);
        
        Person dummy = map.get("11");
        dummy.setUsername("李四");
        System.out.println("name:" + map.get("11").getUsername());
    }
}

class person
{
    private String name;
    
    private String password;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "person [name=" + name + ", password=" + password + "]";
    }
    
}
