package com.macren.persistence;


import java.util.Date;

public class BaseConditionVO {
	public final static int PAGE_SHOW_COUNT = 20;
	private int pageNum = 1;
	private int pageSize = 0;
	private int totalCount = 0;
	private String orderField;
	private String orderDirection;
	private String keywords;
	private String keywords1;
	private String keywords2;
	private String keywords3;
	private String keywords4;
	private String keywords5;
	private String status;
	private String type;
	private Date startDate;
	private Date endDate;
	
	public String getType() {
		return "".equals(type) ? null : type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return "".equals(status)? null : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize > 0 ? pageSize : PAGE_SHOW_COUNT;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderDirection() {
		return "desc".equals(orderDirection) ? "desc" : "asc";
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getKeywords() {
		return "".equals(keywords)? null : keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public int getStartIndex() {
		int pageNum = this.getPageNum() > 0 ? this.getPageNum() - 1 : 0;
		return pageNum * this.getPageSize();
	}
	public String getKeywords1() {
		return "".equals(keywords1)? null : keywords1;
	}
	public void setKeywords1(String keywords1) {
		this.keywords1 = keywords1;
	}
	public String getKeywords2() {
		return "".equals(keywords2)? null : keywords2;
	}
	public void setKeywords2(String keywords2) {
		this.keywords2 = keywords2;
	}
	public String getKeywords3() {
		return "".equals(keywords3)? null : keywords3;
	}
	public void setKeywords3(String keywords3) {
		this.keywords3 = keywords3;
	}
	public String getKeywords4() {
		return "".equals(keywords4)? null : keywords4;
	}
	public void setKeywords4(String keywords4) {
		this.keywords4 = keywords4;
	}
	public String getKeywords5() {
		return "".equals(keywords5)? null : keywords5;
	}
	public void setKeywords5(String keywords5) {
		this.keywords5 = keywords5;
	}
	
	
}
