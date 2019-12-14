package chap1;

public class Greeter {
	// 변수 선언
	private String format;
	
	// 메서드 선언 
	public String greet(String guest) {
		return String.format(format, guest);
	}
	
	//format : %s을 시작합니다.
	public void setFormat(String format) {
		this.format = format;
	}
}
