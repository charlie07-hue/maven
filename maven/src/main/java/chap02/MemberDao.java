package chap02;

import java.util.HashMap;
import java.util.Map;

// 같은 객체를 쓴다하더라도 공통되는 기능을 하는것 뿐이니 vo와 다르게 (!= 사용자가 입력, DB에서 가져온값) 싱글톤으로 처리가 가능 ( = service)
public class MemberDao {
	private static long nextId = 0; // 기능을 처리하기 위해 필요한 변수일뿐 , 값을 담에 사용자에게 전달하는 것이 아님.
	private Map<String, Member> map = new HashMap<String, Member>(); // DB대신 Map을 사용 (예: 이름과 이메일)
	
	public Member selectByEmail(String email) { 	// 이메일로 조회한다는 뜻
		return map.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++nextId); // 겹치면 안되니까 +1하고 들어가는 연산자
		map.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	// 목록
	public Map<String, Member> selectList() {
		return map;		
	}
}
