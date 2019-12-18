package chap3;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // (annotation 객체 생성 main1에서 먼저 실행됨)homecontroller 이름의 객체로 생성되고, 컨테이너에 저장
public class HomeController {
	
	private AlarmDevice alarmDevice;
	private Viewer viewer;
	
	
	@Resource(name="camera1") //이름을 기준으로 객체를 주입
	private Camera camera1; // 1값
	@Resource(name="camera2")
	private Camera camera2; // 2값
	@Resource(name="camera3")
	private Camera camera3; // 3값
	@Resource(name="camera4")
	private Camera camera4; // 4값
	
	private List<InfraredRaySensor> sensors;
	@Autowired(required=false)//Recorder 객체가 없으면 null로 주입
	private Recorder recorder;
	@Autowired //나의 컨테이너에서 AlarmDevice객체와, Viewer 객체 메서드 생성 required=false가 없으니 값이 무조건 있어야됌
	public void prepare(AlarmDevice alarmDevice, Viewer viewer) { //Viewer로부터 받은 추상클래스가 여러개면 Error남!! 중요!!
		this.alarmDevice = alarmDevice;
		this.viewer = viewer;
	}
	@PostConstruct //객체의 생성시 모든 객체의 주입이 완료된 후. 객체 생성 이후 마지막에 실행됨.
	public void init() {
		System.out.println("init() 메서드 호출");
		viewer.add(camera1);
		viewer.add(camera2);
		viewer.add(camera3);
		viewer.add(camera4);
		viewer.draw();
	}
	@Autowired //Postconstruct보다 먼저 실행됨.
	@Qualifier("intrusionDetection") //벌명 설정
	public void setsensors(List<InfraredRaySensor> sensors) {
		this.sensors = sensors;
		for(InfraredRaySensor s : sensors) {
			System.out.println("센서 등록:" + s);
		}	
	}
	public void checkSensorAndAlarm() { // 메인에서 호출하는 메서드
		for(InfraredRaySensor s : sensors) {
			if(s.isObjectFounded()) {
				alarmDevice.alarm(s.getName());
			}
		}
	}
	
	
}
/*
 기본어노테이션
 1. 객체 생성 : @Component
 	xml : <context: component-scan base-package="chap3"/>
 2. 객체 주입 : 
 	@Autowired : 객체선택의 기준이 클래스의 자료형임.
 	 			 (required=false) : 객체가 없는 경우 가능.
 	@Resource(이름)  : 객체 중 이름이 해당 하는 객체를 주입. 
 	@Required  : 객체선택의 기준이 클래스의 자료형임. 반드시 객체가 있어야됨. 

 3. 그외
 	@PostConstruct : 객체 생성이 완료된 후 호출되는 메서드의 위에 설정
 	@Qualifier : 객체의 이름에 별명을 설정
 	@Scope(..) : 생성된 객체의 지속가능한 영역 설정.
 	 			 @Component와 함께 사용됨.
 */
