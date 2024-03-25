package sp5.chap03;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

public class MemberRegisterSrevice {

	private MemberDao memberDao;
	
	public MemberRegisterSrevice(MemberDao memberDao) {
		this.memberDao = memberDao();
	}
	
	public static void main(String[] args) {
		// MemberRegisterSrevice가 DB처리를 위해 MemberDao클래스를 사용하고있다.
		// 이렇게 한 클래스가 다른 클래스의 메서드를 실행할 때 이를 '의존'한다고 표현한다.
		private MemberDao memberDao = new MemberDao();
		
		public void regist(RegisterRequest req) {
			Member member = memberDao.selectByEmail(req.getEmail());
			if(member != null) {
				throw new DuplicateMemberException();
			}
			
			Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
			memberDao.insert(newMember);
		}

	}

}
