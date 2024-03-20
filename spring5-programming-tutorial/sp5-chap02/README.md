# spring-programming-tutorial chap02

## 1. Spring은 객체 컨테이너 
``` Java
	@Bean
	public Greeter greeter(){
		...
	}
```
- 스프링이 생성하는 객체를 Bean객체라고 부르는데, 이 Bean객체에 대한 정보를 담고 있는 메서드가 greeter()메서드다. 
- @Bean은 해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록한다는 뜻이다.

``` Java
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
	Greeter g = ctx.getBean("greeter", Greeter.class);
```
- 스프링의 핵심 기능은 객체를 생성하고 초기화 하는 것이다. 이와 관련된 기능은 ApplicationContext라는 인터페이스에 정의되어 있다.
- AnnotationConfigApplicationContext는 자바설정(AppContext)에 정의한 @Bean설정 정보를 읽어와 객체를(Greeter) 생성/초기화 한다.

### 1-1. AnnotationConfigApplicationContext 클래스 계층도
``` 
    AnnotationConfigApplicationContext > ApplicationContext > BeanFactory
```
- BeanFactory 인터페이스는 객체 생성과 검색에 대한 기능을 정의한다. 생성된 객체를 검색하는데 필요한 getBean()메서드가 BeanFactory에 정의돼있다.
--> 싱글톤/프로토타입 빈인지 확인하는 기능도 제공.
- ApplicationContext 인터페이스는 메시지, 프로필/환경 변수 등을 처리할 수 있는 기능을 정의한다.
- AnnotationConfigApplicationContext, GenericXmlApplicationContext, GenericGroovyApplicationContext 는 BeanFactory와 ApplicationContext에 정의된 기능의 구현을 제공한다. 
- 각 구현 클래스는 설정 정보로부터 Bean 객체를 생성하고 그 객체를 내부에 보관한다. 그 후 getBean()메서드를 실행하면 해당하는 빈 객체를 제공한다.
- ApplicationContext는 빈 객체의 생성, 초기화, 보관, 제거 등을 관리하고 있어서 Container라고도 부른다.
- ApplicationContext와 BeanFactory 등을 스프링 컨테이너라고 부른다.

``` Java
	Greeter g1 = ctx.getBean("greeter", Greeter.class);
	Greeter g2 = ctx.getBean("greeter", Greeter.class);
```
- 별도 설정을 하지 않을 경우 스프링은 한 개의 빈 객체만을 생성하며, 이떄 빈 객체는 singleton 범위를 갖는다.
- singleton은 단일 객체로, 스프링은 기본적으로 한 개의 @Bean애노테이션에 대해 한 개의 Bean 객체를 생성한다.

``` Java
	@Bean
	public Greeter greeter1(){}
	
	@Bean
	public Greeter greeter2(){}
```

- 그래서 서로 다른 객체로 만들고 싶으면 위와 같이 만들면 된다.