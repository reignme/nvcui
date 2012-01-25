package org.nvc.ui.home;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.nvc.ui.util.SecurityUtil;

@Controller
public class HomeController 
{
	private static final String INIT_VIEW = "home";
	
	
	@RequestMapping(value = "/home")
	public ModelAndView init()
	{
		String username;
		try
		{
			username = SecurityUtil.getLoginUser();
		}
		catch (TimeoutException e)
		{
			ModelAndView mav = new ModelAndView("redirect:/login");
			return mav;
		}
		
		Collection<GrantedAuthority> authority = SecurityUtil.getAuthority();
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("username", username);
		modelMap.put("authority", authority.toArray()[0].toString());
        return new ModelAndView(INIT_VIEW, modelMap);  
		
	}
	
	@RequestMapping(value = "test")
	public String home2()
	{
		System.out.println("HomeController: Passing through...");
		return "home";
	}
}
