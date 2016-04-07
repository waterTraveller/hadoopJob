package com.topeastic.hadoop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.topeastic.hadoop.commons.Constant;
import com.topeastic.hadoop.entity.User;
import com.topeastic.hadoop.service.UserService;
import com.topeastic.hadoop.utils.StringUtils;

@Controller
public class LoginController {
	
	static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	public String login(ModelMap model, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		logger.info("user login:[" + username + "], passwd is:["+password+"]");
		if(!StringUtils.strIsNotNull(username)||!StringUtils.strIsNotNull(password)){
			if(!StringUtils.strIsNotNull(username)){
				logger.info("username is :"+username);
				model.put("usernameError", "用户名不能为空");
			}
			if(!StringUtils.strIsNotNull(password)){
				logger.info("password is :"+password);
				model.put("passwordError", "密码不能为空");
			}
			return "login";
		}
		
		/**
		 * 查询是否存在用户
		 */
		User user = userService.checkLogin(username, password);

		if (user == null) {
			logger.info("user is null.");
			model.put(Constant.ERROR, "用户名或密码不正确");
			return "login";
		} else {
			model.put(Constant.CURRENT_USER, user);
			request.getSession().setAttribute(Constant.CURRENT_USER, user);
			logger.info("user:" + ToStringBuilder.reflectionToString(user));
		}
		return "redirect:/welcome";
	}
	
	@RequestMapping("error")
	public ModelAndView error(HttpServletRequest request){
		logger.info("to error.");
		return new ModelAndView("error");
	}
	
	@RequestMapping("exit")
	public ModelAndView exit(HttpServletRequest request){
		logger.info("user exit.");
		request.getSession().invalidate();
		return new ModelAndView("login");
	}
	
}
