package com.juiniot.modules.business.student;
 
import java.util.*;

/**
 *
 * @description  对应的（查询参数类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-09-05 21:37:11
 *
 */
public class StudentListParam {

	 // 参数key map
	 private HashMap<StudentListParamKey, Object> keyMap = new HashMap<>();

	
	/**
	 * StudentList参数key枚举类型
	 */
	public enum StudentListParamKey {
	    id, name, num, phone, sex, age, classes, createTime, createTime1, createTime2
    }
    
    //构造函数，初始化参数默认值
    public StudentListParam() {
      
     	keyMap.put(StudentListParamKey.id, null);
	  
     	keyMap.put(StudentListParamKey.name, null);
	  
     	keyMap.put(StudentListParamKey.num, null);
	  
     	keyMap.put(StudentListParamKey.phone, null);
	  
     	keyMap.put(StudentListParamKey.sex, null);
	  
     	keyMap.put(StudentListParamKey.age, null);
	  
     	keyMap.put(StudentListParamKey.classes, null);
	  
     	keyMap.put(StudentListParamKey.createTime1, null); keyMap.put(StudentListParamKey.createTime2, null);
	 
	 
	}
	
	
	/**
	 * 设置查询参数
	 * @param key 键
	 * @param value 值
	 */
	public void putValue(StudentListParamKey key, Object value) {
		keyMap.put(key, value);
	}

	
	/**
	 * 获取参数key map
	 * @return HashMap
	 */
	public HashMap<StudentListParamKey, Object> getKeyMap() {
		return keyMap;
	}
	
	
}

