package org.nvc.ui.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.nvc.ui.user.dao.UserDao;
import org.nvc.ui.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController 
{
	private static final String CREATE_VIEW = "login/user_info_input";
	@Autowired private UserDao userDao;
	
	@RequestMapping("/{memberId}")
	public ModelAndView create(@PathVariable long memberId, HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user;
		
		//want to make sure that there is no log in...
		try
		{
			user = userDao.loadUserById(memberId);
		}
		catch(Exception e)
		{
			user = new User();
		}
		
		user.setMemberId(memberId);
		user.setPassword("");
		
		modelMap.put("user", user);
        modelMap.put("action", "Create User");
        modelMap.put("title", "Create User");
        
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		return mav;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@PathVariable long userId, HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		User user = userDao.loadUserById(userId);
        modelMap.put("title", "Login");
        
        modelMap.put("user", user);
        
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		
		return mav;
	}
	
	@RequestMapping("/save")
	public ModelAndView save(@Valid User user, HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("title", "Login");
		
		if(user.getMemberId() == 0)
		{
			userDao.createUser(user);
		}
		else
			userDao.updateLogin(user);
		
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		return mav;
	}

}
