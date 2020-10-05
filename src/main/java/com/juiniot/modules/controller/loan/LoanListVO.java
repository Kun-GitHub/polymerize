package com.juiniot.modules.controller.loan;

import java.sql.Timestamp;
import com.juiniot.common.web.BaseValueObject;

/**
 * @description  对应的VO对象
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
public class LoanListVO extends BaseValueObject {
   
      
 	private Long  id; //  
     
 	private Long  userId; //  
     
 	private String  name; //  
     
 	private String  phone; //  
     
 	private String  sex; //  
     
 	private String bankNo; //
     
 	private String  quota; //贷款额度
     
 	private Integer  status; //0：未联系，1：无效信息，2：无需贷款，3：已放款；  
   
    private Timestamp loanTime; // 
    private Timestamp loanTime1; //  开始时间
    private Timestamp loanTime2; //  结束时间

    private String  notifyUrl; //

    private Double  price;

 	private String orderNumber; //
     
 	private String  remark; //  
   


 	private String  account; //
    private String  treeCode; //
    private Double rate;
   
	 
      
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
     * @param userId 
     */
     public void setUserId(Long userId){
	    this.userId=userId;    
     }
    /**
     * @return userId 
     */
     public Long getUserId(  ){ 
	    return this.userId;    
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
     public void setSex(String sex){
	    this.sex=sex;    
     }
    /**
     * @return sex 
     */
     public String getSex(  ){ 
	    return this.sex;    
     }
   
     
      
 	/**
     * @param bankNo
     */
     public void setBankNo(String bankNo){
	    this.bankNo = bankNo;
     }
    /**
     * @return city 
     */
     public String getBankNo(  ){
	    return this.bankNo;
     }
   
     
      
 	/**
     * @param quota 贷款额度
     */
     public void setQuota(String quota){
	    this.quota=quota;    
     }
    /**
     * @return quota 贷款额度
     */
     public String getQuota(  ){
	    return this.quota;    
     }
   
     
      
 	/**
     * @param status 0：未联系，1：无效信息，2：无需贷款，3：已放款；
     */
     public void setStatus(Integer status){
	    this.status=status;    
     }
    /**
     * @return status 0：未联系，1：无效信息，2：无需贷款，3：已放款；
     */
     public Integer getStatus(  ){ 
	    return this.status;    
     }
   
     
     
   	/**
     * @param loanTime 
     */
     public void setLoanTime(Timestamp loanTime){
	    this.loanTime = loanTime;    
     }
    /**
     * @return loanTime 
     */
     public Timestamp getLoanTime(  ){ 
	    return this.loanTime;    
     }
   	/**
     * @param loanTime1 
     */
     public void setLoanTime1(Timestamp loanTime1){
	    this.loanTime1 = loanTime1;    
     }
    /**
     * @return loanTime1 
     */
     public Timestamp getLoanTime1(  ){ 
	    return this.loanTime1;    
     }
    
     /**
     * @param loanTime2 
     */
     public void setLoanTime2(Timestamp loanTime2){
	    this.loanTime2 = loanTime2;    
     }
    /**
     * @return loanTime2 
     */
     public Timestamp getLoanTime2(  ){ 
	    return this.loanTime2;    
     }
   
     
      
 	/**
     * @param orderNumber
     */
     public void setOrderNumber(String orderNumber){
	    this.orderNumber = orderNumber;
     }
    /**
     * @return source 
     */
     public String getOrderNumber(  ){
	    return this.orderNumber;
     }
   
     
      
 	/**
     * @param remark 
     */
     public void setRemark(String remark){
	    this.remark=remark;    
     }
    /**
     * @return remark 
     */
     public String getRemark(  ){ 
	    return this.remark;    
     }


    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
