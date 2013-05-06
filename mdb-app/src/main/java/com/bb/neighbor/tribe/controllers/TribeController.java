package com.bb.neighbor.tribe.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.paoding.rose.web.InvocationLocal;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.bb.neighbor.common.Constants;
import com.bb.neighbor.common.GB2Alpha;
import com.bb.neighbor.common.Page;
import com.bb.neighbor.common.UploadUtil;
import com.bb.neighbor.common.constant.CrawlerConstants;
import com.bb.neighbor.tribe.compare.ComparatorTribe;
import com.bb.neighbor.tribe.home.TribeHome;
import com.bb.neighbor.tribe.home.TribeJoinRequestHome;
import com.bb.neighbor.tribe.home.TribeManageLogHome;
import com.bb.neighbor.tribe.home.TribeMemberHome;
import com.bb.neighbor.tribe.home.TribeTopicHome;
import com.bb.neighbor.tribe.model.Tribe;
import com.bb.neighbor.tribe.model.TribeJoinRequest;
import com.bb.neighbor.tribe.model.TribeManageLog;
import com.bb.neighbor.tribe.model.TribeMember;
import com.bb.neighbor.tribe.model.TribeTopic;
import com.bb.neighbor.tribe.utils.CompareUtils;
import com.bb.neighbor.tribe.utils.ManageLogConstants;
import com.bb.neighbor.user.home.UserHome;
import com.bb.neighbor.user.model.UserInfo;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

/**
 * 部落控制器
 * 
 * @author song.zhang@boco.jp
 * 
 */
@Path("/tribe")
public class TribeController {
	@Autowired
	private InvocationLocal inv;
	@Autowired
	private UserHome userHome;
	@Autowired
	private TribeHome tribeHome;
	@Autowired
	private TribeTopicHome tribeTopicHome;
	@Autowired
	private TribeMemberHome tribeMemberHome;
	@Autowired
	private TribeManageLogHome tribeManageLogHome;
	@Autowired
	private TribeJoinRequestHome joinRequestHome;

	/**
	 * 进入部落首页
	 * 
	 * @return
	 */
	@Get("/home")
	public String tribeHome() {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		// 我加入的部落
		List<Tribe> myTribeList = tribeHome.getJoinTribeList(userInfo.getId());
		// 热门部落
		List<Tribe> hotTribeList = tribeHome.getHotTribeList(Constants.PAGE_SIZE_TRIBE_HOT);
		// 我创建的部落
		List<TribeMember> createTribeList = tribeMemberHome.getCreateTribeIds(userInfo.getId());
		if (createTribeList.size() > 0) {
			StringBuffer ids = new StringBuffer();
			for (TribeMember bean : createTribeList) {
				ids.append(bean.getTribeId()).append(",");
			}
			ids.deleteCharAt(ids.lastIndexOf(","));
			inv.addModel("createTribeIds", ids);
		}
		// 得到最新创建的6个部落
		List<Tribe> tribes = tribeHome.getNewCreateTribes();
		// 热门话题
		List<TribeTopic> hotTopicList = tribeTopicHome.getHotTopicList(5);
		for (TribeTopic bean : hotTopicList) {
			bean.setTribe(tribeHome.getTribeById(bean.getTribeId()));
		}
		List<TribeTopic> myTopicList = tribeTopicHome.getHotTopicList(userInfo.getId(), 5);
		for (TribeTopic bean : myTopicList) {
			bean.setTribe(tribeHome.getTribeById(bean.getTribeId()));
		}
		inv.addModel("tribes", tribes);
		inv.addModel("myTribeList", myTribeList);
		inv.addModel("hotTribeList", hotTribeList);
		inv.addModel("hotTopicList", hotTopicList);
		inv.addModel("myTopicList", myTopicList);
		return "tribe-home";
	}

	/**
	 * 部落明细
	 * 
	 * @param id
	 * @return
	 */
	@Get("/detail/{id}")
	public String tribeDetail(@Param("id") Integer id) {
		Tribe tribe = tribeHome.getTribeById(id);
		int limit = 4;
		Integer type = inv.getParameter("type") != null ? Integer.parseInt(inv.getParameter("type")) : null;
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = tribeTopicHome.countGetTribeTopicByList(id, type);
		List<TribeTopic> tribeTopicList = tribeTopicHome.getTribeTopicByList(id, pageIndex, limit, type);
		// 按照置顶排序
		// ComparatorTribeTopicTop comparator = new ComparatorTribeTopicTop();
		// Collections.sort(tribeTopicList, comparator);
		// 填充用户信息
		for (TribeTopic tribeTopic : tribeTopicList) {
			UserInfo userInfo = userHome.getUserInfoByUserId(tribeTopic.getReleaserId());
			tribeTopic.setReleaser(userInfo);
		}
		Page<TribeTopic> page = new Page<TribeTopic>(total, limit, pageIndex, tribeTopicList);

		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		Integer isMemberTag = tribeMemberHome.whetherTribeMemberByUserId(userInfo.getId(), id) ? 1 : 0;

		inv.addModel("page", page);
		inv.addModel("tribe", tribe);
		inv.addModel("isMemberTag", isMemberTag);
		return "tribe-detail";
	}

	/**
	 * 搜索部落
	 * 
	 * @return
	 */
	@Post("/searchTribe/${tribeName}")
	public String searchTribe(@Param("tribeName") String tribeName) {
		List<Tribe> tribes = tribeHome.searchByName(tribeName);
		inv.addModel("tribes", tribes);
		return "tribe-list";
	}

	/**
	 * 查看所有部落
	 * 
	 * @return
	 */
	@Get("/listTribes")
	public String listTribes() {
		List<Tribe> tribes = tribeHome.getAllTribes();
		// 得到拼音字母
		GB2Alpha alpha = GB2Alpha.getInstance();
		for (Tribe t : tribes) {
			t.setLetters(alpha.string2Alpha(t.getName()));
		}
		// 按照拼音字母排序
		ComparatorTribe comparator = new ComparatorTribe();
		Collections.sort(tribes, comparator);
		inv.addModel("tribes", tribes);
		return "tribe-list";
	}

	@Get("/applyCreate")
	public String applyCreate() {
		return "tribe-applyCreate";
	}

	/**
	 * 保存部落
	 * 
	 * @param tribe
	 * @return
	 */
	@Post("/saveTribe")
	public String saveTribe(Tribe tribe) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		String[] labels = inv.getRequest().getParameterValues("labels");
		if (labels != null && labels.length > 0) {
			tribe.setLabel(Arrays.asList(labels));
		}
		// 创建部落个数
		int total = tribeMemberHome.countCreateTribeNum(userInfo.getId());
		if (total >= 3) {
			return "@overLimit";
		}
		tribe = tribeHome.createTribe(tribe);
		// 添加成员关系
		tribeMemberHome.save(userInfo.getId(), tribe.getId());
		// 返回部落明细页面
		return "@/tribe/detail/" + tribe.getId();
	}

	/**
	 * 更新部落
	 * 
	 * @param tribe
	 * @return
	 */
	@Post("/updateTribe")
	public String updateTribe(Tribe tribe) {
		String[] labels = inv.getRequest().getParameterValues("labels");
		int tribeId = tribe.getId();
		if (labels != null && labels.length > 0) {
			tribe.setLabel(Arrays.asList(labels));
		}
		// 更新前的部落
		Tribe oldTribe = tribeHome.getTribeById(tribeId);
		UpdateOperations<Tribe> ops = tribeHome.getUpdateOps();
		Query<Tribe> query = tribeHome.getQuery().filter("id", tribeId);
		updateManageLog(tribe, oldTribe, ops);
		tribeHome.updateTribe(query, ops);
		return "r:/tribe/admin/manageTribe/" + tribeId;
	}

	private void updateManageLog(Tribe tribe, Tribe oldTribe, UpdateOperations<Tribe> ops) {
		TribeManageLog managelog = new TribeManageLog();
		managelog.setTribeId(tribe.getId());
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		managelog.setOperatorName(userInfo.getUserName());
		StringBuffer modifyStr = new StringBuffer();
		modifyStr.append(ManageLogConstants.OPT_MODIFY);
		if (!tribe.getDescription().equals(oldTribe.getDescription())) {
			modifyStr.append("部落描述").append(",");
			ops.set("description", tribe.getDescription());
		}
		if (!tribe.getJoinType().equals(oldTribe.getJoinType())) {
			modifyStr.append("部落加入方式").append(",");
			ops.set("joinType", tribe.getJoinType());
		}
		if (!CompareUtils.compare(tribe.getLabel(), oldTribe.getLabel())) {
			modifyStr.append("部落标签").append(",");
			ops.set("label", tribe.getLabel());
		}
		if (modifyStr.lastIndexOf(",") > 0) {
			modifyStr.deleteCharAt(modifyStr.lastIndexOf(","));
		}
		managelog.setModifyContent(modifyStr.toString());
		tribeManageLogHome.createLog(managelog);
	}

	/**
	 * 上传房间图片
	 * 
	 * @param files
	 * @return
	 */
	// @SuppressWarnings("unchecked")
	@Post("/tribeImg/upload")
	public String tribeImgUpload(MultipartFile file) {
		// HouseImg houseImg = new HouseImg();
		String fileName = file.getOriginalFilename();
		// String name = fileName.substring(0, fileName.lastIndexOf("."));
		// String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String saveFilePath = CrawlerConstants.IMG_PATH + CrawlerConstants.IMG_TEMP_PATH + "/tribe/111/";
		String saveDBPath = CrawlerConstants.IMG_TEMP_PATH + "/tribe/111/";
		try {
			UploadUtil.saveFile(file, saveFilePath, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "@" + saveDBPath + fileName;
	}

	/**
	 * 查看我创建的部落
	 * 
	 * @return
	 */
	@Get("/admin/listMyTribes")
	public String listMyTribes() {
		String tribeIds = inv.getParameter("tribeIds");
		String[] tribeIdArray;
		List<Integer> tribeIdList = new ArrayList<Integer>();
		if (tribeIds.contains(",")) {
			tribeIdArray = tribeIds.split(",");
			for (int i = 0; i < tribeIdArray.length; i++) {
				tribeIdList.add(Integer.parseInt(tribeIdArray[i]));
			}
		} else {
			tribeIdList.add(Integer.parseInt(tribeIds));
		}
		List<Tribe> tribes = tribeHome.getTribeByIds(tribeIdList);
		inv.addModel("tribes", tribes);
		return "tribe-list-manage";
	}

	/**
	 * 管理基本信息
	 * 
	 * @param id
	 * @return
	 */
	@Get("/admin/manageTribe/{id}")
	public String manageBaseInfo(@Param("id") Integer id) {
		Tribe tribe = tribeHome.getTribeById(id);
		inv.addModel("tribe", tribe);
		return "tribe-manageBaseInfo";
	}

	/**
	 * 管理话题
	 * 
	 * @param id
	 * @return
	 */
	@Get("/admin/manageTopic/{id}")
	public String manageTopic(@Param("id") Integer id) {
		int limit = 4;
		Integer type = inv.getParameter("type") != null ? Integer.parseInt(inv.getParameter("type")) : null;
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		int total = tribeTopicHome.countGetTribeTopicByList(id, type);
		List<TribeTopic> tribeTopicList = tribeTopicHome.getTribeTopicByList(id, pageIndex, limit, type);
		// 填充用户信息
		for (TribeTopic tribeTopic : tribeTopicList) {
			UserInfo userInfo = userHome.getUserInfoByUserId(tribeTopic.getReleaserId());
			tribeTopic.setReleaser(userInfo);
		}
		Page<TribeTopic> page = new Page<TribeTopic>(total, limit, pageIndex, tribeTopicList);
		inv.addModel("page", page);
		inv.addModel("tribeId", id);
		return "tribe-list-manageTopic";
	}

	/**
	 * 管理日志
	 * 
	 * @param id
	 * @return
	 */
	@Get("/admin/manageTribeLog/{id}")
	public String manageTribeLog(@Param("id") Integer id) {
		List<TribeManageLog> tribeManageLogs = tribeManageLogHome.getLogs(id);
		inv.addModel("tribeManageLogs", tribeManageLogs);
		inv.addModel("tribeId", id);
		return "tribe-list-manageLog";
	}

	/**
	 * 退出部落
	 * 
	 * @param id
	 * @return
	 */
	@Post("/admin/quitTribe/{id}")
	public String quitTribe(@Param("id") Integer id) {
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		boolean isCreater = tribeMemberHome.isCreater(userInfo.getId(), id);
		if (isCreater) {
			return "@isCreater";
		}
		tribeMemberHome.drop(userInfo.getId(), id);
		return "@quit";
	}

	/**
	 * 申请加入部落
	 * 
	 * @param id
	 * @return
	 */
	@Post("/admin/apply/{id}")
	public String apply(@Param("id") Integer id) {
		TribeJoinRequest bean = new TribeJoinRequest();
		bean.setTribeId(id);
		UserInfo userInfo = (UserInfo) inv.getRequest().getSession().getAttribute(Constants.SESSION_USER_KEY);
		bean.setUserId(userInfo.getId());
		joinRequestHome.saveTribeJoinRequest(bean);
		return "@ok";
	}

	@Get("/admin/membermanage/{tribeId}")
	public String membermanage(@Param("tribeId") Integer tribeId) {
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		// 普通成员个数
		int total = tribeMemberHome.countNumberById(tribeId);
		// 普通成员
		List<TribeMember> memberlist = tribeMemberHome.getMembersById(tribeId, pageIndex, 6);
		for (TribeMember bean : memberlist) {
			// 填充用户信息
			bean.setUserInfo(userHome.getUserInfoByUserId(bean.getMember()));
		}
		Page<TribeMember> page = new Page<TribeMember>(total, 6, pageIndex, memberlist);
		inv.addModel("page", page);
		// 管理人员
		List<TribeMember> managers = tribeMemberHome.getManagersById(tribeId);
		for (Iterator<TribeMember> iterator = managers.iterator(); iterator.hasNext();) {
			TribeMember bean = iterator.next();
			// 填充用户信息
			bean.setUserInfo(userHome.getUserInfoByUserId(bean.getMember()));
			if (bean.getRole() == Constants.TRIBE_ROLE_ADMIN) {
				// 管理员
				inv.addModel("admin", bean);
				// 去掉管理员
				iterator.remove();
			}
		}
		inv.addModel("managers", managers);
		inv.addModel("tribeId", tribeId);
		return "tribe-manageMember";
	}

	/**
	 * 搜索成员
	 * 
	 * @param name
	 * @return
	 */
	@Get("/admin/searchMember/{tribeId}/{name}")
	public String searchMember(@Param("tribeId") int tribeId, @Param("name") String name) {
		int pageIndex = inv.getParameter("page") != null ? Integer.parseInt(inv.getParameter("page")) : 1;
		// 得到userIdList
		List<UserInfo> userList = userHome.findByName(name);
		if (userList.size() > 0) {
			List<Integer> userIds = new ArrayList<Integer>();
			for (UserInfo bean : userList) {
				userIds.add(bean.getId());
			}
			int total = tribeMemberHome.countNumberByUserIds(tribeId, userIds);
			List<TribeMember> memberlist = tribeMemberHome.getMembersByUserIds(tribeId, userIds, pageIndex, 12);
			for (TribeMember bean : memberlist) {
				// 填充用户信息
				bean.setUserInfo(userHome.getUserInfoByUserId(bean.getMember()));
			}
			Page<TribeMember> page = new Page<TribeMember>(total, 12, pageIndex, memberlist);
			inv.addModel("page", page);
		}
		inv.addModel("tribeId", tribeId);
		return "tribe-search-member";
	}
}
