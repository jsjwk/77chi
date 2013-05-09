package com.bb.neighbor.common;

/**
 * 头像模型
 * 
 * @author ly.jiao@live.cn
 * 
 */
public class HeadImg {
	/**
	 * 100*100大小
	 */
	private String bigUrl;

	/**
	 * 50*50大小
	 */
	private String midUrl;

	/**
	 * 30*30大小
	 */
	private String tinyUrl;

	public String getBigUrl() {
		return bigUrl;
	}

	public void setBigUrl(String bigUrl) {
		this.bigUrl = bigUrl;
	}

	public String getMidUrl() {
		return midUrl;
	}

	public void setMidUrl(String midUrl) {
		this.midUrl = midUrl;
	}

	public String getTinyUrl() {
		return tinyUrl;
	}

	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}
}
