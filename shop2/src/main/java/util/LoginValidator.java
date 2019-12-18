package util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.User;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		//��ȿ�� ���� ����� �Ǵ� ��ü ���� Ȯ��(User.class���� �ƴ���).
		return User.class.isAssignableFrom(cls);
	}
	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User)obj;
		String group = "error.required";
		//userid : �ݵ�� property�� ���ƾ� ��.
		if(user.getUserid() == null || user.getUserid().length()==0) {
			//
			errors.rejectValue("userid", group);
		}
		if(user.getPassword() == null || user.getPassword().length()==0) {
			errors.rejectValue("password", group);
		}
		if(errors.hasErrors()) {
			//reject : �۷ι��޼��� -> view�� : ${errors.globalErrors}�� ����
			errors.reject("error.input.user");//�۷�������(�Է������� ������ �ֽ��ϴ�.)
		}
	}
}
