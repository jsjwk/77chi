package com.chi.po;

import java.io.Serializable;
import java.util.Date;

import com.taobao.api.domain.TaobaokeItem;

/**
 * 
 * @author Administrator
 * 
 */
public class TaobaokeItemVo implements Serializable {

	private static final long serialVersionUID = -255183940792610328L;

	public TaobaokeItemVo(TaobaokeItem taobaokeItem) {
		super();
		this.taobaokeItem = taobaokeItem;
	}

	/**
	 * 淘宝客商品
	 */
	private TaobaokeItem taobaokeItem;

	/**
	 * 标准商品后台类目id
	 */
	private Long cid;
	/**
	 * 是否海外商品
	 */
	private String overseasItem;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public TaobaokeItem getTaobaokeItem() {
		return taobaokeItem;
	}

	public void setTaobaokeItem(TaobaokeItem taobaokeItem) {
		this.taobaokeItem = taobaokeItem;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getOverseasItem() {
		return overseasItem;
	}

	public void setOverseasItem(String overseasItem) {
		this.overseasItem = overseasItem;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
