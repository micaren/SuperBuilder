package com.macren.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.macren.common.jcaptcha.CaptchaServiceSingleton;
import com.macren.framework.ServiceMgr.RoleServiceMgr;
import com.macren.framework.ServiceMgr.UserServiceMgr;
import com.macren.framework.config.Constants;
import com.macren.framework.sys.pojo.User;
 

@Controller
@RequestMapping(value="/passport")
public class PassportController extends BaseController{
	
	@Autowired
	private UserServiceMgr userMgr;
	@Autowired
	private RoleServiceMgr roleMgr;
 

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkword = request.getParameter("checkword");
		User user = userMgr.getUserByUsername(username,password);

        /*
         * 检验码是否正确
         */
		if (!CaptchaServiceSingleton.getInstance().validateResponseForID(request.getSession().getId(), checkword))
			return new ModelAndView("login", "message", "检验码不对，请重新输入。");
		
		if (user != null 
				&& password!=null && password.equals(user.getPassword())) {
			request.getSession().setAttribute(Constants.AUTHENTICATION_KEY, user);
			request.getSession().setAttribute(Constants.MenuByRoleId,roleMgr.getRoleMenuByUserRoleId(user.getRoleid()));
			
			String backToUrl = request.getParameter("backToUrl");
			if (backToUrl == null || backToUrl.trim().length() == 0) {
				backToUrl = "admin".equalsIgnoreCase(user.getUsername())? "/admin" : "/";
			} else {
				try {
					backToUrl = java.net.URLDecoder.decode(backToUrl, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			//System.out.println("backToUrl: " + backToUrl);
			
			return new ModelAndView("redirect:"+backToUrl);
		}
		 
		return new ModelAndView("login", "message", "用户名或密码错误。");
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {

		request.getSession().removeAttribute(Constants.AUTHENTICATION_KEY);

		return  new ModelAndView("login");
	}

	
}
