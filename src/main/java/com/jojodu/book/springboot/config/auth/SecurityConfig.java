package com.jojodu.book.springboot.config.auth;

import com.jojodu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring SEcurity 설정들을 활성화시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http        .csrf().disable().headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests()// URL별 권한 관리를 설정하는 옵션의 시작점이다. 이게 선언되야 antMatchers옵션 사용가능
                    .antMatchers("/", "/css/**", "/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // antMatcher는 권한 관리대상을 지정하는 옵션, URL, HTTP메소드별로 관리 가능, "/"등 지정된 URL들은 permitAll()옵션을 통해 전체 열람 권한을 준다.  POST메소드이면서 "/api/v1/**" 주소를 가진 API는 USER권한을 가진 사람만 가능하도록 했다.
                    .anyRequest().authenticated() //설정값 이외나머지 URL들어
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}
