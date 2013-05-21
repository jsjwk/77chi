package com.chi.po;

import java.io.Serializable;
import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.taobao.api.domain.TaobaokeItemDetail;

@Entity(value = "taobaokeItemDetailVo", noClassnameStored = true)
public class TaobaokeItemDetailVo implements Serializable {

    private static final long serialVersionUID = 2471350883100485517L;

    public TaobaokeItemDetailVo() {
	super();
    }

    public TaobaokeItemDetailVo(TaobaokeItemDetail taobaokeItemDetail) {
	super();
	this.taobaokeItemDetail = taobaokeItemDetail;
    }

    @Id
    private Long numIid;
	
    /**
	 * 77chi的商品类别
	 */
	private int itemType;
	
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

    public Long getNumIid()
    {
        return numIid;
    }

    public void setNumIid(Long numIid)
    {
        this.numIid = numIid;
    }

    public TaobaokeItemDetail getTaobaokeItemDetail()
    {
	return taobaokeItemDetail;
    }

    public void setTaobaokeItemDetail(TaobaokeItemDetail taobaokeItemDetail)
    {
	this.taobaokeItemDetail = taobaokeItemDetail;
    }

    public Date getCreateTime()
    {
	return createTime;
    }

    public void setCreateTime(Date createTime)
    {
	this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
	return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
	this.updateTime = updateTime;
    }

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

}
