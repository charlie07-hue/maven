package chap02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

//실행시킬 Method
public class Main {
		
	public static void main(String[] args) throws IOException {
		// new hong@gmail.com 홍길동 1234 1234 -> 비번. 컨펌비번. 까지 총 5개의 배열
		// change hong@gmail.com 1234 5678
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) { // 계속 반복시킬것
			System.out.println("명령어를 입력해 주세요.");
			String cmd = reader.readLine(); // reader를 한줄씩 읽어나간다
			if (cmd.equals("exit")) {
				System.out.println("종료합니다.");
				break;
			} else if (cmd.startsWith("new")) {
				processNewCommand(cmd.split(" ")); // new " " email " " new " " password ...
			} else if (cmd.startsWith("change")) {
				processChangeCommand(cmd.split(" "));
			} else if (cmd.equals("list")) {
				// hong@gmail.com 홍길동 비밀번호 날짜 
//				processListCommand(cmd.split(" "));		
				Map<String, Member> map = assembler.getListSvc().selectList(); // email이 key
				for (String key : map.keySet()) {
					Member m = map.get(key);
					System.out.println(m.getId()+"\t"+ m.getEmail()+"\t"+m.getName()
									+"\t"+m.getPassword()
									+"\t"+m.getRegisterDateTime()
									);
				}
			}
			//cmd.startsWith("info")
			//info 이메일 
			//id : 1, email:hong@gmail.com, name:홍길동, date:2021.. 
			//없으면 -> 등록되지않은 이메일 입니다
			else if (cmd.startsWith("info")) {
				if (cmd.split(" ").length != 2) {
					return;
				}
				Member member = assembler.getInfoSvc().selectOne(cmd.split(" ")[1]);
				if (member == null) {
					System.out.println("등록되지않은 이메일입니다.");
				} else {
					System.out.println("id:"+member.getId()
									+" email:"+member.getEmail()
									+" name:"+member.getName()
									+" date:"+member.getRegisterDateTime());
				}
			}
		}
	}
	private static Assembler assembler =  new Assembler(); // main method 안에서 호출하려고 static 사용
	
	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			return;
		}
		MemberRegisterService regSvc = assembler.getRegSvc();
		
		RegisterRequest req = new RegisterRequest(); // vo 처럼 값을 담아두기 위한 객체. field를 가지고있음
		
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]); // 대입시킨것
		
		if(!req.isPasswordEqualToConfirmPassword()) { // method 호출의 결과값이- true or false
			System.out.println("비밀번호가 일치하지 않습니다.");
			return; // method 중지
		}
		try { 
			regSvc.regist(req);
			System.out.println("등록완료");
		} catch (DuplicateMemberException e) {
			System.out.println("이메일 중복");
		}
	}
	
	private static void processChangeCommand(String[]arg) {
		if (arg.length != 4) { // 필요한 3개 포함 앞에 텍스트까지 총 4개
			return;
		}
		
		ChangePasswordService pwdSvc = assembler.getPwdSvc();
		
		try {
			pwdSvc.changePassword(arg[1], arg[2], arg[3]); // 0번은 다른 인덱스
			System.out.println("비밀번호 변경");
		} catch (MemberNotFoundException e) {
			System.out.println("회원이 존재하지 않습니다.");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 비밀번호가 일치하지 않습니다.");
		}
	}
	
	//목록
	private static void processListCommand(String[] arg) {
		 
	}
}
