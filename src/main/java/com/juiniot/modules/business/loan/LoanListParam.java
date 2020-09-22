package com.juiniot.modules.business.loan;
 
import java.util.*;

/**
 *
 * @description  对应的（查询参数类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 *
 */
public class LoanListParam {

	 // 参数key map
	 private HashMap<LoanListParamKey, Object> keyMap = new HashMap<>();

	
	/**
	 * LoanList参数key枚举类型
	 */
	public enum LoanListParamKey {
	    id, userId, name, phone, sex, city, quota, status, loanTime, loanTime1, loanTime2, source, remark, account, treeCode, price, rate
    }
    
    //构造函数，初始化参数默认值
    public LoanListParam() {
      
     	keyMap.put(LoanListParamKey.id, null);
	  
     	keyMap.put(LoanListParamKey.userId, null);
	  
     	keyMap.put(LoanListParamKey.name, null);
	  
     	keyMap.put(LoanListParamKey.phone, null);
	  
     	keyMap.put(LoanListParamKey.sex, null);
	  
     	keyMap.put(LoanListParamKey.city, null);
	  
     	keyMap.put(LoanListParamKey.quota, null);
	  
     	keyMap.put(LoanListParamKey.status, null);
	  
     	keyMap.put(LoanListParamKey.loanTime1, null); keyMap.put(LoanListParamKey.loanTime2, null);
	  
     	keyMap.put(LoanListParamKey.source, null);
	  
     	keyMap.put(LoanListParamKey.remark, null);


		keyMap.put(LoanListParamKey.account, null);

		keyMap.put(LoanListParamKey.treeCode, null);

		keyMap.put(LoanListParamKey.price, null);

		keyMap.put(LoanListParamKey.rate, null);
	 
	}
	
	
	/**
	 * 设置查询参数
	 * @param key 键
	 * @param value 值
	 */
	public void putValue(LoanListParamKey key, Object value) {
		keyMap.put(key, value);
	}

	
	/**
	 * 获取参数key map
	 * @return HashMap
	 */
	public HashMap<LoanListParamKey, Object> getKeyMap() {
		return keyMap;
	}
	
	
}

