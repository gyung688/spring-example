package sp5.chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		//AnnotationConfigApplicationContext는 자바설정(AppContext)에 정의한 @Bean설정 정보를 읽어와 Greeter객체를 생성/초기화.
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		Greeter g = ctx.getBean("greeter", Greeter.class); // getBean(@Bean메서드명, 검색할 빈 객체의 타입)
		String msg = g.greet("스프링"); //Greeter.greet()메서드 실행 
		System.out.println(msg);
		ctx.close();
	}
}
