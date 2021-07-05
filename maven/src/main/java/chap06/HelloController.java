package chap06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping(value="/hello.do", method=RequestMethod.GET) // get방식. 굳이 이렇게 쓸 필요 없다
	public String hello(Model model) {
		model.addAttribute("name", "홍길동");
		return "hello";
	}
	@PostMapping("/hello2.do") // GET방식의 요청은 지원되지 않는다는 오류 발생
	public String hello2(Model model) {
		model.addAttribute("name", "홍길동");
		return "hello";
	}
	
	@GetMapping("/board/write.do") 
	public String write(Model model) {
		model.addAttribute("name", "홍길동");
		return "hello";
	}
	
	@PostMapping("/board/write.do") // GET과 POST를 따로 쓰면 주소가 같아도 오류나지 않는다
	public String write2(Model model) {
		model.addAttribute("name", "홍길동");
		return "hello";
	}
}
