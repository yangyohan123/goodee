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
		user.setUsername("ȫ�浿");
		
		return new User();
	}
	
	
	/* ��ûó��
	 * GET��� :
	 * POST��� :
	 */

	@RequestMapping(method=RequestMethod.GET)
	public String userEntryForm() {
		//return "userEntry";//view�� ���� userEntry.jsp
		return null;
		
	}
	//User user : bean���� �����ϸ� id�� id password�� password�� �˾Ƽ� ��.
	//BindingResult bindResult : ��ȿ�� �˻�
	@RequestMapping(method=RequestMethod.POST)
	//user : �Ķ���Ͱ�(�Էµ� ��)�� �����ϰ� �ִ� ��ü
	//BindingResult bindResult :��ȿ�� �˻�(error)����
	public ModelAndView userEntry(User user,BindingResult bindResult) {
		ModelAndView mav = new ModelAndView();
		//validate �޼��� : �ش� �����޽����� ������ bindResult�� ����
		userValidator.validate(user, bindResult);
		if(bindResult.hasErrors()) {
			//userEntry��ü�� ��.
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}try {
			shopService.insertUser(user);
			//user��ü ����(�Էµ� �Ķ���Ͱ���)
			mav.addObject("user", user);
			//DataIntegrityViolationException : springjdbc���� �߻��Ǵ� ����
			//Ű �ߺ� ������ ���
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			//�۷ι����� 
			bindResult.reject("error.duplicate.user");
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		
		mav.setViewName("userEntrySuccess");
		return mav;
		
	}
	@InitBinder // �Ķ���Ͱ� �� ��ȯ
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 * Date.class : ����ȯ ����� �Ǵ� �ڷ���
		 * ����ȯ ����� birthday�� ��� �˰� ã�ư��°� ?
		 * bean.class�� user�̱⶧���� user���� date�� ã�ư�.
		 * format : ��������
		 * true/false : ���Է����/���Էº���
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
		
	}
	
	
}
