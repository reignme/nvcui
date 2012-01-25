package org.nvc.ui.login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.nvc.ui.login.dao.LoginDao;
import org.nvc.ui.login.model.Login;
import org.nvc.ui.member.service.MemberService;


@Controller

public class LoginController
{
	private static Log log = LogFactory.getLog(LoginController.class);
	
	private static final String INIT_ACTION = "/login";
	private static final String PROCESS_ACTION = "/login/process";
	
	private static final String INIT_VIEW = "login/login";
	
	static 
	{
		ResourceBundle bundle = ResourceBundle.getBundle("nvcui");
	} 
	
	@RequestMapping("/login")
	public ModelAndView init()
	{
		System.out.println("LoginController: init()");
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		Login login = new Login();
		modelMap.put("login", login);
        modelMap.put("action", PROCESS_ACTION);
        modelMap.put("title", "Login");
        
		ModelAndView mav = new ModelAndView(INIT_VIEW, modelMap);
		
		return mav;
	}
	
	@RequestMapping("/login/process")
	public ModelAndView process(@Valid Login login, BindingResult result)
	{
		System.out.println("LoginController: process()");
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("login",login );
        
        if(result.hasErrors())
        {
        	System.out.println("LoginController: process() - error ");
            modelMap.put("message", "Please correct the following errors");
            modelMap.put("action",INIT_ACTION);
            return new ModelAndView(INIT_VIEW, modelMap);  
        }
		
        //done, so redirect to main page.
        ModelAndView mav = new ModelAndView("redirect:/home");
		return mav;
	}
}
