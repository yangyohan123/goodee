package chap3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:annotation.xml");
		Executor exec = ctx.getBean("executor", Executor.class);
		
		//addUnit WorkUnit을 객체를 호출
		exec.addUnit(new WorkUnit());
		exec.addUnit(new WorkUnit());
		
		
		HomeController home =
				ctx.getBean("homeController", HomeController.class);
		home.checkSensorAndAlarm();
		System.out.println("침입 없음 ============");
		
		//창문에 침입함.
		InfraredRaySensor sensor = ctx.getBean("windowSensor", InfraredRaySensor.class);
		sensor.foundObject();
		home.checkSensorAndAlarm();
		
		sensor = new InfraredRaySensor("현관센서"); //현관센서가 아닌 창센서가 나타나는 이유 다른 sensor이기 때문에
		sensor.foundObject();
		home.checkSensorAndAlarm();
	}

}
