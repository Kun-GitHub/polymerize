package com.juiniot.modules.business.recharge;

import java.util.HashMap;

/**
 *
 * @description  对应的（查询参数类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 *
 */
public class RechargeListParam {

	 // 参数key map
	 private HashMap<RechargeListParamKey, Object> keyMap = new HashMap<>();

	
	/**
	 * RechargeList参数key枚举类型
	 */
	public enum RechargeListParamKey {
	    id, userId, rechargeTime, rechargeTime1, rechargeTime2, price, userName, treeCode
    }
    
    //构造函数，初始化参数默认值
    public RechargeListParam() {
      
     	keyMap.put(RechargeListParamKey.id, null);
	  
     	keyMap.put(RechargeListParamKey.userId, null);
	  
     	keyMap.put(RechargeListParamKey.rechargeTime1, null); keyMap.put(RechargeListParamKey.rechargeTime2, null);
	  
		keyMap.put(RechargeListParamKey.price, null);

		keyMap.put(RechargeListParamKey.userName, null);

		keyMap.put(RechargeListParamKey.treeCode, null);
	}
	
	
	/**
	 * 设置查询参数
	 * @param key 键
	 * @param value 值
	 */
	public void putValue(RechargeListParamKey key, Object value) {
		keyMap.put(key, value);
	}

	
	/**
	 * 获取参数key map
	 * @return HashMap
	 */
	public HashMap<RechargeListParamKey, Object> getKeyMap() {
		return keyMap;
	}
	
	
}

