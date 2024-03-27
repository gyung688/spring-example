# spring-programming-tutorial chap02

## 1. DI(Dependency Injection)

``` Java
	class MemberRegisterSrevice{
		...
		Member member = memberDao.selectByEmail(req.getEmail());
		...
	}
```
- MemberRegisterSrevice가 DB처리를 위해 MemberDao클래스를 사용하고있다.
- 이렇게 한 클래스가 다른 클래스의 메서드를 실행할 때 이를 '의존'한다고 표현한다.
- MemberRegisterSrevicer가  MemberDao에 의존한다.

``` Java
	class MemberRegisterSrevice{
		//의존 객체를 직접 생성
		private MemberDao memberDao = new MemeberDao();
	}
	
	class Main{
		MemberRegisterService svc = new MemberRegisterService();
	}
```
- MemberRegisterService객체를 생성하는 순간 의존하는 MemberDao의 객체도 함께 생성된다.

### DI를 통한 의존 처리 
- DI는 의존하는 객체를 직접 생성하는 대신 의존 객체를 전달받는 방식을 사용한다.
```
	public MemberRegisterService(MemberDao memberDao){
		this.memberDao = memberDao;
	}
```


