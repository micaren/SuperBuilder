package com.macren.framework.sys.pojo;

import com.macren.framework.sys.business.AbstractBusinessObject;

public class Role extends AbstractBusinessObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1682982509122032330L;
	private String id;
	private String name;
	private int enable;
	private String orgid;
	private String comment;
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
