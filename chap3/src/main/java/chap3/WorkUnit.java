package chap3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkUnit {
	@Autowired
	private Worker worker;
	public void addUnit(WorkUnit unit) {
		worker.work(unit);
	}
}
