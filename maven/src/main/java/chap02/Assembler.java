package chap02;

// 실행하기 전 유기적으로 연결시켜 줄 놈이 필요
public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	private MemberListService listSvc;
	private MemberInfoService infoSvc;
	
	public Assembler() {
		memberDao = new MemberDao(); // 주입이 필요
		
		regSvc = new MemberRegisterService(memberDao); // 위에서 생성해 놓은 memberDao 객체를 주입을 시켜준다. regSvc는 생성자가 있기 때문에 그것을 주입.
		
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
		
		listSvc = new MemberListService(memberDao);
		infoSvc = new MemberInfoService(memberDao);
		
	}
	
	// 이미 생성자에서 setter를 하고있기 때문에 getter만 필요
	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getRegSvc() {
		return regSvc;
	}

	public ChangePasswordService getPwdSvc() {
		return pwdSvc;
	}
	
	public MemberListService getListSvc() {
		return listSvc;
	}
	
	public MemberInfoService getInfoSvc() {
		return infoSvc;
	}
}
