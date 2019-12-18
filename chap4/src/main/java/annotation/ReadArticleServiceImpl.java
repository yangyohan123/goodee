package annotation;

import org.springframework.stereotype.Component;

import xml.Article;
import xml.ReadArticleService;

//Component("...") -> ...이름 재설정하여 객체 생성
//Component -> 클래스이름ReadArticleServiceImpl으로 객체 생성
@Component("readArticleService")
public class ReadArticleServiceImpl implements ReadArticleService{

	public Article getArticleAndReadCnt(int id) throws Exception {
		if(id==0) {
			throw new Exception("id는 0이 안됨.");
		}
		return new Article(id);
	}
	
}
