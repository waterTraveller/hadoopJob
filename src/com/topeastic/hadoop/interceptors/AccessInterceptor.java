package com.topeastic.hadoop.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.topeastic.hadoop.commons.Constant;

/**
 * 权限验证
 * @author root
 *
 */
public class AccessInterceptor implements HandlerInterceptor {
	private static Logger logger = Logger.getLogger(AccessInterceptor.class);

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
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getRequestURI();

		logger.info("url:[" + request.getRequestURI() + "]");

		@SuppressWarnings("unchecked")
		List<String> perList = (List<String>) request.getSession()
				.getAttribute(Constant.CURRENT_USER_PERMISSIONS);

		if (perList != null && perList.size() > 0) {
			for (String string : perList) {
				if (url.contains(string)) {
					return true;
				}
			}
		}

		request.getSession().setAttribute(Constant.ERROR, "抱歉暂无此权限，请联系管理员。");
		response.sendRedirect(request.getContextPath() + "/error");
		return false;
	}

}
