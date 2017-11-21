package com.macren.web.admin;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macren.web.BaseController;

@Controller("admin.druidController")
@RequestMapping(value="/admin/druid")
public class DruidController extends BaseController{
	 

	@RequestMapping("")
	public String show( Model model) {
		 
		return "/admin/druid/show";
	}
	 
}
