package com.juiniot.common.web;

import com.juiniot.common.web.preview.UserIdentity;

import javax.servlet.http.HttpServletRequest;


/**
 * Session相关操作
 * @author ZHIFEN
 */
public class Sessions {

    private static final String KEY_USER = "_user";
	
	
	/**
	 * 获取Session值
	 * 
	 * @param name
	 *            参数名
	 * @return Object
	 */
	public static final <T extends Object> T getValue(HttpServletRequest request, String name) {
		return (T) request.getSession().getAttribute(name);
	}

	/**
	 * 设置Session值
	 * 
	 * @param name
	 *            参数名
	 * @param value
	 *            参数值
	 */
	public static final void setValue(HttpServletRequest request, String name, Object value) {
		request.getSession().setAttribute(name, value);
	}
	
	/**
	 * 清楚所有session
	 * @param request
	 */
	public static final void clearAll(HttpServletRequest request){
		request.getSession().invalidate();
	}
	
	/**
	 * 清楚单个session
	 * @param request
	 * @param name
	 */
	public static final void clear(HttpServletRequest request,String name){
		request.getSession().removeAttribute(name);
	}


    /**
     * 获取session用户身份
     * @param request
     * @return
     */
    public static final UserIdentity getUser(HttpServletRequest request) {
        return getValue(request, KEY_USER);
    }

    /**
     * 设置session用户身份
     * @param request
     * @param user
     */
    public static final void setUser(HttpServletRequest request,UserIdentity user) {
        setValue(request, KEY_USER, user);
    }

    /**
     * 获取session用户身份ID
     * @param request
     * @return
     */
    public static final Long getUserId(HttpServletRequest request) {
        UserIdentity user = getValue(request, KEY_USER);
        return user.getUserId();
    }

    public static boolean isSuperAdmin(HttpServletRequest request){
        UserIdentity user = getValue(request, KEY_USER);
        return user.isSuperAdmin();
    }


}
