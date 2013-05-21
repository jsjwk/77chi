package com.chi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chi.dao.TaobaokeItemDao;
import com.chi.po.TaobaokeItemVo;

@Service(value="mongoTaobaokeItemService")
public class MongoTaobaokeItemServiceImpl implements MongoTaobaokeItemService {

    @Resource(name="mongoTaobaokeItemDao")
    private TaobaokeItemDao mongoTaobaokeItemDao;
    
    @Override
    public List<TaobaokeItemVo> findAllItems()
    {
	return mongoTaobaokeItemDao.findAllItems();
    }

	@Override
	public TaobaokeItemVo getItemByNumIid(Long numIid) {
		return mongoTaobaokeItemDao.getItemByNumIid(numIid);
	}

	@Override
	public List<TaobaokeItemVo> findItemsByCid(Long cid) {
		return mongoTaobaokeItemDao.findItemsByCid(cid);
	}

	@Override
	public List<TaobaokeItemVo> findItemsByItemType(int itemType) {
		return mongoTaobaokeItemDao.findItemsByItemType(itemType);
	}

}
