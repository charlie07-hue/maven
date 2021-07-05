package chap03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	@Bean
	public MemberDao memberDao1() { // MemberDao 타입으로 가져오기때문에 memberDao1() <- 이름은 상관없다
		return new MemberDao();
	}
	
	//memberDao2()를 추가하고 싶다면 Service 파일에 Qualifier("memberDao1") 사용해야 에러가 없다.
	//설정할때도 Qualifier 추가 가능.
	
	@Bean
	public MemberRegisterService regSvc() {
		return new MemberRegisterService();
	}
	
	@Bean 
	public ChangePasswordService pwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();;
		//pwdSvc.setMemberDao(memberDao()); Service.java파일에 Autowired
		return pwdSvc;
	}
	
	@Bean 
	public MemberListService listSvc() {
		return new MemberListService();
	}
	
	@Bean 
	public MemberInfoService infoSvc() {
		return new MemberInfoService();
	}
	
}
