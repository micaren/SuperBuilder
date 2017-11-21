package com.macren.framework.context;

import com.macren.framework.sys.pojo.User;

public interface AppContext {

	public User getUser();

	public void setUser(User user);

//	public Website getWebsite();
//	public void setWebsite(Website website);

}
