package com.bb.neighbor.operation.controllers;

import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.neighbor.operation.home.BlacklistHome;
import com.bb.neighbor.operation.model.Blacklist;

/**
 * 
 * @author ly.jiao@live.cn
 * 
 */
@Path("/blacklist")
public class BlacklistController {
	// @Autowired
	// private InvocationLocal inv;
	@Autowired
	private BlacklistHome blacklistHome;

	/**
	 * 添加黑名单
	 * 
	 * @return
	 */
	@Post("/add/{fromId}/{toId}/{collectionId}/{type}")
	public int addBlacklist(@Param("fromId") int fromId, @Param("toId") int toId, @Param("collectionId") int collectionId, @Param("type") String type) {
		Blacklist blacklist = new Blacklist(fromId, toId, collectionId, type);
		return blacklistHome.save(blacklist);
	}
}
