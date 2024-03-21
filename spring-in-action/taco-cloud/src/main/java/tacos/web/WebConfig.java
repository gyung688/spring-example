package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	//WebMvcConfigurer 인터페이스 : 스프링 mvc를 구성하는 메서드를 정의. 필요한 메서드만 선택해 오버라이딩하면됨.
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 이 메서드는 하나 이상의 뷰 컨트롤러를 등록하기 위해 사용할 수 있는 ViewControllerRegistry를 인자로 받음.
		// 뷰 컨트롤러가 GETㅛ청을 처리하는 경로인 "/"를 인자로 전달해 addViewController()를 호출한다.
		// 이 메서드는 ViewControllerRegistration 객체를 반환한다.
		// 그리고 "/" 경로의 요청이 전달되어야 하는 뷰로 home을 지정하기 위해 연달아 ViewControllerRegistration객체의 setViewName()을 호출한다.
		// 이렇게 HomeController를 대체할 수 있다. HomeController를 삭제해도 잘 실행 될 것이다.
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login");
	}
}
