package chap05;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExeAspect {
	
	@Pointcut("execution(public * chap05..*(..))")
	private void publicTarget() {}
	
	@Around("publicTarget()") // method가 실행이 될때마다 이 구문이 실행
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("메서드 실행 전!!!");
		long start = System.nanoTime();
		
		try { // cal이라는 객체가 $proxy속 객체이고 거기서 method를 호출하면 proceeding안의 proceed객체 호출 -> 비로소 우리가 작성한 메소드를 호출하는것
			Object result = joinPoint.proceed(); // 실제호출. 위아래 nanoTime 알아서 작성되게끔 하는것이 Aspect
			return result;
			
		} finally {
			System.out.println("메서드 실행 끝!!");
			long end = System.nanoTime();
			System.out.println("AOP : "+(end-start));
		}
	}
}
