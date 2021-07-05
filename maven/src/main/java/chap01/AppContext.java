package chap01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter(); // 값을 먼저 set 해주는 과정
		g.setFormat("%s, 안녕하세요");
		return g;
	}
}
