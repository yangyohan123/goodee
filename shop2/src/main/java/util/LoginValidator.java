package util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.User;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		//유효성 검증 대상이 되는 객체 여부 확인(User.class인지 아닌지).
		return User.class.isAssignableFrom(cls);
	}
	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User)obj;
		String group = "error.required";
		//userid : 반드시 property와 같아야 함.
		if(user.getUserid() == null || user.getUserid().length()==0) {
			//
			errors.rejectValue("userid", group);
		}
		if(user.getPassword() == null || user.getPassword().length()==0) {
			errors.rejectValue("password", group);
		}
		if(errors.hasErrors()) {
			//reject : 글로벌메세지 -> view단 : ${errors.globalErrors}로 전달
			errors.reject("error.input.user");//글러벌오류(입력정보에 문제가 있습니다.)
		}
	}
}
