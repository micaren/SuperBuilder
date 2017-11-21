package com.macren.common.jcaptcha;

import com.macren.common.jcaptcha.servlet.GMailEngine;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

 
//import com.octo.captcha.engine.GenericCaptchaEngine;
//import com.octo.captcha.service.CaptchaService;

/**
 *  
 * @author Administrator
 *
 */
public class CaptchaServiceSingleton {
	 private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService(
			   new FastHashMapCaptchaStore(), new GMailEngine(), 180,
			   1000000 , 75000);
//	private static ImageCaptchaService instance=new DefaultManageableImageCaptchaService();
    public static ImageCaptchaService getInstance(){
        return instance;
    }
}