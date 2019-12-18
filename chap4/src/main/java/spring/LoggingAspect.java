package spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LoggingAspect {
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("[LA]로그 시작");
		StopWatch sw = new StopWatch();
		sw.start();
		//loggin 다음 메서드는 write메서드임.
		//write메서드 실행
		Object ret = joinPoint.proceed();
		sw.stop();
		
		System.out.println
		("[LA]메서 실행 시간:" + sw.getTotalTimeMillis() + "밀리초");
		return ret;
	}
}
