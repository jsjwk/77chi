package com.bb.neighbor.user.model;

import java.util.List;

import com.bb.mongo.model.BasicEntry;
import com.bb.mongo.utils.DateFormat;
import com.bb.neighbor.common.Constants;
import com.google.code.morphia.annotations.Entity;

@Entity(value = "userInfo", noClassnameStored = true)
public class UserInfo extends BasicEntry {

	private static final long serialVersionUID = -91920424040332098L;

	public UserInfo() {
		super();
	}

	/**
	 * 人人网用户ID
	 */
	private Long renrenUserId;

	/**
	 * 人人网用户名
	 */
	private String userName;

	/**
	 * email
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 头像链接 100*100大小
	 */
	private String headUrl;

	/**
	 * 头像链接 50*50大小
	 */
	private String tinyUrl;

	/**
	 * 头像链接 200*200大小
	 */
	private String mainUrl;

	/**
	 * 性别：值1表示男性；值0表示女性
	 */
	private Long sex;

	/**
	 * 是否为星级用户，值“1”表示“是”；值“0”表示“不是”
	 */
	private String star;

	/**
	 * 是否为vip用户，值1表示是；值0表示不是
	 */
	private Long zidou;

	/**
	 * 是否为vip用户等级，前提是zidou节点必须为1
	 */
	private Long vip;

	/**
	 * 出生时间
	 */
	private Long birthday;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 家乡信息
	 */
	private Hometown hometown;

	/**
	 * 工作信息
	 */
	private List<WorkHistory> workHistory;

	/**
	 * 就读大学信息
	 */
	private List<UniversityInfo> universityInfo;

	/**
	 * 就读高中学校信息
	 */
	private List<HsInfo> hsInfo;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 验证码
	 */
	private Integer authCode;
	/**
	 * 帐号在独立站点是否激活。1：是；0：否
	 */
	private Integer isActivate;
	/**
	 * 职业：1:普通职业；2：学生；3：自由职业；4：其他
	 */
	private String career;
	/**
	 * 习惯
	 */
	private Habit habit;
	/**
	 * 用于显示(不保存)
	 */
//	@Transient
//	private Purpose purpose;

	public Long getRenrenUserId() {
		return renrenUserId;
	}

	public void setRenrenUserId(Long renrenUserId) {
		this.renrenUserId = renrenUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getTinyUrl() {
		return tinyUrl;
	}

	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public Long getZidou() {
		return zidou;
	}

	public void setZidou(Long zidou) {
		this.zidou = zidou;
	}

	public Long getVip() {
		return vip;
	}

	public void setVip(Long vip) {
		this.vip = vip;
	}

	public String getBirthday() {
		return DateFormat.longToStr(birthday == null ? null : birthday, Constants.PARAM_DATE);
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public Hometown getHometown() {
		return hometown;
	}

	public void setHometown(Hometown hometown) {
		this.hometown = hometown;
	}

	public List<WorkHistory> getWorkHistory() {
		return workHistory;
	}

	public void setWorkHistory(List<WorkHistory> workHistory) {
		this.workHistory = workHistory;
	}

	public List<UniversityInfo> getUniversityInfo() {
		return universityInfo;
	}

	public void setUniversityInfo(List<UniversityInfo> universityInfo) {
		this.universityInfo = universityInfo;
	}

	public List<HsInfo> getHsInfo() {
		return hsInfo;
	}

	public void setHsInfo(List<HsInfo> hsInfo) {
		this.hsInfo = hsInfo;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAuthCode() {
		return authCode;
	}

	public void setAuthCode(Integer authCode) {
		this.authCode = authCode;
	}

	public Integer getIsActivate() {
		return isActivate;
	}

	public void setIsActivate(Integer isActivate) {
		this.isActivate = isActivate;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public Habit getHabit() {
		return habit;
	}

	public void setHabit(Habit habit) {
		this.habit = habit;
	}

	// public Purpose getPurpose() {
	// return purpose;
	// }
	//
	// public void setPurpose(Purpose purpose) {
	// this.purpose = purpose;
	// }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
