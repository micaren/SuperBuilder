package com.macren.web.admin;

//import javax.inject.Inject;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
 
import org.springframework.web.bind.annotation.RequestMapping;

import com.macren.web.BaseController;

@Controller("admin.indexController")
@RequestMapping("/admin")
public class IndexController extends BaseController{

	@RequestMapping("")
	public String index(Model model) {

		model.addAttribute("now", new Date());
	 System.out.println("--------------------------------------------");
		return "/admin/index";
	}

	
}