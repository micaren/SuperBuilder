package com.macren.framework.sys.logic;

import java.util.List;

import com.macren.framework.sys.pojo.Opreation;

public class ModelRoleOpreation {
	private String modelid;
	private String roleid;
	private String orgid;
	private List<Opreation> opre;

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public List<Opreation> getOpre() {
		return opre;
	}

	public void setOpre(List<Opreation> opre) {
		this.opre = opre;
	}

}
