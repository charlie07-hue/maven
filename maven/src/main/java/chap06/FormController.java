package chap06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller // 깜빡하지 않도록 주의. 없으면 Scan X, Bean에 등록이 X. 페이지를 못찾는다. = @component
//@RequestMapping("/member") 상위에 이렇게 있다면, 아래 Annotation 주소들이 전부 "/member/form.do" 이런식으로 된다. 전체를 매핑 후 하위 메소드들을 매핑 시킬 수 있는 것.
public class FormController { // 첫자를 소문자로 바꿔 객체를 생성한 후 Bean에 등록시킨다 (formController)
	@RequestMapping("/form.do")
	public String form() {
		return "form"; // jsp forwarding
	}
	
	@RequestMapping("/")
	public String index() {
		return "redirect:index.do"; // addController로 가서 index.do로 보냄
	}
	
//	@RequestMapping("/index.do")
//	public String index() {
//		return "index";
//	}
	// 리턴이 없으면 매핑된 url과 동일한 경로 jsp를 포워딩 하게 되어있다. 
	// 서블릿으로 응답 가능
	@RequestMapping("/test.do") // 하나밖에 없다면 맨 위에 써도 무방.
	public void test(HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>alert('정상적으로 저장되었습니다');</script>");
		
	}
	
	@RequestMapping("/send.do")
	public String send(Model model, HttpServletRequest req, // 무조건 암기(아래 3개)
									@RequestParam(value="name", required = false) String name2, // : "name"이라는 이름의 parameter를 name2에다 넣어주는것.
									@RequestParam(value="email", required = false) String email2, // 2개이상 넣으려면 value값 꼭 있어야. false니까 null도 가능. request param 자동형변환, getparameter 무조건 string
									@RequestParam(value="no", required = false, defaultValue = "0") int no, // 값이 존재하지 않을때 오류를 방지하기 위해 defaultValue. 기본자료형같은 경우는 초기화 要 string은 쓸일없다.
									MemberVo vo) { 
		// 파라미터를 받는 방법 (파라미터를 통해 값을 받음)
		// 1. HttpServletRequest
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		// 2. @RequestParam
		//    @RequestParam("파라미터명") : 매개변수에 지정 // 별도의 parameter가 하나정도 따로 있다 하면 굳이 vo에 추가하지 않고 param 쓰는편
		// 3. 커맨드객체 ☆
		
		System.out.println(vo.getHobby().length);
		for(int v : vo.getHobby()) {
			System.out.println(v);
		}
		
		model.addAttribute("name1", name); // 쌍따 안 이름이 실제 request에 set되는 값. 오른쪽은 변수
		model.addAttribute("email1", email); // 굳이 model.addAttribute할 필요가 없다. 커맨드객체가 이미 들어있기 때문에
		model.addAttribute("name2", name2);
		model.addAttribute("email2", email2);
		model.addAttribute("no", no);
		model.addAttribute("vo", vo);
		
		req.setAttribute("id", "hong");
		
		if(email == null || "".equals(email)) {
			return "form";
		}
		return "send";
	}
	// ModelAndView 객체 리턴
	@RequestMapping("/main.do") // 매핑
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView(); // 로직처리
		mav.addObject("name", "홍길동");
		mav.setViewName("main"); // main.jsp가 되는것
		return mav;
	}
}
