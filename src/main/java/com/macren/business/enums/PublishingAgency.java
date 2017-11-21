package com.macren.business.enums;

public enum PublishingAgency {
	
	ACADEMIC_UNIT,
	GOVERNMENT_ORGAN,
	SCIENCE_ASSOCIATION,
	PRIVATE_PUBLISHING;
	
	public String getKey(){
		return "enum.publishingAgency."+this;
	}
	
	public static PublishingAgency[] values(Region region){
		if (Region.TW.equals(region)) {
			return values();
		}
		
		return new PublishingAgency[] {ACADEMIC_UNIT,SCIENCE_ASSOCIATION};
	}
}
