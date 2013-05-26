package com.chi.dao.mysql;

import java.sql.Types;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.chi.po.UserInfo;

@Repository(value = "userInfoDao")
public class UserInfoDaoImpl extends SpringJDBCDaoSupport implements UserInfoDao {

	private static String TABLE_NAME = "user_info";
	
	@Override
	public boolean saveOrUpdateUserInfo(UserInfo userInfo) 
	{
		String sql = "INSERT INTO "	+ TABLE_NAME + "(user_id,email,password,nick_name,account_type,source_type,access_token,open_id,gender,birthday,create_time,update_time)  "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE email=?,password=?，nick_name=?,account_type=?,source_type=?,access_token=?,open_id=?,gender=?,birthday=?,create_time=?,update_time=?";
		Object[] args = new Object[] { userInfo.getUserId(),StringEscapeUtils.escapeSql(userInfo.getEmail()),userInfo.getPassword(), StringEscapeUtils.escapeSql(userInfo.getNickName()),userInfo.getAccountType(),userInfo.getSourceType(),userInfo.getAccessToken(),userInfo.getOpenId(),userInfo.getGender(),userInfo.getBirthday(), userInfo.getCreateTime(), userInfo.getUpdateTime(),StringEscapeUtils.escapeSql(userInfo.getEmail()),userInfo.getPassword(), StringEscapeUtils.escapeSql(userInfo.getNickName()),userInfo.getAccountType(),userInfo.getSourceType(),userInfo.getAccessToken(),userInfo.getOpenId(),userInfo.getGender(),userInfo.getBirthday(), userInfo.getCreateTime(), userInfo.getUpdateTime() };
		int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR,	Types.INTEGER, Types.VARCHAR,Types.TIMESTAMP, Types.TIMESTAMP,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR,	Types.INTEGER, Types.VARCHAR,Types.TIMESTAMP, Types.TIMESTAMP };
		int n = this.getJdbcTemplate().update(sql, args, argTypes);
		return n > 0 ? true : false;
	}

	@Override
	public UserInfo getUserInfoByOpenId(String openId) 
	{
		String sql = "select * from " + TABLE_NAME +" where open_id=?";
		Object[] args = new Object[] {openId};
		int[] argTypes = new int[] {Types.VARCHAR};
		List<UserInfo> list = this.getJdbcTemplate().query(sql, args, argTypes, ParameterizedBeanPropertyRowMapper.newInstance(UserInfo.class));
		if(list==null || list.size()<=0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public UserInfo getUserInfoByEmail(String email) 
	{
		String sql = "select * from " + TABLE_NAME +" where email=?";
		Object[] args = new Object[] {email};
		int[] argTypes = new int[] {Types.VARCHAR};
		List<UserInfo> list = this.getJdbcTemplate().query(sql, args, argTypes, ParameterizedBeanPropertyRowMapper.newInstance(UserInfo.class));
		if(list==null || list.size()<=0){
			return null;
		}
		return list.get(0);
	}

}
