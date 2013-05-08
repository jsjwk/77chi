package com.bb.neighbor.common;

/**
 * 状态常量类
 * 
 * @author ly.jiao
 * 
 */
public class StatusConstants {

	/* 图片已经下载 | 图片未下载 */
	public static final int IMG_SAVED = 1;
	public static final int IMG_UNSAVED = 0;

	/* 完成 | 未完成 */
	public static final int COMPLETE = 1;
	public static final int UNCOMPLETE = 0;

	/* 未审核 | 审核通过 | 审核不通过 */
	public static final int UNCHECKED = 0;
	public static final int CHECKED_PASS = 1;
	public static final int CHECKED_FAILED = -1;

	/* 在线 | 离线 */
	public static final int ONLINE = 1;
	public static final int OFFLINE = 0;

	/* 最新记录 | 老记录 */
	public static final int NEW_RECORD = 1;
	public static final int OLD_RECORD = 0;

	/**
	 * 消息：是否已读。1：已读；0：未读
	 */
	public static final int MESSAGE_READ = 1;
	public static final int MESSAGE_UNREAD = 0;
	/**
	 * 是否已处理。1：已处理；0：未处理
	 */
	public static final int MESSAGE_HANDLED = 1;
	public static final int MESSAGE_UNHANDLED = 0;

	/**
	 * 意向：是否抽烟：1：是；0：否
	 */
	public static final int SMOKING = 1;
	public static final int NO_SMOKING = 0;
	/**
	 * 意向：是否养宠物：1：是；0：否
	 */
	public static final int KEEPING_PETS = 1;
	public static final int NO_KEEPING_PETS = 0;
	/**
	 * 部落：审核状态。1：审核通过；0：审核不通过；2：待审核
	 */
	public static final int TRIBE_VERIFY_PASS = 1;
	public static final int TRIBE_VERIFY_NO_PASS = 0;
	public static final int TRIBE_VERIFY_TODO = 2;
	/**
	 * 部落：成员加入方式。1：任何人都可以加入；2：需要申请才能加入
	 */
	public static final int TRIBE_JOIN_ANYBODY = 1;
	public static final int TRIBE_JOIN_APPLY = 2;
	/**
	 * 部落：是否创建人。1：是；0：否
	 */
	public static final int TRIBE_IS_CREATER = 1;
	public static final int TRIBE_NOT_CREATER = 0;
	/**
	 * 部落：是否管理员。1：是；0：否
	 */
	public static final int TRIBE_IS_MANAGER = 1;
	public static final int TRIBE_NOT_MANAGER = 0;
	/**
	 * 部落：是否屏蔽。1：是；0：否
	 */
	public static final int TRIBE_IS_SCREEN = 1;
	public static final int TRIBE_NOT_SCREEN = 0;

	/**
	 * 房屋关注：预约状态。0：未预约；1：预约处理中；2：预约成功；3：预约被拒；4：已出租
	 */
	public static final int ATTENTION_RESERVATION_NOT = 0;
	public static final int ATTENTION_RESERVATION_ALREADY = 1;
	public static final int ATTENTION_RESERVATION_SUCCESS = 2;
	public static final int ATTENTION_RESERVATION_FAIL = 3;
	public static final int ATTENTION_RESERVATION_RENT = 4;
	/**
	 * 房屋预约处理状态：收到的预约。0：未处理；1：接受；2：拒绝
	 */
	public static final int RESERVATION_HANDLED_STATUS_NOT = 0;
	public static final int RESERVATION_HANDLED_STATUS_ACCEPT = 1;
	public static final int RESERVATION_HANDLED_STATUS_REFUSE = 2;
	/**
	 * 房屋邀请处理状态：收到的邀请。0：未处理；1：接受；2：拒绝
	 */
	public static final int INVITATION_HANDLED_STATUS_NOT = 0;
	public static final int INVITATION_HANDLED_STATUS_ACCEPT = 1;
	public static final int INVITATION_HANDLED_STATUS_REFUSE = 2;
	/**
	 * 用户帐号，在独立站点是否激活。1：已激活；0：未激活
	 */
	public static final int USER_ACCOUNT_ACTIVATE_NOT = 0;
	public static final int USER_ACCOUNT_ACTIVATE_YES = 1;
	/**
	 * 房屋出租状态。0：未出租；1：已出租
	 */
	public static final int HOUSE_RENT_STATUS_NOT = 0;
	public static final int HOUSE_RENT_STATUS_YES = 1;
	/**
	 * 短信处理预约类型。0：接受；1：拒绝；2：已出租；3：不再通知
	 */
	public static final String RESERVATION_SMS_ACCEPT = "0";
	public static final String RESERVATION_SMS_REFUSE = "1";
	public static final String RESERVATION_SMS_HAS_BEEN_LEASED = "2";
	public static final String RESERVATION_SMS_WITHOUT_NOTICE = "3";
	/**
	 * 房屋是否接收预约短信。0：不接收；1：接收（默认接收）
	 */
	public static final int HOUSE_RESERVATION_SMS_NOT = 0;
	public static final int HOUSE_RESERVATION_SMS_YES = 1;
	/**
	 * 图片转移任务类型。1：房源图片；2：部落图片
	 */
	public static final int TASK_IMAGE_TYPE_HOUSE = 1;
	public static final int TASK_IMAGE_TYPE_TRIBE = 2;
	/**
	 * 图片转移任务状态。0：未开始；1：已完成
	 */
	public static final int TASK_IMAGE_STATUS_UNPLAYED = 0;
	public static final int TASK_IMAGE_STATUS_COMPLETED = 1;

	/**
	 * 举报状态: 1,被举报; 0,未被举报.
	 */
	public static final int ACCUSATION_NO = 0;
	public static final int ACCUSATION_YES = 1;
	/**
	 * 删除状态: 1,被删除; 0,未被刪除.
	 */
	public static final int DELETE_NO = 0;
	public static final int DELETE_YES = 1;
	/**
	 * 屏蔽状态: 1,被屏蔽; 0,未被屏蔽.
	 */
	public static final int SCREEN_NO = 0;
	public static final int SCREEN_YES = 1;
	/**
	 * 置顶状态: 1,被置顶; 0,未被置顶.
	 */
	public static final int TOP_NO = 0;
	public static final int TOP_YES = 1;
	/**
	 * 有效状态: 1,被置顶; 0,未被置顶.
	 */
	public static final int EFFECTIVE_NO = 1;
	public static final int EFFECTIVE_YES = 0;
}
