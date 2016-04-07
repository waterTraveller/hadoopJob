package com.topeastic.hadoop.interceptors;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 过滤特殊字符
 * @author root
 *
 */
public class ContentFilter implements HandlerInterceptor{

	private static Logger logger = Logger.getLogger(ContentFilter.class);
	private static HashMap<String,String> maps;
	
	static{
		maps = new HashMap<String, String>();
		maps.put("&", "&amp");
    	maps.put("<", "&lt");
    	maps.put(">", "&gt");
	}
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
		HttpSession session = request.getSession();   
	    //过滤客户端提交表单中特殊字符
		Iterator its = request.getParameterMap().values().iterator();
		while(its.hasNext()){
		     String[] params = (String[])its.next();
		     for (int i = 0; i < params.length; i++) {
		          for (String key :maps.keySet()) {
			           params[i] = params[i].replace(key, maps.get(key)).trim();
		          }
		     }
		}  
		return true;
	}

}
