package com.macren.web;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import com.macren.business.enums.Region;
import com.macren.common.util.ServerInfo;
import com.macren.framework.config.AppConfiguration;
import com.macren.framework.config.Constants;
import com.macren.framework.context.AppContext;
import com.macren.framework.context.AppContextHolder;
import com.macren.framework.sys.pojo.User;
import com.macren.web.editor.DateEditor;
import com.macren.web.editor.DoubleEditor;
import com.macren.web.editor.IntegerEditor;
import com.macren.web.editor.LongEditor;

public abstract class BaseController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ResourceBundleMessageSource _res;
	
//	@Autowired
//	protected SpringContextHolder _contextHolder;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	protected ModelAndView ajaxDone(int statusCode, String message, String forwardUrl) {
		ModelAndView mav = new ModelAndView("ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		mav.addObject("forwardUrl", forwardUrl);
		return mav;
	}
	
	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message, "");
	}

	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(300, message, "");
	}
	protected String getMessage(String code) {
		return this.getMessage(code, new Object[] {});
	}

	protected String getMessage(String code, Object arg0) {
		return getMessage(code, new Object[] { arg0 });
	}

	protected String getMessage(String code, Object arg0, Object arg1) {
		return getMessage(code, new Object[] { arg0, arg1 });
	}

	protected String getMessage(String code, Object arg0, Object arg1,
			Object arg2) {
		return getMessage(code, new Object[] { arg0, arg1, arg2 });
	}

	protected String getMessage(String code, Object arg0, Object arg1,
			Object arg2, Object arg3) {
		return getMessage(code, new Object[] { arg0, arg1, arg2, arg3 });
	}

	protected String getMessage(String code, Object[] args) {
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale = localeResolver.resolveLocale(request);

		return _res.getMessage(code, args, locale);
	}
	
	protected AppConfiguration getAppConfig() {
		return AppConfiguration.getInstance();
	}
	//随机码认证功能
 	 protected boolean verifyValidationCode(
			String validationCode) {

		boolean enableVerify = getAppConfig()
				.getBoolean("validation-code.validation-code-enable");

		if (enableVerify) {
			if (validationCode == null)
				return false;

			String randomCode = null;
			try {
				randomCode = (String) request.getSession().getAttribute(
						Constants.VALIDATION_CODE);
				System.out.println(randomCode + " : " + validationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (randomCode == null)
				return false;
			else if (!randomCode.equalsIgnoreCase(validationCode.trim()))
				return false;
		}
		return true;
	}  
	
	@ModelAttribute("regionList") 
	public List<Region> getRegionList(){
		return Arrays.asList(Region.values());
	}
	
	@ModelAttribute("contextUser")
	public User getContextUser() {
		AppContext appContext = AppContextHolder.getContext();
		
		if (appContext != null) {
			return appContext.getUser();
		}
		System.out.println("test");
		return null;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		
		request.setAttribute("exception", e);
		
		if (ServerInfo.isAjax(request) || request.getParameter("ajax") != null) {
			return ajaxDoneError(e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("statusCode", 300);
		mav.addObject("message", e.getMessage());
		
		return mav;
	}
}
