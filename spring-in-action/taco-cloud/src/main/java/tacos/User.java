package tacos;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
//@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
//@RequiredArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private final String username;
	private final String password;
	private final String fullname;
	private final String street;
	private final String city;
	private final String state;
	private final String zip;
	private final String phoneNumber;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 해당 사용자에게 부여된 권한을 저장한 컬렉션을 반환
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public boolean isAccountNonExpired() {
		//is~~Expired 메소드는 사용자 계정의 활성화 또는 비활성화 여부를 나탄는 값 반환
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
