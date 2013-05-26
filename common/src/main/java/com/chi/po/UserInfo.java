package com.chi.po;

import java.util.Date;

/**
 * 用户基本信息
 * 
 * @author Administrator
 * 
 */
public class UserInfo {

	/**
	 * 用户ID
	 */
	private int userId;
	/**
	 * 用户邮件（账号）
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 账户类型
	 */
	private int accountType;
	/**
	 * 账户类型：0-默认类型
	 */
	public static int ACCOUNTTYPE_DEFAULT = 0;
	
	/**
	 * 账户来源类型 0-注册，1-新浪微博，2-qq，3-淘宝
	 */
	private int sourceType;
	public static int SOURCETYPE_REGISTER = 0;
	public static int SOURCETYPE_WEIBO = 1;
	public static int SOURCETYPE_QQ = 2;
	public static int SOURCETYPE_TAOBAO = 3;

	/**
	 * accessToken
	 */
	private String accessToken;
	/**
	 * QQ-openId
	 * WEIBO-uid
	 */
	private String openId;
	/**
	 * 性别：1-男，2-女
	 */
	private int gender;
	/**
	 * 出生年月
	 */
	private String birthday;
	
	private Date createTime;
	private Date updateTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
