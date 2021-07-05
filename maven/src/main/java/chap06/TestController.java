package chap06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/form.do")
public class TestController {
	@RequestMapping
	public String form() {
		return "form";
	}
}
