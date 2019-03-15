/**
 * 
 */
package entity;


/**
 * @author dell
 *
 */
public class QuestionNaire {
	
	private String username;
	private String password;
	
	public QuestionNaire(){
		this.username = "zhangsan";
		this.password = "123456";
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
}
