package com.juiniot.modules.business.feedback;
 
import java.util.*;

/**
 *
 * @description  对应的（查询参数类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-26 20:52:56
 *
 */
public class FeedbackListParam {

	 // 参数key map
	 private HashMap<FeedbackListParamKey, Object> keyMap = new HashMap<>();

	
	/**
	 * FeedbackList参数key枚举类型
	 */
	public enum FeedbackListParamKey {
	    id, studentId, createTime, createTime1, createTime2, learningContent, learningAchievement, clear, fluency, creativity, logicalThinking, homeworkCompleaon, concentration, expression, reaction, interaction, ruleConsciousness, concernForOthers, leaderShip, corporation, commentsOnStudent, todayAchievement, name, num, classes, phone
    }
    
    //构造函数，初始化参数默认值
    public FeedbackListParam() {
      
     	keyMap.put(FeedbackListParamKey.id, null);
	  
     	keyMap.put(FeedbackListParamKey.studentId, null);
	  
     	keyMap.put(FeedbackListParamKey.createTime1, null); keyMap.put(FeedbackListParamKey.createTime2, null);
	  
     	keyMap.put(FeedbackListParamKey.learningContent, null);
	  
     	keyMap.put(FeedbackListParamKey.learningAchievement, null);
	  
     	keyMap.put(FeedbackListParamKey.clear, null);
	  
     	keyMap.put(FeedbackListParamKey.fluency, null);
	  
     	keyMap.put(FeedbackListParamKey.creativity, null);
	  
     	keyMap.put(FeedbackListParamKey.logicalThinking, null);
	  
     	keyMap.put(FeedbackListParamKey.homeworkCompleaon, null);
	  
     	keyMap.put(FeedbackListParamKey.concentration, null);
	  
     	keyMap.put(FeedbackListParamKey.expression, null);
	  
     	keyMap.put(FeedbackListParamKey.reaction, null);
	  
     	keyMap.put(FeedbackListParamKey.interaction, null);
	  
     	keyMap.put(FeedbackListParamKey.ruleConsciousness, null);
	  
     	keyMap.put(FeedbackListParamKey.concernForOthers, null);
	  
     	keyMap.put(FeedbackListParamKey.leaderShip, null);
	  
     	keyMap.put(FeedbackListParamKey.corporation, null);
	  
     	keyMap.put(FeedbackListParamKey.commentsOnStudent, null);
	  
     	keyMap.put(FeedbackListParamKey.todayAchievement, null);
	 
	           
	    keyMap.put(FeedbackListParamKey.name, null);
	           
	    keyMap.put(FeedbackListParamKey.num, null);

		keyMap.put(FeedbackListParamKey.classes, null);

		keyMap.put(FeedbackListParamKey.phone, null);
	 
	}
	
	
	/**
	 * 设置查询参数
	 * @param key 键
	 * @param value 值
	 */
	public void putValue(FeedbackListParamKey key, Object value) {
		keyMap.put(key, value);
	}

	
	/**
	 * 获取参数key map
	 * @return HashMap
	 */
	public HashMap<FeedbackListParamKey, Object> getKeyMap() {
		return keyMap;
	}
	
	
}

