package com.macren.framework.sys.pojo;

import com.macren.framework.sys.business.AbstractBusinessObject;

public class RoleModel extends AbstractBusinessObject{
  /**
	 * 
	 */
	private static final long serialVersionUID = 2945571225551842264L;
private String rmid;
  private String roleid;
  private String modelid;
  private String opid;
  private String orgid;
  private String comment;
  private String color;
public String getId() {
	return rmid;
}
public void setRmid(String rmid) {
	this.rmid = rmid;
}
public String getRoleid() {
	return roleid;
}
public void setRoleid(String roleid) {
	this.roleid = roleid;
}
public String getModelid() {
	return modelid;
}
public void setModelid(String modelid) {
	this.modelid = modelid;
}
public String getOpid() {
	return opid;
}
public void setOpid(String opid) {
	this.opid = opid;
}
public String getOrgid() {
	return orgid;
}
public void setOrgid(String orgid) {
	this.orgid = orgid;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
  
}
