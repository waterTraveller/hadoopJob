package com.topeastic.hadoop.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.topeastic.hadoop.entity.User;
import com.topeastic.hadoop.utils.StringUtils;

/**
 * 登录验证
 * @author root
 *
 */
public class LoginInterceptor implements HandlerInterceptor{


	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		User user = (User)request.getSession().getAttribute("currentUser");
		
		if(user != null && StringUtils.strIsNotNull(user.getUserName())){
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/exit");
			request.getSession().setAttribute("requestPath", request.getRequestURI().replace(request.getContextPath(), ""));
			return false;
		}
	}

}
