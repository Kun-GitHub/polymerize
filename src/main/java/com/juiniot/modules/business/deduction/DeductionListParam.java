package com.juiniot.modules.business.deduction;

import java.util.HashMap;

/**
 *
 * @description  对应的（查询参数类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 *
 */
public class DeductionListParam {

	 // 参数key map
	 private HashMap<DeductionListParamKey, Object> keyMap = new HashMap<>();


	/**
	 * DeductionList参数key枚举类型
	 */
	public enum DeductionListParamKey {
	    id, userId, deductionTime, deductionTime1, deductionTime2, price, userName, treeCode
    }

    //构造函数，初始化参数默认值
    public DeductionListParam() {
      
     	keyMap.put(DeductionListParamKey.id, null);
	  
     	keyMap.put(DeductionListParamKey.userId, null);
	  
     	keyMap.put(DeductionListParamKey.deductionTime1, null); keyMap.put(DeductionListParamKey.deductionTime2, null);
	  
		keyMap.put(DeductionListParamKey.price, null);

		keyMap.put(DeductionListParamKey.userName, null);

		keyMap.put(DeductionListParamKey.treeCode, null);
	}
	
	
	/**
	 * 设置查询参数
	 * @param key 键
	 * @param value 值
	 */
	public void putValue(DeductionListParamKey key, Object value) {
		keyMap.put(key, value);
	}

	
	/**
	 * 获取参数key map
	 * @return HashMap
	 */
	public HashMap<DeductionListParamKey, Object> getKeyMap() {
		return keyMap;
	}
	
	
}

