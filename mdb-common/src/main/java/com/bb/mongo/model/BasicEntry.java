package com.bb.mongo.model;

import java.io.Serializable;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;

@Entity(noClassnameStored = true)
public class BasicEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Indexed(name = "index_id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BasicEntry() {
	}
}
