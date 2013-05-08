package com.bb.neighbor.user.model;

public class WorkHistory {

	/**
	 * 入职时间
	 */
	private Long startDate;
	
	/**
	 * 离职时间
	 */
	private Long endDate;
	
	/**
	 * 工作描述
	 */
	private String description;
	
	/**
	 * 所在公司
	 */
	private String companyName;

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
