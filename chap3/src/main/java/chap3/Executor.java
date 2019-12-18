package chap3;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

//스프링 Component에 의해 Executor가 객체화됨. "executor" 이름으로 컨테이너에 저장
@Component
public class Executor {
	@Autowired // DI 컨테이너 중 Worker 객체를 찾아서 주입.setter없이도 주입이됨.
	private Worker worker;
	public void addUnit(WorkUnit unit) {
		worker.work(unit);
	}
}
