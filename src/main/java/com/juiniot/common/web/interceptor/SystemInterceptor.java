package com.juiniot.common.web.interceptor;

import com.juiniot.common.utils.CommonConfUtil;
import com.juiniot.common.utils.Cookies;
import com.juiniot.common.utils.Global;
import com.juiniot.common.utils.StringUtil;
import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统拦截器（异常、页面公共数据、权限拦截）
 * @author ZHIFEN
 * 
 */
public class SystemInterceptor extends BaseInterceptor {

	// 需要排除的参数名称
	private static final String[] paramNames = new String[] { "page" };
    private static final String appName = CommonConfUtil.getInstance().getGlobalParams("appName");

    @Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ex != null) {
			log.error(ex.getMessage(), ex);
			// 返回错误提示
			HandlerMethod method = (HandlerMethod) handler;
			error(request, response, method, ex);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView view) throws Exception {

		if (view != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ctx", request.getContextPath());
			map.put("uri", request.getRequestURI());
			map.put("appName",appName);
            map.put("permission", Global.getConfig("permission.open"));

			String qs = request.getQueryString();
			String parameter = "";
			if (qs != null) {
				String[] qsArr = qs.split("&");
				for (String str : qsArr) {
					String[] strArr = str.split("=");
					if (!ArrayUtils.contains(paramNames, strArr[0])
							&& strArr.length > 1) {
						parameter += "&" + str;
					}
				}
			}

			map.put("parameter", parameter);
			view.addAllObjects(map);
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
        NeedSession sessionType = NeedSession.YES; // 是否需要session限制

        HandlerMethod method;
        try {
            method = (HandlerMethod)handler;
        } catch (Exception e) {
            return true;
        }

        Authority methodAuth = method.getMethodAnnotation(Authority.class);

        if(methodAuth != null){
            sessionType = methodAuth.needSession();
        }

        //登录检查
        if(sessionType == NeedSession.YES){
			String account = Cookies.getValue(request, "account");
            if(StringUtil.isBlank(account)){
                timeout(request, response, method);
                return false;
            }
        }

        return true;
	}

}
