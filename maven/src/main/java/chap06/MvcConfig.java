package chap06;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"chap06"})
@EnableWebMvc // spring 에서 제공하는 web MVC 기능을 사용하겠다는 뜻.
public class MvcConfig implements WebMvcConfigurer{
	@Override // 재정의 해야하는 이유 :  추상메서드는 아니고 default 메소드로 정의되어있다. 몸뚱이 존재, 재정의 하여 아래 메소드들이 실행이 되도록.
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer conf) {
		conf.enable();
	}
	// Jsp는 보통 WEB-INF에 저장 (폴더이름 jsp or view)
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		// (prefix : jsp가 있는 경로 [시작경로], suffix : 확장자) ☆☆☆
		reg.jsp("/WEB-INF/view/", ".jsp"); 
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		// 비즈니스로직이 필요없는(디자인만 있는 페이지) url과 jsp 매핑
		reg.addViewController("/index.do").setViewName("index"); // FormController에 있는 코드와 비교
	}
}
