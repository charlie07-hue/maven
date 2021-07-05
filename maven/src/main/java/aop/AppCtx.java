package aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chap05.Calculator;
import chap05.ExeAspect;
import chap05.ImplCalculator;

@Configuration
@EnableAspectJAutoProxy // Aspect를 사용하겠다! 반드시 써줘야
public class AppCtx {
	@Bean
	public ExeAspect exeAspect() { // exeAspect란 이름으로 Bean에 등록
		return new ExeAspect();
	}
	@Bean
	public Calculator calculator() {
		return new ImplCalculator();
	}
	
//	@Bean
//	public Calculator calculator2() {
//		return new ImplCalculator2();
//	}
}
