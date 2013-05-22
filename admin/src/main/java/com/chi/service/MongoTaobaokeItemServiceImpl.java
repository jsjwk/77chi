package com.chi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chi.dao.mongo.MongoTaobaokeItemDao;
import com.chi.po.MongoTaobaokeItemVo;

@Service(value="mongoTaobaokeItemService")
public class MongoTaobaokeItemServiceImpl implements MongoTaobaokeItemService {

    @Resource(name="mongoTaobaokeItemDao")
    private MongoTaobaokeItemDao mongoTaobaokeItemDao;
    
    @Override
    public List<MongoTaobaokeItemVo> findAllItems()
    {
	return mongoTaobaokeItemDao.findAllItems();
    }

	@Override
	public MongoTaobaokeItemVo getItemByNumIid(Long numIid) {
		return mongoTaobaokeItemDao.getItemByNumIid(numIid);
	}

	@Override
	public List<MongoTaobaokeItemVo> findItemsByCid(Long cid) {
		return mongoTaobaokeItemDao.findItemsByCid(cid);
	}

	@Override
	public List<MongoTaobaokeItemVo> findItemsByItemType(int itemType) {
		return mongoTaobaokeItemDao.findItemsByItemType(itemType);
	}

	@Override
	public boolean deleteItemByNumIid(Long numIid)
	{
	    return mongoTaobaokeItemDao.deleteItemByNumIid(numIid);
	}

}
