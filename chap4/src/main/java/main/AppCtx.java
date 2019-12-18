package main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration 	//컨테이너 설정을 위한 java 소스, xml설정 대체하는 소스

//<context:component-scan base-package="annotation,main"/>
//annotation패키지 돌아다니면서 Component인 애들을 객체화 시켜줌
@ComponentScan(basePackages = {"annotation","main"}) //annotation패키지 

//<aop:aspectj-autoproxy/><!--  AOP설정 : annotation방식으로 설정함. -->
@EnableAspectJAutoProxy // AOP 설정
public class AppCtx {
	
	
	
	/*
	 * @Bean //xml의 <bean id="memberService2" class="main.MemberService" /> public
	 * MemberService memberService2() {//메서드 이름이 bean의 id값 return new
	 * MemberService(); //return 값이 bean의 class이다. }
	 */
}
