package com.macren.business.enums;

public enum ChapterRelativeType {

	journal_term("期刊 - 期", "/journal/chapter"), 
	book("图书", "/journal/chapter"), 
	thesis("论文", "/journal/chapter"), 
	teaching("教学资源", "/journal/chapter"), 
	dictionary("词典", "/dictionary"), 
	legislation("两岸法规", "/legislation");
	private String name;
	private String mapping;

	ChapterRelativeType(String name, String mapping) {
		this.name = name;
		this.mapping = mapping;
	}

	public String getName() {
		return name;
	}

	public String getMapping() {
		return mapping;
	}

	public static ChapterRelativeType getChapterRelativeType(String name) {
		for (ChapterRelativeType po : ChapterRelativeType.values()) {
			if (po.name.equals(name))
				;
			return po;
		}
		return ChapterRelativeType.book;
	}
}
