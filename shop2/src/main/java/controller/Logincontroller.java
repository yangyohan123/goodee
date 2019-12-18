package controller;

import javax.servlet.http.HttpSession;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.ShopService;
import logic.User;

public class Logincontroller {
	private ShopService shopService;
	private Validator validator;
	
	public void setShopService (ShopService shopService) {
		this.shopService = shopService;
	}
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	@GetMapping // @RequestMapping(method=RequestMethod.GET)�� ���� �ǹ�
	            // 4.0 ���� �������� ����
	//model�� ��ܿ� ������ ��ü
	public String loginForm(Model model) {
		model.addAttribute(new User());
		return "login";
	}
	
	//HttpSession session : session��ü ����
	//BindingResult bresult : ��ȿ�� �˻�
	//user : �Ķ���Ͱ�(�Էµ� ��)�� �����ϰ� �ִ� ��ü
	@PostMapping
	public ModelAndView login(User user, BindingResult bresult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		validator.validate(user, bresult);
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		try {
			User dbuser = shopService.getUser(user.getUserid());
			//��й�ȣ ���� ���
			if(user.getPassword().equals(dbuser.getPassword())) {
				session.setAttribute("loginUser", dbuser);
			}else {
			// ��й�ȣ Ʋ�� ��� : �۷ι�����
				bresult.reject("error.login.password");
				mav.getModel().putAll(bresult.getModel());
				return mav;
			}
		}catch(EmptyResultDataAccessException e) {
			//id���� ���
			//EmptyResultDataAccessException : db���� ��ȸ�� ���ڵ尡 ���� ���
			bresult.reject("error.login.id");
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		//�α��� �Ǹ�  loginSuccess�� �̵�
		mav.setViewName("loginSuccess");
		return mav;
	}
}
