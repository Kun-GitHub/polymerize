package com.juiniot.modules.controller.student;

import java.sql.Timestamp;
import com.juiniot.common.web.BaseValueObject;

/**
 * @description  对应的VO对象
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-09-05 21:37:11
 */
public class StudentListVO extends BaseValueObject {
   
      
 	private Long  id; //  
     
 	private String  name; //  
     
 	private String  num; //  
     
 	private String  phone; //  
     
 	private Integer  sex; //  
     
 	private Integer  age; //  
     
 	private String  classes; //  
   
    private Timestamp createTime; // 
    private Timestamp createTime1; //  开始时间
    private Timestamp createTime2; //  结束时间
     
   
    
    
   
	 
      
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
     * @param name 
     */
     public void setName(String name){
	    this.name=name;    
     }
    /**
     * @return name 
     */
     public String getName(  ){ 
	    return this.name;    
     }
   
     
      
 	/**
     * @param num 
     */
     public void setNum(String num){
	    this.num=num;    
     }
    /**
     * @return num 
     */
     public String getNum(  ){ 
	    return this.num;    
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
     * @param sex 
     */
     public void setSex(Integer sex){
	    this.sex=sex;    
     }
    /**
     * @return sex 
     */
     public Integer getSex(  ){ 
	    return this.sex;    
     }
   
     
      
 	/**
     * @param age 
     */
     public void setAge(Integer age){
	    this.age=age;    
     }
    /**
     * @return age 
     */
     public Integer getAge(  ){ 
	    return this.age;    
     }
   
     
      
 	/**
     * @param classes 
     */
     public void setClasses(String classes){
	    this.classes=classes;    
     }
    /**
     * @return classes 
     */
     public String getClasses(  ){ 
	    return this.classes;    
     }
   
     
     
   	/**
     * @param createTime 
     */
     public void setCreateTime(Timestamp createTime){
	    this.createTime = createTime;    
     }
    /**
     * @return createTime 
     */
     public Timestamp getCreateTime(  ){ 
	    return this.createTime;    
     }
   	/**
     * @param createTime1 
     */
     public void setCreateTime1(Timestamp createTime1){
	    this.createTime1 = createTime1;    
     }
    /**
     * @return createTime1 
     */
     public Timestamp getCreateTime1(  ){ 
	    return this.createTime1;    
     }
    
     /**
     * @param createTime2 
     */
     public void setCreateTime2(Timestamp createTime2){
	    this.createTime2 = createTime2;    
     }
    /**
     * @return createTime2 
     */
     public Timestamp getCreateTime2(  ){ 
	    return this.createTime2;    
     }
   
    
    
     
    
    
}
