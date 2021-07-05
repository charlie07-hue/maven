package chap03;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao; // memberDao 객체가 의존객체 (객체를 미리 만들어놓고 외부에서 값을 주입받는)
	
//	public MemberRegisterService(MemberDao memberDao) { 
//		this.memberDao = memberDao;  
//	}
	
	public Long regist(RegisterRequest req) { 
		Member member = memberDao.selectByEmail(req.getEmail()); 
		if (member != null) {
			throw new DuplicateMemberException("이메일 중복:" + req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now()); 
		memberDao.insert(newMember); // map에 put된다.
		return newMember.getId();
	}
}
