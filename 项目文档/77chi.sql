/*
MySQL Data Transfer
Source Host: 10.10.126.58
Source Database: 77chi
Target Host: 10.10.126.58
Target Database: 77chi
Date: 2013/5/17 18:10:08
*/

CREATE DATABASE `77chi` DEFAULT CHARACTER SET utf8;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for taobaoke_item
-- ----------------------------
DROP TABLE IF EXISTS `taobaoke_item`;
CREATE TABLE `taobaoke_item` (
  `num_iid` bigint(20) NOT NULL default '0' COMMENT '商品id',
  `title` varchar(255) default NULL COMMENT '标题',
  `nick` varchar(255) default NULL COMMENT '用户昵称',
  `pic_url` text COMMENT '图片地址',
  `price` varchar(255) default NULL COMMENT '价格',
  `click_url` text COMMENT '点击URL',
  `commission` varchar(255) default NULL COMMENT '佣金',
  `commission_rate` varchar(255) default NULL COMMENT '佣金比例',
  `commission_num` varchar(255) default NULL COMMENT '推广量',
  `commission_volume` varchar(255) default NULL,
  `shop_click_url` text COMMENT '店铺URL',
  `seller_credit_score` bigint(20) default NULL COMMENT '卖家信誉度',
  `item_location` varchar(255) default NULL COMMENT '商品地区',
  `volume` bigint(20) default NULL,
  `cid` bigint(20) default NULL,
  `overseas_item` varchar(255) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`num_iid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for taobaoke_item_detail
-- ----------------------------
DROP TABLE IF EXISTS `taobaoke_item_detail`;
CREATE TABLE `taobaoke_item_detail` (
  `num_iid` bigint(20) NOT NULL default '0' COMMENT '商品数字id',
  `cid` bigint(20) default NULL,
  `detail_url` varchar(255) default NULL COMMENT '商品url',
  `title` varchar(255) default NULL COMMENT '商品标题,不能超过60字节',
  `type` varchar(255) default NULL COMMENT '商品类型(fixed:一口价;auction:拍卖)注：取消团购',
  `item_desc` mediumtext COMMENT '商品描述, 字数要大于5个字符，小于25000个字符',
  `props_name` varchar(255) default NULL COMMENT '商品属性名称。',
  `created` datetime default NULL COMMENT 'Item的发布时间',
  `auction_point` bigint(20) default NULL COMMENT '天猫订单抽佣比例，为5的倍数，最低0.5%。跟淘客佣金没有关系',
  `volume` bigint(20) default NULL COMMENT '对应搜索商品列表页的最近成交量',
  `is_xinpin` tinyint(1) default NULL COMMENT '标示商品是否为新品。值含义：true-是，false-否。',
  `food_security` text COMMENT '食品安全信息，包括：生产许可证编号、产品标准号、厂名、厂址等',
  `locality_life` text COMMENT '本地生活电子交易凭证业务，目前此字段只涉及到的信息为有效期',
  `item_weight` varchar(255) default NULL COMMENT '商品的重量，用于按重量计费的运费模板。注意：单位为kg',
  `item_size` varchar(255) default NULL COMMENT '表示商品的体积，用于按体积计费的运费模板',
  `num` bigint(20) default NULL COMMENT '商品数量',
  `valid_thru` bigint(20) default NULL COMMENT '有效期,7或者14（默认是14天）',
  `location` varchar(255) default NULL COMMENT '商品所在地',
  `list_time` datetime default NULL COMMENT '上架时间',
  `delist_time` datetime default NULL COMMENT '下架时间',
  `price` varchar(255) default NULL COMMENT '商品价格，格式：5.00；单位：元；精确到：分',
  `post_fee` varchar(255) default NULL COMMENT '平邮费用',
  `express_fee` varchar(255) default NULL COMMENT '快递费用',
  `ems_fee` varchar(255) default NULL COMMENT 'ems费用',
  `has_discount` tinyint(255) default NULL COMMENT '支持会员打折,true/false',
  `freight_payer` varchar(255) default NULL COMMENT '运费承担方式,seller（卖家承担），buyer(买家承担）',
  `modified` datetime default NULL COMMENT '商品修改时间',
  `approve_status` varchar(255) default NULL COMMENT '商品上传后的状态。onsale出售中，instock库中',
  `item_imgs` varchar(255) default NULL COMMENT '商品图片列表(包括主图)。fields中只设置item_img可以返回ItemImg结构体中所有字段，如果设置为item_img.id、item_img.url、item_img.position等形式就只会返回相应的字段',
  `score` bigint(255) default NULL COMMENT '商品所属卖家的信用等级数',
  `is_taobao` tinyint(255) default NULL COMMENT '是否在淘宝显示',
  `violation` tinyint(255) default NULL COMMENT '商品是否违规，违规：true , 不违规：false',
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`num_iid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL auto_increment COMMENT '用户Id',
  `email` varchar(255) default NULL COMMENT '用户名',
  `password` varchar(255) default NULL COMMENT '密码',
  `nick_name` varchar(255) default NULL COMMENT '邮件地址',
  `account_type` int(255) default NULL COMMENT '来源',
  `source_type` int(255) default NULL COMMENT '来源的账号',
  `access_token` varchar(255) default NULL,
  `open_id` varchar(255) default NULL,
  `gender` int(11) default NULL,
  `birthday` varchar(255) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_name_index` (`email`),
  UNIQUE KEY `email_index` (`nick_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
