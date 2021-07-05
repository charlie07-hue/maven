package chap01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main { // 실행하기 위한 Main method

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class); // greeter라는 이름으로 bean 등록
		
//		Greeter g = (Greeter)ctx.getBean("greeter"); // Greeter로 형변환하여 return 아래 코드와 동일
		Greeter g = ctx.getBean("greeter", Greeter.class);
		System.out.println(g.greet("홍길동"));
		
	}

}
