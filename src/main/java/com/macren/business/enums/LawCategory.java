package com.macren.business.enums;

import java.util.ArrayList;
import java.util.List;

public enum LawCategory {
/*
 * 台湾：
		宪法
		行政法
		民事法
		刑事法
		商事法暨金融法
		智慧财产权法
		国际法暨条约协定
		医事法规
		金融证照法规
		土地及不动产法规
		税务法规
	大陆：
		宪法
		行政法与行政诉讼法
		刑法
		刑事诉讼法
		民法
		民事诉讼法
		经济法
		商法
 */
	CONSTITUTION_TW(Region.TW, "宪法"),
	ADMINISTRATIVE_TW(Region.TW, "行政法"),
	CIVIL_TW(Region.TW, "民事法"),
	CRIMINAL_TW(Region.TW, "刑事法"),
	COMMERCIAL_FINANCIAL_TW(Region.TW, "商事法暨金融法"),
	INTELLECTUAL_PROPERTY_TW(Region.TW, "智慧财产权法"),
	INTERNATIONAL_TREATIES_TW(Region.TW, "国际法暨条约协定"),
	MEDICAL_TW(Region.TW, "医事法规"),
	FINANCIAL_LICENSES_TW(Region.TW, "金融证照法规"),
	LAND_ESTATE_TW(Region.TW, "土地及不动产法规"),
	TAX_TW(Region.TW, "税务法规"),
	
	CONSTITUTION_CN(Region.CN, "宪法"),
	ADMINISTRATIVE_CN(Region.CN, "行政法与行政诉讼法"),
	CRIMINAL_CN(Region.CN, "刑法"),
	CRIMINAL_PROCEDURE_CN(Region.CN, "刑事诉讼法"),
	CIVIL_CN(Region.TW, "民法"),
	CIVIL_PROCEDURE_CN(Region.CN, "民事诉讼法"),
	ECONOMIC_CN(Region.CN, "经济法"),
	COMMERCIAL_CN(Region.CN, "商法");
	
	private Region region;
	private String name;
	
	LawCategory(Region region, String name) {
		this.region = region;
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Region getRegion() {
		return this.region;
	}
	
	public static List<LawCategory> values(Region region) {
		List<LawCategory> lawCategoryList = new ArrayList<LawCategory>();
		
		for (LawCategory cat : values()) {
			if (cat.region.equals(region)) {
				lawCategoryList.add(cat);
			}
		}
		return lawCategoryList;
	}
}
