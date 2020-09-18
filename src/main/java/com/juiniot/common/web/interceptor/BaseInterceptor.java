package com.juiniot.common.web.interceptor;

import com.juiniot.common.utils.AjaxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础拦截器
 * @author ZHIFEN
 *
 */
public abstract class BaseInterceptor implements HandlerInterceptor {
	
	/** 日志对象  */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());


	/**
	 * 判断是否异步请求
	 * @param method
	 * @return
	 */
	protected boolean isAjax(HandlerMethod method) {
		if(method == null){
			return false;
		}
		ResponseBody resp = method.getMethodAnnotation(ResponseBody.class);
		return resp != null;
	}

	/**
	 * 会话过期
	 * @param request
	 * @param response
	 * @param method
	 * @throws Exception
	 */
	protected void timeout(HttpServletRequest request, HttpServletResponse response,HandlerMethod method) throws Exception {
		String msg = "会话已过期，请您重新登录后再进行操作。";
		if (isAjax(method)) {
			AjaxUtil.failure(response, msg, "timeout");
		}
		else {
			AjaxUtil.html(response, "<script>window.top.location.href='"+request.getContextPath()+"/login';</script>");
		}
	}
	

	/**
	 * 无操作权限
	 * @param request
	 * @param response
	 * @param method
	 * @throws Exception
	 */
	protected void noAuth(HttpServletRequest request, HttpServletResponse response,HandlerMethod method) throws Exception {
		String msg = "您的账号尚未开通该功能的操作权限。";
		if (isAjax(method)) {
			AjaxUtil.failure(response, msg, "noauth");
		} else {
			String url = request.getContextPath()+"/statics/403.html";
			response.sendRedirect(url);
		}
	}
	
	/**
	 * 系统出错
	 * @param request
	 * @param response
	 * @param method
	 * @throws Exception
	 */
	protected void error(HttpServletRequest request,HttpServletResponse response,HandlerMethod method,Exception ex) throws Exception {
		String msg = "系统发生错误，不便之处，敬请谅解。";
		if (isAjax(method)) {
			AjaxUtil.failure(response, msg, "error");
		}
	}


}
