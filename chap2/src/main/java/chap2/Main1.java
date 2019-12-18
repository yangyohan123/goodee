package chap2;

import java.util.Arrays;


import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {

	public static void main(String[] args) {
		//classpath:applicationContext.xml 읽어서 컨테이너 만듬 
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Project pro = ctx.getBean("project", Project.class);
		pro.build();
		
		// 새로운 객체 생성은 되어도 데이터 값들은 주입되어 있지 않아 
		// pro.build()메서드 실행하면 nullpointException 에러발생
		pro = new Project();
		//pro.build();
		
		BuilderRunner br = ctx.getBean("runner", BuilderRunner.class);
		br.build(Arrays.asList("src/","srcResource/"),"/bin2");
		
		br = new BuilderRunner();
		br.build(Arrays.asList("src/","srcResource/"), "bin2");
		
		WriteImpl wi = ctx.getBean("write", WriteImpl.class);
		wi.write();
		
		
	}

}
