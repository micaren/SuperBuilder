package com.macren.framework.sys.logic;

import com.macren.persistence.BaseConditionVO;

public class PageUtil {
   public static PageCommon getPageInfo(int startpage,int pagesize,int totalrow)
   {
	   PageCommon pc = new PageCommon();
	   if (startpage*pagesize>=totalrow)
	   {
		   pc.setStartrow((startpage-1)*(pagesize)+1);
		   pc.setStoprow(totalrow);
	   }
	   else
	   {
		   pc.setStartrow((startpage-1)*(pagesize)+1);
		   pc.setStoprow(startpage*pagesize);
	   }
	   return pc;
   }
   
   static public void main(String [] str)
   {
	   BaseConditionVO vo = new BaseConditionVO();
	   System.out.println(PageUtil.getPageInfo(0, vo.getPageSize(), 35).getStoprow());
   }
}
