package com.chi.api.taobao.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chi.api.taobao.service.TaobaoApiService;
import com.chi.dao.TaobaokeItemDao;
import com.chi.dao.TaobaokeItemDetailDao;
import com.chi.po.TaobaokeItemDetailVo;
import com.chi.po.TaobaokeItemVo;
import com.taobao.api.ApiException;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.domain.TaobaokeItemDetail;

public class Task1 {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-schedule.xml");
	private static TaobaoApiService taobaoApiService = (TaobaoApiService) context.getBean("taobaoApiService");
	private static TaobaokeItemDao taobaokeItemDao = (TaobaokeItemDao) context.getBean("taobaokeItemDao");
	private static TaobaokeItemDetailDao taobaokeItemDetailDao = (TaobaokeItemDetailDao) context.getBean("taobaokeItemDetailDao");

	private static final Logger LOG = LoggerFactory.getLogger(Task1.class);

	public static void main(String[] args) throws ApiException 
	{
	    /*
	     * 
		//获取[保健营养]并且保存
		getItemsAndStore(50020275L);
		getItemsAndStore(50026800L);
		
		//获取[进口食品]并且保存
		
		
		//获取[酒类]并且保存
		getItemsAndStore(50008141L);
		
		//获取[母婴食品]并且保存
		getItemsAndStore(35L);
		getItemsAndStore(50022517L);
		
		//获取[水果]并且保存
		getItemsAndStore(50050725L);
		
		//获取[休闲零食]并且保存
		getItemsAndStore(50002766L);
	     */
		
		//获取[饮料]并且保存
		getItemsAndStore(50026316L);
		
	}

	/**
	 * 获取某个类别下面的商品&保存
	 * @param cid
	 * @throws ApiException
	 */
	private static void getItemsAndStore(Long cid) throws ApiException 
	{
		Date now = new Date();
		// 获取淘宝客商品
		List<TaobaokeItem> listTaobaokeItem = taobaoApiService.getTaobaokeItems(cid, 1L, 40L);
		List<Long> numIidsList = new ArrayList<Long>();
		for (TaobaokeItem taobaokeItem : listTaobaokeItem) 
		{
			TaobaokeItemVo vo = new TaobaokeItemVo(taobaokeItem);
			Long numIid = taobaokeItem.getNumIid();
			vo.setNumIid(numIid);
			vo.setCid(cid);
			vo.setCreateTime(now);
			vo.setUpdateTime(now);
			vo.setOverseasItem("false");
			// 存储商品基本信息
			taobaokeItemDao.insertTaobaokeItemVo(vo);
			numIidsList.add(taobaokeItem.getNumIid());
		}

		List<TaobaokeItemDetailVo> listTaobaokeItemDetailVo = new ArrayList<TaobaokeItemDetailVo>();
		StringBuilder numIidBuilder = new StringBuilder();
		for (int i = 1; i <= numIidsList.size(); i++) 
		{
			numIidBuilder.append(numIidsList.get(i - 1)).append(",");
			if (i % 10 == 0 || i == numIidsList.size()) 
			{// 每十条执行一次详细信息查询
				// 获取商品详细信息
				List<TaobaokeItemDetail> listTaobaokeItemDetail = taobaoApiService.getTaobaokeItemDetail(numIidBuilder.deleteCharAt(numIidBuilder.length() - 1).toString());
				for (TaobaokeItemDetail taobaokeItemDetail : listTaobaokeItemDetail) 
				{
					TaobaokeItemDetailVo taobaokeItemDetailVo = new TaobaokeItemDetailVo(taobaokeItemDetail);
					// taobaokeItemDetailVo.setNumIid(taobaokeItemDetail.getItem().getNumIid());
					taobaokeItemDetailVo.setNumIid(UUID.randomUUID().getLeastSignificantBits());
					taobaokeItemDetailVo.setCreateTime(now);
					taobaokeItemDetailVo.setUpdateTime(now);
					listTaobaokeItemDetailVo.add(taobaokeItemDetailVo);
				}
				numIidBuilder.delete(0, numIidBuilder.length() - 1);
			}
		}

		// 存储详细信息
		taobaokeItemDetailDao.batchInsertTaobaokeItemDetailVo(listTaobaokeItemDetailVo);
	}

}
