package chap1;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {
	public static void main(String[] args) {
		// GenericXmlApplicationContext : 컨테이너
		// 컨테이너 -> 여러 객체들을 가지고 있음.
		// applicationContext.xml을 파싱함.
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		// greeter라는 객체를 가져옴. == Greeger.java
		Greeter g = ctx.getBean("greeter",Greeter.class);//greeter라는 이름을 가진 객체를 g가 가지고 잇음
		
		//greete을 메서드 실행
		System.out.println(g.greet("스프링"));
				
		//message 메서드 실행
		Message m = ctx.getBean("message", Message.class);
		m.sayHello("yangyeongwon");
		m.sayHello("test123");
		
		//만약 greeter g1 1000번지이면 g2 또한 1000번지인 ctx의 객체를 가져옴
		//같은 객체를 가져다가 쓰는 것을 확인할 수 있다.
		//이것을 의존성주입(DI)를 의미한다.
		//의존성주입은 다양한 종류가 있다.
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		if(g == g2) {
			System.out.println("g 객체와 g2객체는 같은 객체이다.");
		}
	}
}
