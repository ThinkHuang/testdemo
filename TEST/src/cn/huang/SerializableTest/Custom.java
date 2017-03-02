package cn.huang.SerializableTest;

import java.io.Serializable;

public class Custom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5440554556445545620L;
	private int age;
	private String name;
	private String station;
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString(){
		return "name:" + name +",age" + age + ",station" + station;
	}

	
}
