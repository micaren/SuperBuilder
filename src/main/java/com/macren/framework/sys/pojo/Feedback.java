package com.macren.framework.sys.pojo;

import java.util.Date;

import com.macren.framework.sys.business.AbstractBusinessObject;

public class Feedback extends AbstractBusinessObject{
 /**
	 * 
	 */
	private static final long serialVersionUID = 4915507092030435098L;
private String id;
 private String title;
 private String context;
 private String userid;
 private Date time;
 private String color;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContext() {
	return context;
}
public void setContext(String context) {
	this.context = context;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
 
}
