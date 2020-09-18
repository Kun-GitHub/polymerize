package com.juiniot.modules.controller.sms;

import java.sql.Timestamp;
import com.juiniot.common.web.BaseValueObject;

/**
 * @description 短信记录 对应的VO对象
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-12 15:16:39
 */
public class SmsListVO extends BaseValueObject {
   
      
 	private Long  id; //  
     
 	private String  phone; //  
   
    private Timestamp lastTime; // 
    private Timestamp lastTime1; //  开始时间
    private Timestamp lastTime2; //  结束时间
     
     
 	private Long  nums; //
     
 	private String  sms; //  
     
 	private String  code; //  

    private String  source; //
    
   
	 
      
 	/**
     * @param id 
     */
     public void setId(Long id){
	    this.id=id;    
     }
    /**
     * @return id 
     */
     public Long getId(  ){ 
	    return this.id;    
     }
   
     
      
 	/**
     * @param phone 
     */
     public void setPhone(String phone){
	    this.phone=phone;    
     }
    /**
     * @return phone 
     */
     public String getPhone(  ){ 
	    return this.phone;    
     }
   
     
     
   	/**
     * @param lastTime 
     */
     public void setLastTime(Timestamp lastTime){
	    this.lastTime = lastTime;    
     }
    /**
     * @return lastTime 
     */
     public Timestamp getLastTime(  ){ 
	    return this.lastTime;    
     }
   	/**
     * @param lastTime1 
     */
     public void setLastTime1(Timestamp lastTime1){
	    this.lastTime1 = lastTime1;    
     }
    /**
     * @return lastTime1 
     */
     public Timestamp getLastTime1(  ){ 
	    return this.lastTime1;    
     }
    
     /**
     * @param lastTime2 
     */
     public void setLastTime2(Timestamp lastTime2){
	    this.lastTime2 = lastTime2;    
     }
    /**
     * @return lastTime2 
     */
     public Timestamp getLastTime2(  ){ 
	    return this.lastTime2;    
     }
   
     
      
 	/**
     * @param nums 
     */
     public void setNums(Long nums){
	    this.nums=nums;    
     }
    /**
     * @return nums 
     */
     public Long getNums(  ){
	    return this.nums;    
     }
   
     
      
 	/**
     * @param sms 
     */
     public void setSms(String sms){
	    this.sms=sms;    
     }
    /**
     * @return sms 
     */
     public String getSms(  ){ 
	    return this.sms;    
     }
   
     
      
 	/**
     * @param code 
     */
     public void setCode(String code){
	    this.code=code;    
     }
    /**
     * @return code 
     */
     public String getCode(  ){ 
	    return this.code;    
     }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
