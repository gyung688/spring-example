package sp5.chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter(); // 객체생성
		// 스프링이 생성하는 객체를 Bean객체라고 부르는데, 이 빈객체에 대한 정보를 담고 있는 메서드가 greeter()메서드다.
		// 이 메서드에는 @Bean이 붙어있는데, 해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록한다는 뜻이다.
		g.setFormat("%s, 안녕하세요!"); // Greeter 객체 초기화
		return g;
	}
}
