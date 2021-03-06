package com.chi.po;

import java.io.Serializable;
import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.taobao.api.domain.TaobaokeItem;

/**
 * 
 * @author Administrator
 * 
 */
@Entity(value = "taobaokeItemVo", noClassnameStored = true)
public class MongoTaobaokeItemVo implements Serializable {

	private static final long serialVersionUID = -255183940792610328L;

	public MongoTaobaokeItemVo() {
	    super();
	}

	public MongoTaobaokeItemVo(TaobaokeItem taobaokeItem) {
		super();
		this.taobaokeItem = taobaokeItem;
	}

	@Id
	private Long numIid;
	
	/**
	 * 77chi的商品类别
	 */
	private int itemType;
	
	/**
	 * 商品在77chi状态
	 * 1-上线
	 */
	private int status;
	
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

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

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

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getStatus()
	{
	    return status;
	}

	public void setStatus(int status)
	{
	    this.status = status;
	}

	@Override
	public String toString()
	{
	    StringBuilder builder = new StringBuilder();
	    builder.append("MongoTaobaokeItemVo [numIid=");
	    builder.append(numIid);
	    builder.append(", itemType=");
	    builder.append(itemType);
	    builder.append(", status=");
	    builder.append(status);
	    builder.append(", taobaokeItem=");
	    builder.append(taobaokeItem);
	    builder.append(", cid=");
	    builder.append(cid);
	    builder.append(", overseasItem=");
	    builder.append(overseasItem);
	    builder.append(", createTime=");
	    builder.append(createTime);
	    builder.append(", updateTime=");
	    builder.append(updateTime);
	    builder.append("]");
	    return builder.toString();
	}

}
