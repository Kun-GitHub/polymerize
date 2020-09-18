package com.juiniot.modules.business.sms;
 
import java.util.*;

/**
 *
 * @description 短信记录 对应的（查询参数类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-12 15:16:39
 *
 */
public class SmsListParam {

	 // 参数key map
	 private HashMap<SmsListParamKey, Object> keyMap = new HashMap<>();

	
	/**
	 * SmsList参数key枚举类型
	 */
	public enum SmsListParamKey {
	    id, phone, lastTime, lastTime1, lastTime2, nums, sms, code, source
    }
    
    //构造函数，初始化参数默认值
    public SmsListParam() {
      
     	keyMap.put(SmsListParamKey.id, null);
	  
     	keyMap.put(SmsListParamKey.phone, null);
	  
     	keyMap.put(SmsListParamKey.lastTime1, null); keyMap.put(SmsListParamKey.lastTime2, null);
	  
     	keyMap.put(SmsListParamKey.nums, null);
	  
     	keyMap.put(SmsListParamKey.sms, null);

		keyMap.put(SmsListParamKey.code, null);

		keyMap.put(SmsListParamKey.source, null);
	 
	 
	}
	
	
	/**
	 * 设置查询参数
	 * @param key 键
	 * @param value 值
	 */
	public void putValue(SmsListParamKey key, Object value) {
		keyMap.put(key, value);
	}

	
	/**
	 * 获取参数key map
	 * @return HashMap
	 */
	public HashMap<SmsListParamKey, Object> getKeyMap() {
		return keyMap;
	}
	
	
}

