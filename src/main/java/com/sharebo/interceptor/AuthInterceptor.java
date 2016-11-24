package com.sharebo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sharebo.util.DateUtil;
/**
 * 接口安全验证拦截器
 * @author niewei
 *
 */
public class AuthInterceptor implements HandlerInterceptor {
	@Value("${token}")
	private String token;
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		 
		
		if(!request.getMethod().equalsIgnoreCase("post")){
			return false;
		}
		//得到token
		String token=request.getParameter("paramkey"); 
		if(token==null){
			//return false;
			return true;
		}
		//进行token加密判断
		String m_token=com.sharebo.util.security.MD5Util.GetMD5Code(this.token+DateUtil.getDate("dd"));
		//比较
		if(m_token.equalsIgnoreCase(token)){
			return true;
		}
		return true;
	}
}
