//package com.chi.test;
//
//import java.util.List;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.chi.api.taobao.service.TaobaoApiService;
//import com.chi.dao.TaobaokeItemDao;
//import com.taobao.api.ApiException;
//import com.taobao.api.domain.TaobaokeItemDetail;
//
//public class Test {
//
//	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring.xml");
//	private static TaobaoApiService taobaoApiService = (TaobaoApiService) context.getBean("taobaoApiService");
//	private static TaobaokeItemDao taobaokeItemDao = (TaobaokeItemDao) context.getBean("taobaokeItemDao");
//	
//	public static void main(String[] args) throws ApiException 
//	{
//		List<TaobaokeItemDetail> listTaobaokeItemDetail = taobaoApiService.getTaobaokeItemDetail("16789054211");
//		
//	}
//	
//}
