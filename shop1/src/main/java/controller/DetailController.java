package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.ShopService;

public class DetailController {
	private ShopService shopService;
	
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	//Integer id : 파라미터 값을 전달 받음.
	@RequestMapping //detail.shop?id=1
	public ModelAndView detail(Integer id) {
		Item item = shopService.getItemById(id);
		ModelAndView mav = new ModelAndView();//"detail"기기본 설정
		mav.addObject("item", item);
		return mav;
	}
}
