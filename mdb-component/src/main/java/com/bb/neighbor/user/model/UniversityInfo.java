package com.bb.neighbor.user.model;

public class UniversityInfo {

	/**
	 * 大学名
	 */
	private String name;
	
	/**
	 * 入学时间
	 */
	private Long year;
	
	/**
	 * 学院
	 */
	private String department;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
