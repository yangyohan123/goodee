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
	
	@GetMapping // @RequestMapping(method=RequestMethod.GET)와 같은 의미
	            // 4.0 이후 버전에서 가능
	//model은 뷰단에 전달할 객체
	public String loginForm(Model model) {
		model.addAttribute(new User());
		return "login";
	}
	
	//HttpSession session : session객체 전달
	//BindingResult bresult : 유효성 검사
	//user : 파라미터값(입력된 값)을 저장하고 있는 객체
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
			//비밀번호 같은 경우
			if(user.getPassword().equals(dbuser.getPassword())) {
				session.setAttribute("loginUser", dbuser);
			}else {
			// 비밀번호 틀린 경우 : 글로벌에러
				bresult.reject("error.login.password");
				mav.getModel().putAll(bresult.getModel());
				return mav;
			}
		}catch(EmptyResultDataAccessException e) {
			//id없는 경우
			//EmptyResultDataAccessException : db에서 조회된 레코드가 없는 경우
			bresult.reject("error.login.id");
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		//로그인 되면  loginSuccess로 이동
		mav.setViewName("loginSuccess");
		return mav;
	}
}
