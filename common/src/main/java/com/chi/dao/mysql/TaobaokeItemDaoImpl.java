package com.chi.dao.mysql;

import java.sql.Types;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.chi.dao.SpringJDBCDaoSupport;
import com.chi.dao.TaobaokeItemDao;
import com.chi.po.TaobaokeItemVo;
import com.taobao.api.domain.TaobaokeItem;

//@Repository(value = "taobaokeItemDao")
public class TaobaokeItemDaoImpl extends SpringJDBCDaoSupport implements TaobaokeItemDao {
	// 可以在这里重载一个setDataSource

	@Override
	public boolean insertTaobaokeItemVo(TaobaokeItemVo taobaokeItemVo) 
	{
		TaobaokeItem taobaokeItem = taobaokeItemVo.getTaobaokeItem();
		
		String sql = "INSERT IGNORE  INTO "
				+ "taobaoke_item(num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume,cid,overseas_item,create_time,update_time) " 
				+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] args = new Object[] { taobaokeItem.getNumIid(), StringEscapeUtils.escapeSql(taobaokeItem.getTitle()),
				taobaokeItem.getNick(), taobaokeItem.getPicUrl(), taobaokeItem.getPrice(), taobaokeItem.getClickUrl(), 
				taobaokeItem.getCommission(),taobaokeItem.getCommissionRate(),taobaokeItem.getCommissionNum(),taobaokeItem.getCommissionVolume(),
				taobaokeItem.getShopClickUrl(),taobaokeItem.getSellerCreditScore(),taobaokeItem.getItemLocation(),taobaokeItem.getVolume(),
				taobaokeItemVo.getCid(),taobaokeItemVo.getOverseasItem(),taobaokeItemVo.getCreateTime(),taobaokeItemVo.getUpdateTime()};
		int[] argTypes = new int[] { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.VARCHAR,Types.BIGINT,Types.BIGINT,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP };
		int n = this.getJdbcTemplate().update(sql, args,argTypes);
		return n > 0 ? true : false;
	}

	@Override
	public List<TaobaokeItemVo> findAllItems()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	/*
	 * 
	 * 
	@Override
	public boolean batchInsertTaobaokeItemDetailVo(final List<TaobaokeItemDetailVo> listTaobaokeItemDetailVo) 
	{
		String sql = "INSERT INTO taobaoke_item_detail(num_iid,cid,detail_url,title,type,item_desc,props_name,created,auction_point,volume,is_xinpin,food_security,locality_life,item_weight,item_size,num,valid_thru,location,list_time,delist_time,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,modified,approve_status,item_imgs,score,is_taobao,violation,create_time,update_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE update_time=now()";
		int[] resultArr = this.getJdbcTemplate().batchUpdate(sql,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						TaobaokeItemDetailVo detailVo = listTaobaokeItemDetailVo.get(i);
						TaobaokeItemDetail detail = detailVo.getTaobaokeItemDetail();
						Item item = detail.getItem();
						
						ps.setLong(1, item.getNumIid());
						ps.setLong(2, item.getCid());
						ps.setString(3, item.getDetailUrl());
						ps.setString(4, item.getTitle());
						ps.setString(5, item.getType());
						ps.setString(6, item.getDesc());
						ps.setString(7, item.getPropsName());
						Timestamp created = item.getCreated()==null?null:new Timestamp(item.getCreated().getTime());
						ps.setTimestamp(8, created);
						ps.setLong(9, item.getAuctionPoint());
						ps.setLong(10, item.getVolume()==null?0L:item.getVolume());
						ps.setBoolean(11, item.getIsXinpin()==null?false:item.getIsXinpin());
						//TODO FoodSecurity Object
						ps.setString(12, item.getFoodSecurity()==null?"":item.getFoodSecurity().toString());
						//TODO LocalityLife Object
						ps.setString(13, item.getLocalityLife()==null?"":item.getLocalityLife().toString());
						ps.setString(14, item.getItemWeight());
						ps.setString(15, item.getItemSize());
						ps.setLong(16, item.getNum());
						ps.setLong(17, item.getValidThru());
						//TODO Location Object
						ps.setString(18, item.getLocation().getAddress());
						ps.setTimestamp(19, new Timestamp(item.getListTime().getTime()));
						ps.setTimestamp(20, new Timestamp(item.getDelistTime().getTime()));
						ps.setString(21, item.getPrice());
						ps.setString(22, item.getPostFee());
						ps.setString(23, item.getExpressFee());
						ps.setString(24, item.getEmsFee());
						ps.setBoolean(25, item.getHasDiscount());
						ps.setString(26, item.getFreightPayer());
						ps.setTimestamp(27, new Timestamp(item.getModified().getTime()));
						ps.setString(28, item.getApproveStatus());
						//TODO List<ItemImg>
						List<ItemImg> listItemImg = item.getItemImgs();
						ps.setString(29, listItemImg==null?"":listItemImg.toString());
						ps.setLong(30, item.getScore()==null?0L:item.getScore());
						ps.setBoolean(31, item.getIsTaobao()==null?false:item.getIsTaobao());
						ps.setBoolean(32, item.getViolation()==null?false:item.getViolation());
						ps.setTimestamp(33, new Timestamp(detailVo.getCreateTime().getTime()));
						ps.setTimestamp(34, new Timestamp(detailVo.getUpdateTime().getTime()));
					}

					@Override
					public int getBatchSize() {
						return listTaobaokeItemDetailVo.size();
					}

				});

		int num = 0;
		for (int n : resultArr) {
			num += n;
		}
		return num>0;
	}
	 */
	
}
