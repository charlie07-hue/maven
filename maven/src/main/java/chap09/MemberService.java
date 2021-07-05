package chap09;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

// Service는 보통 Interface로 만든다
public interface MemberService {
	List<MemberVo> selectList();
	MemberVo login(MemberVo vo);
	MemberVo mypage(int mno);
	int update(MemberVo vo);
	
	// insertSchool이 vo에는 없다. 추가하기 귀찮
	int insert(MemberVo vo, HttpServletRequest req); 
	
// 굳이 어렵게 하자면, 위에 maypage 한줄 주석처리 후 ↓
//	String mypage( HttpSession sess, Model model);
}
