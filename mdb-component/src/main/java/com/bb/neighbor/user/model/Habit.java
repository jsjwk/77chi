package com.bb.neighbor.user.model;

/**
 * 个人习惯,爱好
 * 
 * @author ly.jiao@live.cn
 * 
 */
public class Habit {
	/**
	 * 抽烟否: 0,不抽烟;1,抽烟
	 */
	private int smoke;
	/**
	 * 养宠物否: 0,不养宠物;1,养宠物
	 */
	private int havePet;

	public int getSmoke() {
		return smoke;
	}

	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}

	public int getHavePet() {
		return havePet;
	}

	public void setHavePet(int havePet) {
		this.havePet = havePet;
	}

}
