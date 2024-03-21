package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import tacos.User;

@Data
public class RegistrationForm {
	
	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	public User toUser(PasswordEncoder passwordEncoder) { 
		// RegistrationForm의 속성 값을 갖는 새로운 User객체 생성
		return new User(username, passwordEncoder.encode(password), 
						fullname, street, city, state, zip, phone);
	}

}
