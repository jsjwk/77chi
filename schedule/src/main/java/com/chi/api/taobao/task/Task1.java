package com.chi.api.taobao.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chi.api.taobao.service.TaobaoApiService;
import com.chi.dao.TaobaokeItemDao;
import com.chi.po.TaobaokeItemDetailVo;
import com.chi.po.TaobaokeItemVo;
import com.taobao.api.ApiException;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.domain.TaobaokeItemDetail;

public class Task1 {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-schedule.xml");
	private static TaobaoApiService taobaoApiService = (TaobaoApiService) context.getBean("taobaoApiService");
	private static TaobaokeItemDao taobaokeItemDao = (TaobaokeItemDao) context.getBean("taobaokeItemDao");

	private static final Logger LOG = LoggerFactory.getLogger(Task1.class);
	
	public static void main(String[] args) throws ApiException
	{
		Date now = new Date();
		Long cid = 50026316L;
		//获取淘宝客商品：饮料
		List<TaobaokeItem> listTaobaokeItem = taobaoApiService.getTaobaokeItems(cid, 8L, 40L);
		List<Long> numIidsList = new ArrayList<Long>();
		for (TaobaokeItem taobaokeItem : listTaobaokeItem) 
		{
			TaobaokeItemVo vo = new TaobaokeItemVo(taobaokeItem);
			vo.setCid(cid);
			vo.setCreateTime(now);
			vo.setUpdateTime(now);
			vo.setOverseasItem("false");
			//存储商品基本信息
			taobaokeItemDao.insertTaobaokeItemVo(vo);
			numIidsList.add(taobaokeItem.getNumIid());
		}
		
		
		/**
		 * 
		List<TaobaokeItemDetailVo> listTaobaokeItemDetailVo = new ArrayList<TaobaokeItemDetailVo>();
		StringBuilder numIidBuilder = new StringBuilder();
		for (int i = 1; i <= numIidsList.size(); i++) 
		{
			numIidBuilder.append(numIidsList.get(i-1)).append(",");
			if(i%10 == 0 || i==numIidsList.size())
			{//每十条执行一次详细信息查询
				//获取商品详细信息
				System.out.println("i="+i);
				List<TaobaokeItemDetail> listTaobaokeItemDetail = taobaoApiService.getTaobaokeItemDetail(numIidBuilder.deleteCharAt(numIidBuilder.length()-1).toString());
				System.out.println("size="+listTaobaokeItemDetail.size());
				for (TaobaokeItemDetail taobaokeItemDetail : listTaobaokeItemDetail) 
				{
					TaobaokeItemDetailVo taobaokeItemDetailVo = new TaobaokeItemDetailVo(taobaokeItemDetail);
					taobaokeItemDetailVo.setCreateTime(now);
					taobaokeItemDetailVo.setUpdateTime(now);
					listTaobaokeItemDetailVo.add(taobaokeItemDetailVo);
				}
				numIidBuilder.delete(0, numIidBuilder.length()-1);
			}
		}
		
		//存储详细信息
		taobaokeItemDao.batchInsertTaobaokeItemDetailVo(listTaobaokeItemDetailVo);
		 */
		
	}
	
}
