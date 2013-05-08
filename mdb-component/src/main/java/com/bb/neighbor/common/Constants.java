package com.bb.neighbor.common;

public class Constants {
	/**
	 * 系统管理员ID
	 */
	public static final Integer SYSTEM_MANAGER_ID = 1;
	/**
	 * 系统管理员用户名
	 */
	public static final String SYSTEM_MANAGER_USERNAME = "系统管理员";
	/**
	 * 北京市编码
	 */
	public static final String BEIJING_CODE = "010000";
	public static final String BEIJING = "北京";
	/**
	 * session 存储userInfo的key
	 */
	public static final String SESSION_USER_KEY = "sessionUser";
	/**
	 * 房源来源
	 */
	public static final String HOUSE_INFO_FROM_BANGNIZU = "bangnizu";
	/**
	 * 日期时间格式化参数
	 */
	public static final String PARAM_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式化参数
	 */
	public static final String PARAM_DATE = "yyyy-MM-dd";
	/**
	 * 日期格式化参数
	 */
	public static final String PARAM_YEAR_MONTH = "yyyy-MM";
	/**
	 * 日期格式化参数
	 */
	public static final String PARAM_YEAR = "yyyy";
	/**
	 * 检索类型：查房间
	 */
	public static final int SEARCH_HOUSE = 1;
	/**
	 * 检索类型：查用户
	 */
	public static final int SEARCH_USER = 2;
	/**
	 * 检索：每页显示数据条数
	 */
	public static final int PAGE_SIZE_SEARCH = 4;
	/**
	 * 意向区域ajax搜索，显示记录数
	 */
	public static final int PAGE_SIZE_PURPOSE = 10;
	/**
	 * 系统消息：每页显示数据条数
	 */
	public static final int PAGE_SIZE_SYSTEM_MESSAGE = 10;
	/**
	 * 收件箱：每页显示数据条数
	 */
	public static final int PAGE_SIZE_INBOX = 10;
	/**
	 * 发件箱：每页显示数据条数
	 */
	public static final int PAGE_SIZE_OUTBOX = 10;
	/**
	 * 找人意向：根据意向，获取用户列表，每页显示多少用户
	 */
	public static final int PAGE_SIZE_PURPOSE_USER = 7;
	/**
	 * 找房意向：根据意向，获取房屋信息，每页显示多少房间
	 */
	public static final int PAGE_SIZE_PURPOSE_HOUSE = 4;
	/**
	 * 部落：首页获取热门部落数量
	 */
	public static final int PAGE_SIZE_TRIBE_HOT = 4;
	/**
	 * 部落：部落详细页，话题每页显示数据条数
	 */
	public static final int PAGE_SIZE_TRIBE_TOPIC = 10;
	/**
	 * 部落：是否创建人: 1,是; 0,不是.
	 */
	public static final int ISCREATER = 1;
	public static final int ISNOTCREATER = 0;
	/**
	 * 部落：是否屏蔽: 1,是; 0,不是.
	 */
	public static final int ISSCREEN = 1;
	public static final int ISNOTSCREEN = 0;
	/**
	 * 部落：成员角色: 0,普通成员;1,管理员;2,副管理员
	 */
	public static final int TRIBE_ROLE_MEMBER = 0;
	public static final int TRIBE_ROLE_ADMIN = 1;
	public static final int TRIBE_ROLE_MANAGER = 2;
	/**
	 * 房屋详情页，显示感兴趣人数
	 */
	public static final int PAGE_SIZE_HOUSE_ATTENTION = 8;
	/**
	 * 房屋详情页，最多显示关注人数
	 */
	public static final int PAGE_SIZE_HOUSE_ATTENTION_MAX = 50;
	/**
	 * 收到的预约，每页显示数据条数
	 */
	public static final int PAGE_SIZE_RESERVATION = 10;
	/**
	 * 意向类型。1：找房；2：找人
	 */
	public static final int PURPOSE_TYPE_HOUSE = 1;
	public static final int PURPOSE_TYPE_USER = 2;
	/**
	 * 地点类型：1：小区；2：区域
	 */
	public static final int WHERE_TYPE_COMMUNITY = 1;
	public static final int WHERE_TYPE_AREA = 2;
	/**
	 * 职业：1:普通职业；2：学生；3：自由职业；4：其他
	 */
	public static final int PROFESSION_ORDINARY = 1;
	public static final int PROFESSION_STUDENT = 2;
	public static final int PROFESSION_FREE = 3;
	public static final int PROFESSION_OTHER = 4;
	/**
	 * 性别：1：男；0：女；2：不限
	 */
	public static final int SEX_MAN = 1;
	public static final int SEX_WOMAN = 0;
	public static final int SEX_UNLIMITED = 2;
	/**
	 * 房间：1：主卧带卫生间；2：主卧不带卫生间；3：正规次卧；4：隔断次卧；5：床位
	 */
	public static final int HOUSE_TYPE_BEDROOM_TOILET = 1;
	public static final int HOUSE_TYPE_BEDROOM_NO_TOILET = 2;
	public static final int HOUSE_TYPE_NORMAL_SUBALTERN_ROOM = 3;
	public static final int HOUSE_TYPE_SEVERED_SUBALTERN_ROOM = 4;
	public static final int HOUSE_TYPE_BED = 5;
	/**
	 * 层级 有多少层,level从10开始,level越大,层级越低,10代表市,11代表地区,12代表商圈
	 */
	public static final int AREA_LEVEL_CITY = 10;
	public static final int AREA_LEVEL_AREA = 11;
	public static final int AREA_LEVEL_BUSINESS = 12;
	/**
	 * 部落主题类型。1：出租；2：求租
	 */
	public static final int TRIBE_TOPIC_TYPE_LET = 1;
	public static final int TRIBE_TOPIC_TYPE_MAGDEBRUG = 2;
	/**
	 * 审核状态: 1,已经审核; 0：审核不通过；2：待审核
	 */
	public static final int VERIFY_FAIL = 0;
	public static final int VERIFY_PASS = 1;
	public static final int VERIFY_WAIT = 2;
	/**
	 * 房屋租赁类型
	 */
	public static final String HIRE_TYPE_ROOMMATE = "合租";
	public static final String HIRE_TYPE_LEASING = "整租";
	/**
	 * 房间图片允许的后缀
	 */
	public static final String[] HOUSE_IMG_SUFFIX = { "jpg", "gif", "png" };
	/**
	 * 房间图片最大值
	 */
	public static final long HOUSE_IMG_MAX_SIZE = 2000000;
	public static final String HOUSE_IMG_MAX_INFO = "2M";
	/**
	 * 成员加入方式: 1,任何人都可以加入; 2,需要申请才能加入
	 */
	public static final int JOINTYPE1 = 1;
	public static final int JOINTYPE2 = 2;
	/**
	 * 返回状态: 0,失败.
	 */
	public static final int RETURN_FAIL = 0;
	public static final int RETURN_SUCCESS = 1;
	/**
	 * 排序方式: 1,升序(默认)/-1,降序
	 */
	public static final int ORDERBY_ASC = 1;
	public static final int ORDERBY_DESC = -1;
	/**
	 * 房子评价: 1,非常好; 2,好; 3,一般; 4,差; 5,很差
	 */
	public static final int HOUSE_COMMENT_VERYGOOD = 1;
	public static final int HOUSE_COMMENT_GOOD = 2;
	public static final int HOUSE_COMMENT_NORMAL = 3;
	public static final int HOUSE_COMMENT_BAD = 4;
	public static final int HOUSE_COMMENT_VERYBAD = 5;
	/**
	 * 评价状态: 0,未评价; 1,已经评价.
	 */
	public static final int HOUSE_APPRAISE_NO = 0;
	public static final int HOUSE_APPRAISE_YES = 1;
}
