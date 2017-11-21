package com.macren.business.enums;

public enum JournalLevel {
	L1,L2,L3,L4;
	
	public String getKey(){
		return "enum.journalLevel."+this;
	}
}
