package chap08;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

// Service는 보통 Interface로 만든다
public interface MemberService {
	List<MemberVo> selectList();
	MemberVo login(MemberVo vo);
	MemberVo mypage(int mno);
	int update(MemberVo vo);
	
// 굳이 어렵게 하자면, 위에 한줄 주석처리 후 ↓
//	String mypage( HttpSession sess, Model model);
}
