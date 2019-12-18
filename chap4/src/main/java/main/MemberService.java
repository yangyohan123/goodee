package main;

import org.springframework.stereotype.Component;

import xml.Member;
import xml.UpdateInfo;

//main2와 main3가 오류가 안나고 MemberService를 사용할 수 있는이유
//main2는 di.xml과 aop2.xml을 사용
//main3는 annotation.xml사용 
@Component("memberService2")
public class MemberService {
	public void regist(Member member) {
		System.out.println("main.MemberService.regist() 메서드 실행");
	}
	public boolean update(String memberId, UpdateInfo info) {
		System.out.println("main.MemberService.update() 메서드 실행");
		return true;
		
	}
	public boolean delete(String id, String str) {
		System.out.println("main.MemberService.delete() 메서드 실행");
		return false;
	}
}
