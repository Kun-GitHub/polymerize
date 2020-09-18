package com.juiniot.modules.dao.entity.sms;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description 短信记录 对应的实体
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-12 15:16:39
 */
public class SmsListEntry implements Serializable {
   
    private static final long serialVersionUID = 1L;
     
    private Long  id; // 
    private String  phone; // 
    private Timestamp  lastTime; // 
    private Long  nums; //
    private String  sms; // 
    private String  code; //
    private String  source; //
    
    
     
    //默认空构造函数
	public SmsListEntry(){
	
	}
	
	
	
	
	 
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
	    this.lastTime=lastTime;    
    }
    /**
     * @return lastTime 
     */
    public Timestamp getLastTime(  ){ 
	    return this.lastTime;    
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
