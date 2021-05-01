package br.com.carlos.modeljwt;

public class JWTRequest {
	private String username;
	private String password;
	
	public String getUserName() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JWTRequest(String username, String password) {
		this.username = username;
		this.password = password;
	} 
	
	public JWTRequest() {
		
	} 
	
	
}
