package com.chi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chi.dao.TaobaokeItemDao;
import com.chi.po.TaobaokeItemVo;

@Service(value="taobaokeItemService")
public class TaobaokeItemServiceImpl implements TaobaokeItemService {

	@Resource(name="taobaokeItemDao")
	private TaobaokeItemDao taobaokeItemDao;
	
	@Override
	public boolean save(TaobaokeItemVo taobaokeItemVo) 
	{
		return taobaokeItemDao.insertTaobaokeItemVo(taobaokeItemVo);
	}

}
