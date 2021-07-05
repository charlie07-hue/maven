package chap01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chap01/beans.xml"); // 매개변수로 beans.xml 
		
		Greeter g = ctx.getBean("greeter", Greeter.class);
		System.out.println(g.greet("홍길동"));
	}

}
