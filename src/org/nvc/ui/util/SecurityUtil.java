package org.nvc.ui.util;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.nvc.ui.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.web.context.support.WebApplicationObjectSupport;

public class SecurityUtil extends WebApplicationObjectSupport 
{
	
	static public boolean isUserInRole(String roleName)
	{
		String principal = ((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getName();
		boolean returnVal = false;
		if (!principal.equals("anonymousUser"))
		{
			List<GrantedAuthority> auths = (List<GrantedAuthority>)((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getAuthorities();
			for(int i=0; i<auths.size(); i++)
			{
				if (auths.get(i).getAuthority().equals(roleName))
				{
					returnVal=true;
					break;
				}
			}
		}
		return returnVal;
	}

	public static String getUsername() throws TimeoutException
	{
		String principal = ((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getName();
		if (principal == null || principal.length() < 1 || principal == "" || principal.equals("anonymousUser")) {
			//return "unassigned"; 
			throw new TimeoutException();
		} else {
			return principal;
		}
	}

	public static String getLoginUser() throws TimeoutException
	{
		String principal = getUsername();
		//System.out.println("SecurityUtil:principal=" + principal);
		//UserService userService = CommonObjectFactory.getUserService();
		//UserLogin u = userService.getUserLoginByLogin(principal);
		return principal;
	}

	public static Collection<GrantedAuthority> getAuthority()
	{
		return ((SecurityContext)SecurityContextHolder.getContext()).getAuthentication().getAuthorities();
	}
	
}

