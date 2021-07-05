package chap02;

import java.time.LocalDateTime;

public class MemberRegisterService {
	private MemberDao memberDao; // memberDao 객체가 의존객체 (객체를 미리 만들어놓고 외부에서 값을 주입받는)
	
	public MemberRegisterService(MemberDao memberDao) { // 이름이 class랑 똑같으면 return type이 없고 생성자인것
		this.memberDao = memberDao; // new MemberDao(); 해도 되지만 서비스객체 생길때마다 매번 생성해야하고, 직접 바꿔줘야하기 때문에 외부에서 주입받는 것이 좋다. 
	}
	
	public Long regist(RegisterRequest req) { // 회원을 등록하는 서비스 메소드를 호출하는 부분. 커맨드 객체 (RegisterRequest에 있는 모든걸 호출)
		Member member = memberDao.selectByEmail(req.getEmail()); // put되어있는걸 호출 ( 있으면 에러, 없으면 등록 )
		if (member != null) {
			throw new DuplicateMemberException("이메일 중복:" + req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now()); // 생성자 만들었기 때문에 굳이 set 하지 않아도 값이 존재. 아이디는 알아서 +1
		memberDao.insert(newMember); // map에 put된다.
		return newMember.getId();
	}
}
