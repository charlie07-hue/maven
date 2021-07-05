package chap02;

import java.time.LocalDateTime;

public class Member {
	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	
	public Member(String email, String password, String name, LocalDateTime registerDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	public void changePassword(String oldPassword, String newPassword) { // 비번은 맘대로 바뀌면 안되니 예외 강제로 발생시키기
		if(!password.equals(oldPassword)) { // 같으면 false 같지않으면 true
			throw new WrongIdPasswordException();
		}
		this.password = newPassword;
		
	}
}
