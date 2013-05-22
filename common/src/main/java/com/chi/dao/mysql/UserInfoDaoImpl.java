package com.chi.dao.mysql;

import java.sql.Types;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.chi.po.UserInfo;

@Repository(value = "userInfoDao")
public class UserInfoDaoImpl extends SpringJDBCDaoSupport implements UserInfoDao {

	private static String TABLE_NAME = "user_info";
	
	@Override
	public boolean saveOrUpdateUserInfo(UserInfo userInfo) 
	{
		String sql = "INSERT INTO "	+ TABLE_NAME + "(user_id,email,password,nick_name,account_type,source_type,access_token,open_id,gender,birthday,create_time,update_time)  "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";
		//TODO
		//  ON DUPLICATE KEY UPDATE
		Object[] args = new Object[] { userInfo.getUserId(), StringEscapeUtils.escapeSql(userInfo.getEmail()),userInfo.getPassword(), StringEscapeUtils.escapeSql(userInfo.getNickName()),
				userInfo.getAccountType(),userInfo.getSourceType(),userInfo.getAccessToken(),userInfo.getOpenId(),userInfo.getGender(),userInfo.getBirthday(), userInfo.getCreateTime(), userInfo.getUpdateTime() };
		int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR,	Types.INTEGER, Types.VARCHAR,Types.TIMESTAMP, Types.TIMESTAMP };
		int n = this.getJdbcTemplate().update(sql, args, argTypes);
		return n > 0 ? true : false;
	}

}
