package com.bb.neighbor.user.home;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.bb.mongo.utils.SequenceUtil;
import com.bb.neighbor.user.dao.UserDAO;
import com.bb.neighbor.user.model.UserInfo;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

@Component
public class UserHome {

	private UserDAO userDao;

	public UserHome() {
		this.userDao = UserDAO.getInstance();
	}

	public Query<UserInfo> getQuery() {
		return userDao.createQuery();
	}

	public UpdateOperations<UserInfo> getUpdateOps() {
		return userDao.createUpdateOperations();
	}

	/**
	 * 保存人人网用户信息到本地数据库
	 * 
	 * @param userInfo
	 */
	public void saveUser(UserInfo userInfo) {
		Query<UserInfo> query = userDao.createQuery();
		query.field("renrenUserId").equal(userInfo.getRenrenUserId());
		UserInfo ui = userDao.findOne(query);

		if (null == ui) {
			userInfo.setId(SequenceUtil.getId(SequenceUtil.USERINFO_ID_VALUE));
		} else {
			userInfo.setId(ui.getId());
		}
		userDao.save(userInfo);
	}

	/**
	 * 保存用户
	 * 
	 * @param userInfo
	 */
	public UserInfo save(UserInfo userInfo) {
		if (userInfo.getId() == null)
			userInfo.setId(SequenceUtil.getId(SequenceUtil.USERINFO_ID_VALUE));
		userDao.save(userInfo);
		return userInfo;
	}

	/**
	 * 根据人人用户ID查询用户对象
	 * 
	 * @param renrenUserId
	 * @return
	 */
	public UserInfo getUserInfoByRenrenUserId(String renrenUserId) {
		Query<UserInfo> query = userDao.createQuery();
		query.field("renrenUserId").equal(Long.parseLong(renrenUserId));
		return userDao.findOne(query);
	}

	/**
	 * 根据用户ID查询用户对象
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserId(Integer userId) {
		Query<UserInfo> query = userDao.createQuery().filter("id", userId);
		return userDao.findOne(query);
	}

	/**
	 * 用于消息展示
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getMessageInfoById(Integer userId) {
		Query<UserInfo> query = userDao.createQuery().retrievedFields(true, "tinyUrl", "userName").filter("id", userId);
		return userDao.findOne(query);
	}

	/**
	 * 获取平台用户总数
	 * 
	 * @return
	 */
	public long getUserCount() {
		Query<UserInfo> query = userDao.createQuery();
		return query.countAll();
	}

	/**
	 * 判断验证码是否存在
	 * 
	 * @param authCode
	 * @return
	 */
	public boolean authCodeIsHave(Integer authCode) {
		Query<UserInfo> query = userDao.createQuery();
		query.filter("authCode =", authCode);
		if (null == userDao.findOne(query)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 验证 验证码是否有效
	 * 
	 * @param authCode
	 * @return
	 */
	public boolean verifyAuthCode(Integer authCode) {
		Query<UserInfo> query = userDao.createQuery();
		query.filter("authCode =", authCode);
		// query.filter("isActivate =", StatusConstants.USER_ACCOUNT_ACTIVATE_NOT);
		if (null == userDao.findOne(query)) {
			return false;
		} else {
			return true;
		}
	}

	public void update(Query<UserInfo> query, UpdateOperations<UserInfo> ops) {
		userDao.update(query, ops);
	}

	/**
	 * 根据验证码，获取用户
	 * 
	 * @param authCode
	 * @return
	 */
	public UserInfo getUserInfoByAuthCode(Integer authCode) {
		Query<UserInfo> query = userDao.createQuery();
		query.filter("authCode =", authCode);
		return userDao.findOne(query);
	}

	public List<UserInfo> findByName(String name) {
		Pattern pattern = Pattern.compile(".*" + name + ".*$", Pattern.CASE_INSENSITIVE);
		Query<UserInfo> q = userDao.createQuery().filter("userName", pattern);
		return userDao.findList(q);
	}

	/**
	 * 更新session中的用户信息
	 * 
	 * @param inv
	public void replaceSessionUser(InvocationLocal inv) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Query<UserInfo> query = userDao.createQuery();
		query.filter("id", userInfo.getId());
		UserInfo user = userDao.findOne(query);
		inv.getRequest().getSession().setAttribute(Constants.SESSION_USER_KEY, user);
	}
	 */

	/**
	 * 根据邮箱获取用户
	 * 
	 * @param email
	 * @return
	 */
	public UserInfo getUserInfoByEmail(String email) {
		Query<UserInfo> query = userDao.createQuery();
		query.field("email").equal(email);
		return userDao.findOne(query);
	}

	/**
	 * 根据邮箱、密码获取用户
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public UserInfo getUserInfoByEmailPassword(String email, String password) {
		Query<UserInfo> query = userDao.createQuery();
		query.field("email").equal(email);
		query.field("password").equal(password);
		return userDao.findOne(query);
	}

}
