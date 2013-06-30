package com.chi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chi.dao.mysql.TaobaokeItemDao;
import com.chi.po.TaobaokeItemVo;

@Service(value = "taobaokeItemService")
public class TaobaokeItemServiceImpl implements TaobaokeItemService {

	@Resource(name = "taobaokeItemDao")
	private TaobaokeItemDao taobaokeItemDao;

	@Override
	public List<TaobaokeItemVo> findAllItems() {
		return taobaokeItemDao.findAllItems();
	}

}
