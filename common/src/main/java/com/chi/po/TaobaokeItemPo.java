package com.chi.po;

import java.util.List;

import com.taobao.api.domain.TaobaokeItem;

/**
 * @author wangkui
 * 
 */
public class TaobaokeItemPo {

    private Long totalResults;
    private List<TaobaokeItem> listTaobaokeItem;

    public TaobaokeItemPo(Long totalResults, List<TaobaokeItem> listTaobaokeItem) {
	super();
	this.totalResults = totalResults;
	this.listTaobaokeItem = listTaobaokeItem;
    }

    public Long getTotalResults()
    {
	return totalResults;
    }

    public void setTotalResults(Long totalResults)
    {
	this.totalResults = totalResults;
    }

    public List<TaobaokeItem> getListTaobaokeItem()
    {
	return listTaobaokeItem;
    }

    public void setListTaobaokeItem(List<TaobaokeItem> listTaobaokeItem)
    {
	this.listTaobaokeItem = listTaobaokeItem;
    }

}
