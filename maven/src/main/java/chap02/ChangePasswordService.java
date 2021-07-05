package chap02;

public class ChangePasswordService {
	private MemberDao memberDao; // 의존객체
	
	public void setMemberDao(MemberDao memberDao) { // setter 메서드 통해 MemberDao 를 ChangePasswordService에 주입을 해줌
		this.memberDao = memberDao;
	}
	
	public void changePassword(String email, String oldPwd, String newPwd) { // 변경하려는 비번과, 식별할 이메일이 要
		Member member = memberDao.selectByEmail(email); // map에서 get 해서 return된 객체가 담길 member
		if (member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd); // oldPwd가 같으면 변경할 기회 주고, 다르면 오류
		// Member.java에서 this.password를 newPassword로 바꾸고있음 
	}
}
