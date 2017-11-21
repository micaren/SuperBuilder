package com.macren.business.enums;

import java.util.List;

public enum Region {
	CN,TW;
	
	public String getKey(){
		return "enum.region."+this;
	}
	
	public PublishingAgency[] getPublishingAgencyList(){
		return PublishingAgency.values(this);
	}
	
	public List<LawCategory> getLawCategoryList() {
		return LawCategory.values(this);
	}
}
