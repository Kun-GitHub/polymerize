package com.juiniot.modules.business.user;
 
import java.util.*;

/**
 *
 * @description  对应的（查询参数类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 15:52:09
 *
 */
public class UserListParam {

	 // 参数key map
	 private HashMap<UserListParamKey, Object> keyMap = new HashMap<>();

	
	/**
	 * UserList参数key枚举类型
	 */
	public enum UserListParamKey {
	    id, parentId, account, mobile, pwd, name, money, treeCode, createTime, createTime1, createTime2, parentName, rate, surplus
    }
    
    //构造函数，初始化参数默认值
    public UserListParam() {
      
     	keyMap.put(UserListParamKey.id, null);
	  
     	keyMap.put(UserListParamKey.parentId, null);
	  
     	keyMap.put(UserListParamKey.account, null);
	  
     	keyMap.put(UserListParamKey.mobile, null);
	  
     	keyMap.put(UserListParamKey.pwd, null);
	  
     	keyMap.put(UserListParamKey.name, null);
	  
     	keyMap.put(UserListParamKey.money, null);
	  
     	keyMap.put(UserListParamKey.treeCode, null);

		keyMap.put(UserListParamKey.surplus, null);

		keyMap.put(UserListParamKey.rate, null);
	  
     	keyMap.put(UserListParamKey.createTime1, null); keyMap.put(UserListParamKey.createTime2, null);
	 
	           
	    keyMap.put(UserListParamKey.parentName, null);
	 
	}
	
	
	/**
	 * 设置查询参数
	 * @param key 键
	 * @param value 值
	 */
	public void putValue(UserListParamKey key, Object value) {
		keyMap.put(key, value);
	}

	
	/**
	 * 获取参数key map
	 * @return HashMap
	 */
	public HashMap<UserListParamKey, Object> getKeyMap() {
		return keyMap;
	}
	
	
}

