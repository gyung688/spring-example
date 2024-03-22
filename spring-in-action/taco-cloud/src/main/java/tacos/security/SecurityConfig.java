package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		//PasswordEncoder타입의 빈을 선언 후 passwordencoder()를 호출하여 이 빈을 사용자 명세 서비스 config에 주입되게 한다.
		return new BCryptPasswordEncoder();
	}
	
	// HTTP보안을 구성하는 메서드
	// HtpSecurity : 웹 수준에서 보안을 처리하는 방법을 구성.
	//		HTTP요청 처리를 허용하기 전에 충족되어야 할 특정 보안 조건을 구성.(=권한 확인. 제일 많이함.)
	//		커스텀 로그인 페이지 구성.
	//		사용자가 애플리케이션의 로그아웃을 할 수 있도록 함.
	//		CSRF공격으로부터 보호하도록 구성.(크로스 사이트 요청 위조: 위조된 페이지에서 로그인
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				.antMatchers("/design", "/orders") // 지정된 경로의 패턴 일치를 검사.
				.access("hasRole('ROLE_USER')")
				.antMatchers("/", "/**").access("permitAll")
			.and()
				.formLogin()
				.loginPage("/login")
//				.loginProcessingUrl("/authenticate")
//				.usernameParameter("user")
//				.passwordParameter("pwd")
//				.defaultSuccessUrl("/design", true) //사용자가 있던 페이지가 아닌 특정 페이지로 무조건 이동하게 만들고싶으면 뒤에 true를 붙여줌.
			.and()
				.logout()
				.logoutSuccessUrl("/") // 세션 종료 후 애플리케이션에서 로그아웃된다.
			.and()
				.csrf()
			;
//			.access("hasRole('ROLE_USER') &&"
//					+ "T(java.util.Calendar).getInstance().get(T(java.util.Calendar).DAY_OF_WEEK) "
//					+ "== T(java.util.Calendar).TUESDAY"); // 화요일이고 role_user이어야만 인증
	}
	
	/*
	 * 사용자 인증 정보를 구성하는 메서드
	 * 1. in-memory 사용자 스토어 : 변경이 필요 없는 사용자만 미리 정해놓고 어플을 사용 시.
	 * 이 메서드는 인증 명세를 구헝하기 위해 빌더 형태의 API를 사용한다. 
	 * 이 때는 inMemoryAuthentication()메서드를 사용하여 보안 구성 자체에 사용자 정보를 직접 지정할 수 있다.
	 * */
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//			.withUser("user1")
//			.password("{nooop}password1")
//			.authorities("ROLE_USER")
//			.and()
//			.withUser("user2")
//			.password("{nooop}password2")
//			.authorities("ROLE_USER");
//	}
	
	/*
	 * 2. JDBC 기반의 사용자 스토어 : 관계형 데이터베이스로 관리.
	 * 스프링 시큐리티 내부 코드에서 쿼리를 수행하여 정보를 가져온다.
	 * username/password/enabled - from users
	 * username/authority - from authorities
	 * username/authority/group_name - from authorities, group_members, group_authorities
	 * 사전 지정된 테이블과 SQL쿼리를 사용하려면 관련 테이블을 생성하고 사용자 데이터를 추가해야함.
	 * */
//	@Autowired
//	DataSource dataSource; //DB에 접근하기 위함.
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.passwordEncoder(new NoEncodingPasswordEncode());
//		//사용자 커스텀 테이블을 사용할 때
////		auth.jdbcAuthentication()
////			.dataSource(dataSource)
////			.usersByUsernameQuery("select username, password, enabled from users where username = ?")
////			.authoritiesByUsernameQuery("select username, authority from authorities where username = ?");
//	}
	
	/*
	 * 3. LDAP 기반 사용자 스토어
	 * LDAP
	 * userSearchBase : 사용자를 찾기 위한 기준점 쿼리를 제공
	 * groupSearchBase : 그룹을 찾기 위한 기준점 쿼리를 지정.
	 * userSearchFilter,groupSearchFilter : LDAP 기본 쿼리의 필터를 제공. 사용자와 그룹을 검색하기 위해 사용.
	 * 루트 계층부터 검색이 되는데 *Base를 붙이면 people구성단위인 ou부터 검색이 시작된다.
	 * passwordCompare : LDAP 인증을 하고자 할 때
	 * LDAP 기본 인증 전략은 사용자가 직접 LDAP 서버에서 인증받도록 하는 것이다. (비밀번호를 비교하는 방법도 있음) 
	 * 입력된 비밀번호를 LDAP 디렉터리에 전송한 후, 이 비밀번호를 사용자의 비밀번호 속성 값과 비교하도록 LDAP 서버에 요청한다.
	 * 비밀번호 교환은 LDAP 서버에서 수행되므로 비밀번호는 노출되지 않는다.
	 * LDAP인증 시 localhost:33389로 LDAP서버가 접속된다고 간주하지만 LDAP서버가 다른 컴퓨터에서 실행중이면 contextSource()를 통해 서버 위치를 구성할 수 있다.
	 * */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.ldapAuthentication()
//			.userSearchBase("ou=people")
//			.userSearchFilter("(uid={0}")
//			.groupSearchBase("ou=groups")
//			.groupSearchFilter("member={0}")
//			.contextSource()
//			.root("dc=taccloud,dc=com")
//			.ldif("classpath:users.ldif")
//			.and()
//			.passwordCompare()
//			.passwordEncoder(new BCryptPasswordEncoder())
//			.passwordAttribute("userPasscode");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		// SecurityConfig로 자동 주입된 UserDetailsService인스턴스를 인자로 전달하여 userDetailsService() 메서드 호출
		// encoder()가 생성한 BCryptPasswordEncoder 인스턴스가 스프링 애플리케이션 컨텍스트에 등록, 관리되며
		// 이 인스턴스가 애플리케이션 컨텍스트로부터 주입되어 반환된다.
		// 이렇게 우리가 원하는 종류의 PasswordEncoder 빈 객체를 스프링의 관리하에 사용할 수 있다.
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}
	
	
}
