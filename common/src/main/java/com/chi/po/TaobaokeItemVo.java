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

    private static final long serialVersionUID = -6098277479642105477L;

    public TaobaokeItemVo() {
	super();
    }

    public TaobaokeItemVo(MongoTaobaokeItemVo mongoTaobaokeItemVo) 
    {
	this.numIid = mongoTaobaokeItemVo.getNumIid();
	this.cid = mongoTaobaokeItemVo.getCid();
	this.createTime = mongoTaobaokeItemVo.getCreateTime();
	this.itemType = mongoTaobaokeItemVo.getItemType();
	this.overseasItem = mongoTaobaokeItemVo.getOverseasItem();
	this.updateTime = mongoTaobaokeItemVo.getUpdateTime();

	TaobaokeItem taobaokeItem = mongoTaobaokeItemVo.getTaobaokeItem();
	this.clickUrl = taobaokeItem.getClickUrl();
	this.commission = taobaokeItem.getCommission();
	this.commissionNum = taobaokeItem.getCommissionNum();
	this.commissionRate = taobaokeItem.getCommissionRate();
	this.commissionVolume = taobaokeItem.getCommissionVolume();
	this.couponEndTime = taobaokeItem.getCouponEndTime();
	this.couponPrice = taobaokeItem.getCouponPrice();
	this.couponRate = taobaokeItem.getCouponRate();
	this.couponStartTime = taobaokeItem.getCouponStartTime();
	this.itemLocation = taobaokeItem.getItemLocation();
	this.keywordClickUrl = taobaokeItem.getKeywordClickUrl();
	this.nick = taobaokeItem.getNick();
	this.picUrl = taobaokeItem.getPicUrl();
	this.price = taobaokeItem.getPrice();
	this.promotionPrice = taobaokeItem.getPromotionPrice();
	this.sellerCreditScore = taobaokeItem.getSellerCreditScore();
	this.sellerId = taobaokeItem.getSellerId();
	this.shopClickUrl = taobaokeItem.getShopClickUrl();
	this.shopType = taobaokeItem.getShopType();
	this.taobaokeCatClickUrl = taobaokeItem.getTaobaokeCatClickUrl();
	this.title = taobaokeItem.getTitle();
	this.volume = taobaokeItem.getVolume();
    }

    /**
     * 商品Id
     */
    private Long numIid;

    /**
     * 77chi的商品类别
     */
    private int itemType;
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

    /**
     * 淘宝客商品(TaobaokeItem)的所有属性
     */
    private String clickUrl;
    private String commission;
    private String commissionNum;
    private String commissionRate;
    private String commissionVolume;
    private String couponEndTime;
    private String couponPrice;
    private String couponRate;
    private String couponStartTime;
    private String itemLocation;
    private String keywordClickUrl;
    private String nick;
    // private Long numIid;
    private String picUrl;
    private String price;
    private String promotionPrice;
    private Long sellerCreditScore;
    private Long sellerId;
    private String shopClickUrl;
    private String shopType;
    private String taobaokeCatClickUrl;
    private String title;
    private Long volume;

    public Long getNumIid()
    {
	return numIid;
    }

    public void setNumIid(Long numIid)
    {
	this.numIid = numIid;
    }

    public Long getCid()
    {
	return cid;
    }

    public void setCid(Long cid)
    {
	this.cid = cid;
    }

    public String getOverseasItem()
    {
	return overseasItem;
    }

    public void setOverseasItem(String overseasItem)
    {
	this.overseasItem = overseasItem;
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

    public int getItemType()
    {
	return itemType;
    }

    public void setItemType(int itemType)
    {
	this.itemType = itemType;
    }

    public String getClickUrl()
    {
	return clickUrl;
    }

    public void setClickUrl(String clickUrl)
    {
	this.clickUrl = clickUrl;
    }

    public String getCommission()
    {
	return commission;
    }

    public void setCommission(String commission)
    {
	this.commission = commission;
    }

    public String getCommissionNum()
    {
	return commissionNum;
    }

    public void setCommissionNum(String commissionNum)
    {
	this.commissionNum = commissionNum;
    }

    public String getCommissionRate()
    {
	return commissionRate;
    }

    public void setCommissionRate(String commissionRate)
    {
	this.commissionRate = commissionRate;
    }

    public String getCommissionVolume()
    {
	return commissionVolume;
    }

    public void setCommissionVolume(String commissionVolume)
    {
	this.commissionVolume = commissionVolume;
    }

    public String getCouponEndTime()
    {
	return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime)
    {
	this.couponEndTime = couponEndTime;
    }

    public String getCouponPrice()
    {
	return couponPrice;
    }

    public void setCouponPrice(String couponPrice)
    {
	this.couponPrice = couponPrice;
    }

    public String getCouponRate()
    {
	return couponRate;
    }

    public void setCouponRate(String couponRate)
    {
	this.couponRate = couponRate;
    }

    public String getCouponStartTime()
    {
	return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime)
    {
	this.couponStartTime = couponStartTime;
    }

    public String getItemLocation()
    {
	return itemLocation;
    }

    public void setItemLocation(String itemLocation)
    {
	this.itemLocation = itemLocation;
    }

    public String getKeywordClickUrl()
    {
	return keywordClickUrl;
    }

    public void setKeywordClickUrl(String keywordClickUrl)
    {
	this.keywordClickUrl = keywordClickUrl;
    }

    public String getNick()
    {
	return nick;
    }

    public void setNick(String nick)
    {
	this.nick = nick;
    }

    public String getPicUrl()
    {
	return picUrl;
    }

    public void setPicUrl(String picUrl)
    {
	this.picUrl = picUrl;
    }

    public String getPrice()
    {
	return price;
    }

    public void setPrice(String price)
    {
	this.price = price;
    }

    public String getPromotionPrice()
    {
	return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice)
    {
	this.promotionPrice = promotionPrice;
    }

    public Long getSellerCreditScore()
    {
	return sellerCreditScore;
    }

    public void setSellerCreditScore(Long sellerCreditScore)
    {
	this.sellerCreditScore = sellerCreditScore;
    }

    public Long getSellerId()
    {
	return sellerId;
    }

    public void setSellerId(Long sellerId)
    {
	this.sellerId = sellerId;
    }

    public String getShopClickUrl()
    {
	return shopClickUrl;
    }

    public void setShopClickUrl(String shopClickUrl)
    {
	this.shopClickUrl = shopClickUrl;
    }

    public String getShopType()
    {
	return shopType;
    }

    public void setShopType(String shopType)
    {
	this.shopType = shopType;
    }

    public String getTaobaokeCatClickUrl()
    {
	return taobaokeCatClickUrl;
    }

    public void setTaobaokeCatClickUrl(String taobaokeCatClickUrl)
    {
	this.taobaokeCatClickUrl = taobaokeCatClickUrl;
    }

    public String getTitle()
    {
	return title;
    }

    public void setTitle(String title)
    {
	this.title = title;
    }

    public Long getVolume()
    {
	return volume;
    }

    public void setVolume(Long volume)
    {
	this.volume = volume;
    }

}
