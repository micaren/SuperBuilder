package com.macren.framework.ServiceMgr;

public enum UserStatus {
	INACTIVE(0),ACTIVE(1),DELETED(2);
 
    private int index;
    private UserStatus(  int index) {
       
        this.index = index;
    }
    public int getIndex() {
        return index;
    }

    
}
