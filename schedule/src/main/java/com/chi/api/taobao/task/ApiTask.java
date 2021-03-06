package com.chi.api.taobao.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chi.api.taobao.service.TaobaoApiService;
import com.chi.dao.mongo.MongoTaobaokeItemDao;
import com.chi.dao.mongo.MongoTaobaokeItemDetailDao;
import com.chi.po.MongoTaobaokeItemDetailVo;
import com.chi.po.MongoTaobaokeItemVo;
import com.chi.po.TaobaokeItemPo;
import com.taobao.api.ApiException;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.domain.TaobaokeItemDetail;

/**
 * 
 * itemType 饮料 - 1 保健营养 - 2 进口食品 - 3 酒类 - 4 母婴食品 - 5 水果 - 6 休闲零食 - 7
 * 
 * @author Administrator
 * 
 */
public class ApiTask {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-schedule.xml");
	private static TaobaoApiService taobaoApiService = (TaobaoApiService) context.getBean("taobaoApiService");
	private static MongoTaobaokeItemDao mongoTaobaokeItemDao = (MongoTaobaokeItemDao) context.getBean("mongoTaobaokeItemDao");
	private static MongoTaobaokeItemDetailDao mongoTaobaokeItemDetailDao = (MongoTaobaokeItemDetailDao) context.getBean("mongoTaobaokeItemDetailDao");

	private static final Logger LOG = LoggerFactory.getLogger(ApiTask.class);

	public static void main(String[] args) throws ApiException 
	{
		/*
	     * 
	     */
		LOG.info("获取[饮料]并且保存");
		getItemsAndStore(50026316L, 1);

		LOG.info("获取[保健营养]并且保存");
		getItemsAndStore(50020275L, 2);
		getItemsAndStore(50026800L, 2);

		// 获取[进口食品]并且保存

		LOG.info("获取[酒类]并且保存");
		getItemsAndStore(50008141L, 4);

		LOG.info("获取[母婴食品]并且保存");
		getItemsAndStore(35L, 5);
		getItemsAndStore(50022517L, 5);

		LOG.info("获取[水果]并且保存");
		getItemsAndStore(50050725L, 6);

		LOG.info("获取[休闲零食]并且保存");
		getItemsAndStore(50002766L, 7);
	}

	/**
	 * 获取某个类别下面的商品&保存
	 * 
	 * @param cid
	 * @throws ApiException
	 */
	private static void getItemsAndStore(Long cid, int itemType) throws ApiException {
		Date now = new Date();
		Long totalResults = 0L;
		Long pageNo = 1L;
		Long pageSize = 40L;

		do {
			LOG.info("[获取某个类别下面的商品&保存]cid:"+cid+",itemType="+itemType+",pageNo="+pageNo+",pageSize="+pageSize);
			totalResults = getApiAndSave(cid, itemType, now, pageNo, pageSize);
			pageNo++;
		} while ((totalResults > pageNo * pageSize) && pageNo < 10);

	}

	private static Long getApiAndSave(Long cid, int itemType, Date now, Long pageNo, Long pageSize) throws ApiException {
		Long totalResults;
		// 获取淘宝客商品
		TaobaokeItemPo taobaokeItemPo = taobaoApiService.getTaobaokeItems(cid, pageNo, pageSize);
		totalResults = taobaokeItemPo.getTotalResults();

		List<TaobaokeItem> listTaobaokeItem = taobaokeItemPo.getListTaobaokeItem();
		if (listTaobaokeItem == null || listTaobaokeItem.size() <= 0)
			return totalResults;
		List<Long> numIidsList = new ArrayList<Long>();
		List<MongoTaobaokeItemVo> listMongoTaobaokeItemVo = new ArrayList<MongoTaobaokeItemVo>();
		for (TaobaokeItem taobaokeItem : listTaobaokeItem) {
			MongoTaobaokeItemVo vo = new MongoTaobaokeItemVo(taobaokeItem);
			Long numIid = taobaokeItem.getNumIid();
			vo.setItemType(itemType);
			vo.setNumIid(numIid);
			// vo.setNumIid(UUID.randomUUID().getLeastSignificantBits());
			vo.setCid(cid);
			vo.setCreateTime(now);
			vo.setUpdateTime(now);
			vo.setOverseasItem("false");

			listMongoTaobaokeItemVo.add(vo);
			numIidsList.add(taobaokeItem.getNumIid());
		}
		// 存储商品基本信息
		mongoTaobaokeItemDao.batchInsertTaobaokeItemVo(listMongoTaobaokeItemVo);

		// 获取详细信息
		List<MongoTaobaokeItemDetailVo> listMongoTaobaokeItemDetailVo = new ArrayList<MongoTaobaokeItemDetailVo>();
		StringBuilder numIidBuilder = new StringBuilder();
		for (int i = 1; i <= numIidsList.size(); i++) {
			numIidBuilder.append(numIidsList.get(i - 1)).append(",");
			if (i % 10 == 0 || i == numIidsList.size()) {// 每十条执行一次详细信息查询
															// 获取商品详细信息
				List<TaobaokeItemDetail> listTaobaokeItemDetail = taobaoApiService.getTaobaokeItemDetail(numIidBuilder.deleteCharAt(
						numIidBuilder.length() - 1).toString());
				if (listTaobaokeItemDetail == null || listTaobaokeItemDetail.size() <= 0)
					continue;
				for (TaobaokeItemDetail taobaokeItemDetail : listTaobaokeItemDetail) {
					MongoTaobaokeItemDetailVo mongoTaobaokeItemDetailVo = new MongoTaobaokeItemDetailVo(taobaokeItemDetail);
					mongoTaobaokeItemDetailVo.setItemType(itemType);
					mongoTaobaokeItemDetailVo.setNumIid(taobaokeItemDetail.getItem().getNumIid());
					// taobaokeItemDetailVo.setNumIid(UUID.randomUUID().getLeastSignificantBits());
					mongoTaobaokeItemDetailVo.setCreateTime(now);
					mongoTaobaokeItemDetailVo.setUpdateTime(now);

					listMongoTaobaokeItemDetailVo.add(mongoTaobaokeItemDetailVo);
				}
				numIidBuilder.delete(0, numIidBuilder.length() - 1);
			}
		}

		// 存储详细信息
		mongoTaobaokeItemDetailDao.batchInsertTaobaokeItemDetailVo(listMongoTaobaokeItemDetailVo);
		return totalResults;
	}

}
