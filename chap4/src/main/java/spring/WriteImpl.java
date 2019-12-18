package spring;

public class WriteImpl {
	private ArticleDao dao;
	//dao : MariadbArticleDao 객체 저장되어 있는 상태. xml에서 주입됨
	//생성자 articleDao가 주입이 되어야 함.
	public WriteImpl (ArticleDao dao) {
		this.dao = dao;
	}
	public void write() { //핵심로직.
		System.out.println("WriteImpl.write 메서드 호출");
		dao.insert();
	}
}
