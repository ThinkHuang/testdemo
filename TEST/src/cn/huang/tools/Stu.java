package cn.huang.tools;

public class Stu {
	private int id;
	private String name;
	private int age;
	private String gender;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "stu [age=" + age + ", gender=" + gender + ", id=" + id
				+ ", name=" + name + "]";
	}
	public Stu(int id, String name, int age, String gender) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public Stu() {
	}
	
}
