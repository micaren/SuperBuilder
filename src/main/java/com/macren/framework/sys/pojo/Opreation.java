package com.macren.framework.sys.pojo;

import com.macren.framework.sys.business.AbstractBusinessObject;

public class Opreation extends AbstractBusinessObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5504466477453605794L;
	private String id;
	private String code;
	private String orgid;
	private int num;
	private String comment;
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
