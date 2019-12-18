package controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.ShopService;

//index.shop 요청이 호출되는 클래스
public class IndexController {
	//  p:shopService-ref="shopService"/>
	private ShopService shopService;
	// itemDao 객체를 저장하고 있는 ShopService 객체 주입. 호출되어져 있을 때 이미 주입되어져 있음.
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	@RequestMapping //index.shop 요청시 호출되는 메서드
	public ModelAndView itemList() {
		//ModelAndView : mvc의 actionforward기능과 redirect기능을 가진 클래스.
		//             : Model -> view에 전달될 데이타 저장하는 객체
		//             : view : view 설정 객체
		//Item : db의 안에 컬럼명과 똑같이 
		//itemlist : item 테이블의 모든 컬럼, 모든레코드 정보를
		//			 item 객체의 List 객체로 저장.
		List<Item> itemList = shopService.getItemList();
		ModelAndView mav = new ModelAndView("index");// view 설정
		mav.addObject("itemList", itemList);//데이터 설정
		return mav; //mav에는 view이름과 전달되는 데이터를 모두 전달. 디스패쳐서블릿에 전달
	}
}
