package com.chi.po;

import java.io.Serializable;
import java.util.Date;

import com.taobao.api.domain.TaobaokeItemDetail;

public class TaobaokeItemDetailVo implements Serializable {

	private static final long serialVersionUID = 2471350883100485517L;

	public TaobaokeItemDetailVo(TaobaokeItemDetail taobaokeItemDetail) {
		super();
		this.taobaokeItemDetail = taobaokeItemDetail;
	}

	/**
	 * 详细
	 */
	private TaobaokeItemDetail taobaokeItemDetail;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public TaobaokeItemDetail getTaobaokeItemDetail() {
		return taobaokeItemDetail;
	}

	public void setTaobaokeItemDetail(TaobaokeItemDetail taobaokeItemDetail) {
		this.taobaokeItemDetail = taobaokeItemDetail;
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
