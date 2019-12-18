package dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource; //db Connection 객체


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import logic.Item;

public class ItemDao {
	// p:dataSource-ref="dataSource"/>
	private NamedParameterJdbcTemplate template;//spring jdbc 프레임워크
	/* mapper의 기능
	 * Item item = new Item();
	 * item.setId(rs.getString("id));
	 * item.setName(rs.getString("name"));
	 * ...
	 */
	RowMapper<Item> mapper =
			new BeanPropertyRowMapper<Item>(Item.class);
	//Connection 객체 주입 = > xml에서 설정됌 
	//dataSource : org.springframework.jdbc.datasource.DriverManagerDataSource
	//template : scott 1234인 유저의 데이터베이스를 사용할 수 있음.
	public void setDataSource(DataSource dataSource) {
		this.template = new NamedParameterJdbcTemplate(dataSource);
		
	}
	
	public  List<Item> list() {
		
		//mapper에 맞춰서 id면 item.id에 넣어줌, name이면 item.name에 넣어줌...
		return template.query("select * from item", mapper);
	}

	public Item selectOne(Integer id) {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", id);
		//:id -> param의 id값과 이름이 같아야 함.
		return template.queryForObject("select * from item where id=:id", param, mapper);
	}
}
