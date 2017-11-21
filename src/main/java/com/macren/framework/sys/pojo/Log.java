package com.macren.framework.sys.pojo;

import java.util.Date;

import com.macren.framework.sys.business.AbstractBusinessObject;

public class Log extends AbstractBusinessObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7127183466074965083L;
	private String id;
	private String userid;
	private String operation;
	private String roleid;
	private String orgid;
	private String staffid;
	private String type;
	private Date time;
	private String comment;
	private String color;

	public String getColor() {
		return color;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
