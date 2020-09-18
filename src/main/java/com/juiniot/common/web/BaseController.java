package com.juiniot.common.web;

import com.juiniot.common.utils.Global;
import com.juiniot.common.web.propEditor.CustomStringEditor;
import com.juiniot.common.web.propEditor.CustomTimestampEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制类基类
 * @author ZHIFEN
 */
public abstract class BaseController {
	
	/** 日志对象  */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 初始化数据绑定
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		datetimeFormat.setLenient(false);
		
		binder.registerCustomEditor(String.class, new CustomStringEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Timestamp.class, new CustomTimestampEditor(datetimeFormat, true));
	}
	
	/**
	 * 获取分页模型对象
	 * @param page 当前页
	 * @param pageSize 每页记录数
	 * @param list 数据列表
	 */
	protected <T> PageModel<T> getPageModel(int page,int totalRows,int pageSize,List<T> list) {
		
		PageModel<T> pm = new PageModel<>();
		pm.init(page, totalRows, pageSize);
		pm.setList(list);

        return pm;
	}
	
	/**
	 * 左匹配模糊查询
	 */
	protected String leftFuzzy(String str){
		return StringUtils.isEmpty(str) ? null : "%"+str;
	}
	
	/**
	 * 右匹配模糊查询
	 */
	protected String rightFuzzy(String str){
		return StringUtils.isEmpty(str) ? null : str+"%";
	}
	
	/**
	 * 左右全匹配模糊查询
	 */
	protected String allFuzzy(String str){
		return StringUtils.isEmpty(str) ? null : "%"+str+"%";
	}


    protected Map<String, String> getCameraMap(Long bridgeId) {
        Map<String, String> cameraMap = new HashMap<>();
        cameraMap.put("ip", Global.getConfig("camera.ip"+bridgeId));
        cameraMap.put("port", Global.getConfig("camera.port"+bridgeId));
        cameraMap.put("user", Global.getConfig("camera.user"+bridgeId));
        cameraMap.put("pwd", Global.getConfig("camera.pwd"+bridgeId));
        return cameraMap;
    }
	
}
