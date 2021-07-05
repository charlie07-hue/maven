package chap09;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping("/member/index.do")
	public String index(Model model) {
		List<MemberVo> list = service.selectList();
		model.addAttribute("list", list);
		return "member/index";
	}
	
	@RequestMapping("/member/form.do") 
	// 쿠키값을 자동으로 받아와야함. parameter에 담아주기. Cookie 타입 cookie 객체, 값 없을수도 있으니. 두개이상 쓸땐 value= 꼭 입력
	public String form(MemberVo vo, @CookieValue(value="cookieEmail", required = false ) Cookie cookie) { 
		if (cookie != null) { // null 이면 에러남
			vo.setEmail(cookie.getValue()); // 아까 저장한 cookie의 값을 vo에 set
			vo.setCheckEmail("check"); // 쿠키 있을때, Email, CheckEmail에 각각 set해줌
		}
		return "member/form"; // suffix때문에 jsp 넣으면 안된다
	}
	
	@RequestMapping("member/login.do")
	public String login(MemberVo vo, HttpSession sess, HttpServletRequest req, HttpServletResponse res) throws Exception{ // parameter 要 아이디&패스워드
		MemberVo m = service.login(vo);
		if (m != null) {
			// 세션에 저장
			// 세션객체를 가져오는 방법
			// 1. HttpServletRequest를 통해 : spring을 이용하면 이런 작업을 거칠필요가 없다
			// HttpSession session = req.getSession(); 
			// getSession(true) : 세션이 존재하지 않으면 객체를 새로 생성해서 리턴
			// getSession(false) : 세션이 존재하지 않으면 null을 리턴 (아무것도 안쓰면 false)
			// 2. 매개변수로 HttpSession : Spring 이용
			
			// 세션에 저장 (로그인 성공, 저장소(서버에))
			sess.setAttribute("memberInfo", m);
			// 쿠키에 저장
			Cookie cookie = new Cookie("cookieEmail", vo.getEmail()); // 쿠키 객체 생성 (response에 추가해야<응답> 한다)
			cookie.setPath("/");
			if("check".equals(vo.getCheckEmail())) { // checkbox 선택 하고 로그인 누른경우
				cookie.setMaxAge(60*60*24*365); // 유효시간 설정하는 것(초단위 초 분 시 원하는 기간<일>)
			} else {
				cookie.setMaxAge(0);
			}
			res.addCookie(cookie);
			return "redirect:index.do"; // 띄어쓰기X
		} else { // 로그인 실패시 쿠키에 저장 不要
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.print("<script>");
			out.print("alert('이메일과 비밀번호가 올바르지 않습니다.');");
			out.print("location.href='form.do';");
			out.print("</script>");
			return null;
		}
	}
	
	@RequestMapping("member/logout.do") 
	public String logout(Model model, HttpSession sess, HttpServletResponse res) throws Exception {
		sess.invalidate();
		model.addAttribute("msg", "로그아웃되었습니다");
		model.addAttribute("url", "index.do");
		return "include/alert";
		
	}
	
	@RequestMapping("member/mypage.do") 
	public String mypage(Model model, HttpSession sess) throws Exception {
		// 세션에서 MemberVo 객체를 가져오기 
		MemberVo vo = (MemberVo)sess.getAttribute("memberInfo");
		if (vo!= null) {
			MemberVo m = service.mypage(vo.getMno());
			model.addAttribute("vo", m);
			return "member/mypage";
		} else {
			model.addAttribute("msg", "로그인 후 사용가능합니다.");
			model.addAttribute("url", "index.do");
			return "include/alert";
		}
		
// 굳이 어렵게 하자면	
//	@RequestMapping("member/mypage.do") 
//	public String mypage(Model model, HttpSession sess) throws Exception {}
//		return service.mypage(sess, model);
//	
	}
	
//	@RequestMapping("member/update.do") 
//	public String update(MemberVo vo, HttpSession sess, HttpServletRequest req, HttpServletResponse res) throws Exception {
//		service.update(vo);
//		return "redirect:index.do";
//	}
	@RequestMapping("member/update.do") 
	public String update(Model model, MemberVo vo, HttpSession sess) throws Exception {
		int r = service.update(vo);
		if(r ==0) {
			model.addAttribute("msg", "수정실패");
			model.addAttribute("url", "mypage.do");
			return "include/alert";
		} else {
			model.addAttribute("msg", "정상적으로 수정되었습니다");
			model.addAttribute("url", "index.do");
			return "include/alert";
		}
	}
	
	@RequestMapping("member/write.do") 
	public String write(MemberVo vo) throws Exception {
		return "member/write";
	}
	
	@RequestMapping("member/insert.do") 
	public String write(MemberVo vo, HttpServletRequest req) throws Exception { // service에다가 vo 와 request 넘겨주려고 여기에다 쓰는것
		if ( service.insert(vo, req) == 0 ) {
			req.setAttribute("msg", "등록 오류");
			req.setAttribute("url", "write.do");
		} else {
			req.setAttribute("msg", "등록 성공");
			req.setAttribute("url", "index.do");
		}
		return "include/alert";
	}
}
