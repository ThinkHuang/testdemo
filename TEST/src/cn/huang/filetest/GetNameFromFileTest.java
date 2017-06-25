package cn.huang.filetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;


class User{
	public String name;
	public int value;
	
	public User(String name,int value){
		this.name = name;
		this.value = value;
	}
	public boolean equals(Object obj) { 
		//下面的代码没有执行，说明往treeset中增加数据时，不会使用到equals方法。 
		boolean result = super.equals(obj); 
		System.out.println(result); 
		return result; 
	} 

}	
/**
 * 程序功能说明：统计文件中的相同名字的次数并排序。
 * @author huangyejun
 *
 */
public class GetNameFromFileTest {
	public static void main(String[] args) {
		Map result = new HashMap();
		InputStream is = GetNameFromFileTest.class.getResourceAsStream("test.txt");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line = null;
		
		try {
			while((line=br.readLine())!=null){
				dealLine(line,result);
			}
			sortResult(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void sortResult(Map result) {
		//TreeSet sortedResult = new TreeSet();
		//针对于TreeSet本身是有自己的比较器的，但是，那只是针对基本数据类型，而且是能够排序的类型，所以针对引用类型就必须自己定义比较器
		TreeSet<User> sortedResult = new TreeSet<User>(
			new Comparator(){
				//下面这个比较器的作用是将对象出现的次数进行比较
				public int compare(Object obj1,Object obj2){
					User u1 = (User) obj1;
					User u2 = (User) obj2;
					if(u1.value < u2.value){
						return -1;
					}
					else if(u1.value > u2.value){
						return 1;
					}else
						return u1.name.compareTo(u2.name);
				}
			}
		);
		//set、list和单值Map都是有自己的迭代器的
		Iterator it = result.keySet().iterator();
		while(it.hasNext()){
			String name = (String) it.next();
			int value = (Integer) result.get(name);
			if(value >= 1){
				sortedResult.add(new User(name,value));
			}
		}
		printResult(sortedResult);
	}

	private static void printResult(TreeSet<User> sortedResult) {
		// TODO Auto-generated method stub
		Iterator it = sortedResult.iterator();
		while(it.hasNext()){
			User user = (User) it.next();
			System.out.println("name:"+user.name+"value:"+user.value);
		}
	}

	private static void dealLine(String line, Map result) {
		if(!"".equals(line.trim())){
			String[] results = line.split(",");
			if(results.length == 3){
				String name = results[1];
				Integer value = (Integer) result.get(name);
				if(value==null)value=0;
				result.put(name, value+1);
			}
		}
	}

	
	
}

