package annotation;

import java.util.HashMap;
import java.util.Map;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import xml.Article;

@Component
@Aspect
@Order(2)
public class ArticleCacheAspect {
	private Map<Integer, Article> cache=
			new HashMap<Integer, Article>();
	//ReadArticleService클래스 안에 모든 메서드
	@Around("execution(public * *..ReadArticleService.*(..))")
	public Object cache(ProceedingJoinPoint joinPoint)throws Throwable{
		// getArgs():핵심로직의 매개변수 목록(오브젝트형태의 배열) 
		Integer id = (Integer)joinPoint.getArgs()[0];
		//joinPoint.getSignature().getName() : 핵심로직 메서드 이름
		System.out.println("[ACA]"
				+ joinPoint.getSignature().getName() + "(" + id + ") 메서드 호출 전");
		Article article = cache.get(id);
		
		//등록된것은 가져옴
		if(article != null) {
			System.out.println
						("[ACA] cache에서 Article[" + id + "] 가져옴");
			return article;
		}
		
		//처음 등록은 cache에 추가함
		//만약 id가 0이면 proceed에서 에러 메세지 전달.
		Object ret = joinPoint.proceed();
		System.out.println
		("[ACA]" + joinPoint.getSignature().getName() + "("+id+") 메서드 호출 후");
		
		if(ret != null && ret instanceof Article) {
			cache.put(id, (Article)ret);
			System.out.println
			("[ACA] cache에 Article["+id+"] 추가함");
		}
		return ret;
	}
}
