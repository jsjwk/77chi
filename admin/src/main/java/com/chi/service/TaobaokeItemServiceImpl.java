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
    public boolean save(TaobaokeItemVo taobaokeItemVo)
    {
	return taobaokeItemDao.insertTaobaokeItemVo(taobaokeItemVo);
    }

    @Override
    public List<TaobaokeItemVo> listTaobaokeItemVoByType(int itemType)
    {
	return taobaokeItemDao.findItemsByItemType(itemType);
    }

}
