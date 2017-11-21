package com.macren.role;

import java.util.Hashtable;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.macren.framework.ServiceMgr.RoleServiceMgr;
import com.macren.framework.junit.BaseJunitCase;

public class RoleServiceMgrTest extends BaseJunitCase{
 
	@Autowired
	private RoleServiceMgr roleMgr;
	
	@Test
	public void TestMenu(){
		List ll =roleMgr.getRoleMenuByUserRoleId("1001");
		for(Object object:ll)
		{
			Hashtable O =(Hashtable) object;
			List <Object> sub =(List<Object>) O.get("RoleSub") ;
			for(Object ob:sub)
			{
				Hashtable o =(Hashtable) ob;
				System.out.println(o.get("text"));
			}
		}
	}
}
