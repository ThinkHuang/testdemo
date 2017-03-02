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
		//����Ĵ���û��ִ�У�˵����treeset����������ʱ������ʹ�õ�equals������ 
		boolean result = super.equals(obj); 
		System.out.println(result); 
		return result; 
	} 

}	
/**
 * ������˵����ͳ���ļ��е���ͬ���ֵĴ���������
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
		//�����TreeSet���������Լ��ıȽ����ģ����ǣ���ֻ����Ի����������ͣ��������ܹ���������ͣ���������������;ͱ����Լ�����Ƚ���
		TreeSet<User> sortedResult = new TreeSet<User>(
			new Comparator(){
				//��������Ƚ����������ǽ�������ֵĴ������бȽ�
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
		//set��list�͵�ֵMap�������Լ��ĵ�������
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

