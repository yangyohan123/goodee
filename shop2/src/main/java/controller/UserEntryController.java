package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.ShopService;
import logic.User;
import util.UserValidator;

public class UserEntryController {
	private ShopService shopService;
	private UserValidator userValidator;
	
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}
	
	@ModelAttribute
	public User getUser() {
		User user = new User();
		user.setUsername("홍길동");
		
		return new User();
	}
	
	
	/* 요청처리
	 * GET방식 :
	 * POST방식 :
	 */

	@RequestMapping(method=RequestMethod.GET)
	public String userEntryForm() {
		//return "userEntry";//view만 설정 userEntry.jsp
		return null;
		
	}
	//User user : bean으로 전달하면 id면 id password면 password로 알아서 들어감.
	//BindingResult bindResult : 유효성 검사
	@RequestMapping(method=RequestMethod.POST)
	//user : 파라미터값(입력된 값)을 저장하고 있는 객체
	//BindingResult bindResult :유효성 검사(error)위해
	public ModelAndView userEntry(User user,BindingResult bindResult) {
		ModelAndView mav = new ModelAndView();
		//validate 메서드 : 해당 오류메시지가 있으면 bindResult에 전달
		userValidator.validate(user, bindResult);
		if(bindResult.hasErrors()) {
			//userEntry객체로 감.
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}try {
			shopService.insertUser(user);
			//user객체 전달(입력된 파라미터값들)
			mav.addObject("user", user);
			//DataIntegrityViolationException : springjdbc에서 발생되는 예외
			//키 중복 오류인 경우
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			//글로벌오류 
			bindResult.reject("error.duplicate.user");
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		
		mav.setViewName("userEntrySuccess");
		return mav;
		
	}
	@InitBinder // 파라미터값 형 변환
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 * Date.class : 형변환 대상이 되는 자료형
		 * 형변환 대상이 birthday를 어떻게 알고 찾아가는가 ?
		 * bean.class가 user이기때문에 user에서 date를 찾아감.
		 * format : 형식지정
		 * true/false : 비입력허용/비입력불허
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
		
	}
	
	
}
