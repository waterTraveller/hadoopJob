package com.topeastic.hadoop.interceptors;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.topeastic.hadoop.annotation.Token;

/**
 * 防止表单重复提交，拦截器
 * 在需要token的表单方法上，添加@Token(save=true)，并在表单上添加：<input type="hidden" name="token" value="${token}" />
 * 在添加完之后，去掉session中的token，添加@Token(remove=true)注释。
 * @author root
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false;
                    }
                    request.getSession(false).removeAttribute("token");
                }
            }
            
            return true;
        } else {
        	
            return super.preHandle(request, response, handler);
        }
	}
	
	/**
	 * 检查是否存在token，是不是重复提交
	 * @param request
	 * @return
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
		Object obj = request.getSession(false).getAttribute("token");
        String serverToken = obj != null ? obj.toString() : null ;
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }

}
