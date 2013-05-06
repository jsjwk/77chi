package com.bb.mongo.utils;

import com.bb.mongo.dao.SequenceDAO;

public class SequenceUtil {

    /**
     * 用户集合id
     */
    public static final String USERINFO_ID_VALUE = "userInfoIdValue";
    /**
     * 地区Id
     */
    public static final String AREA_ID = "areaId";
    /**
     * 任务Id
     */
    public static final String TASK_ID = "taskId";
    /**
     * 房间ID
     */
    public static final String HOUSE_ID = "houseId";
    /**
     * 意向ID
     */
    public static final String PURPOSE_ID = "purposeId";
    /**
     * 参数Id
     */
    public static final String PARAMETER_ID = "parameterId";
    /**
     * 系统消息ID
     */
    public static final String SYSTEM_MESSAGE_ID = "systemMessageId";
    /**
     * 收件箱ID
     */
    public static final String INBOX_ID = "inboxId";
    /**
     * 发件箱ID
     */
    public static final String OUTBOX_ID = "outboxId";
    /**
     * 部落ID
     */
    public static final String TRIBE_ID = "tribeId";
    /**
     * 部落成员ID
     */
    public static final String TRIBE_MEMBER_ID = "tribeMemberId";
    /**
     * 部落话题ID
     */
    public static final String TRIBE_TOPIC_ID = "tribeTopicId";
    /**
     * 部落话题ID
     */
    public static final String TRIBE_REPLY_ID = "tribeReplyId";
    /**
     * 短信ID
     */
    public static final String SMS_ID = "smsId";
    /**
     * 房屋关注ID
     */
    public static final String HOUSE_ATTENTION_ID = "houseAttentionId";
    /**
     * 房屋评论ID
     */
    public static final String HOUSE_COMMENT_ID = "houseCommentId";
    /**
     * 房屋评价ID
     */
    public static final String HOUSE_APPRECIATION_ID = "appreciationId";
    /**
     * 部落管理日志ID
     */
    public static final String TRIBEMANAGELOG_ID = "tribeManageLogId";
    /**
     * 预约请求ID
     */
    public static final String RESERVATION_ID = "reservationId";
    /**
     * 邀请ID
     */
    public static final String INVITATION_ID = "invitationId";
    /**
     * 黑名单
     */
    public static final String BLACKLIST_ID = "blacklistId";
    /**
     * 加入部落申请Id
     */
    public static final String TRIBEJOINREQUEST_ID = "tribeJoinRequestId";
    /**
     * 图片转移任务ID
     */
    public static final String TASK_IMAGE_ID = "taskImageId";
    /**
     * 大学ID
     */
    public static final String UNIVERSITY_ID = "universityId";

    /**
     * 自动生成ID
     * 
     * @param collName
     *            集合名称
     * @return
     */
    public static int getId(String collName) {
        SequenceDAO sequenceDAO = SequenceDAO.getInstance();
        return sequenceDAO.getID(collName).intValue();
    }
}
