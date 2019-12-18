package logic;

import java.util.List;

import dao.ItemDao;

public class ShopService {
	// p:dataSource-ref="itemDao"/>
	// db와 연결된 itemDao 객체 주입
	private ItemDao itemDao;
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public List<Item> getItemList() {
	
		return itemDao.list();
	}
	
	public Item getItemById(Integer id) {
		
		return itemDao.selectOne(id);
	}
}
