package com.juiniot.modules.dao.entity.loan;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description  对应的实体
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
public class LoanListEntry implements Serializable {
   
    private static final long serialVersionUID = 1L;
     
    private Long  id; // 
    private Long  userId; // 
    private String  name; // 
    private String  phone; // 
    private String  sex; // 
    private String bankNo; //
    private String bankLocation; //贷款额度
    private Integer  status; //0：未联系，1：无效信息，2：无需贷款，3：已放款； 
    private Timestamp  loanTime; // 
    private String orderNumber; //
    private String  remark; //
    private String  notifyUrl; //
    private Double price;
    private String notifyResult;
    private Integer  notifyStatus;
    private Timestamp issueTime;

    private String  account; //
    private String  treeCode; //
    private Double rate; //

    //默认空构造函数
	public LoanListEntry(){
	
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
     * @param bankLocation 贷款额度
     */
    public void setBankLocation(String bankLocation){
	    this.bankLocation = bankLocation;
    }
    /**
     * @return quota 贷款额度
     */
    public String getBankLocation(  ){
	    return this.bankLocation;
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
	    this.loanTime=loanTime;    
    }
    /**
     * @return loanTime 
     */
    public Timestamp getLoanTime(  ){ 
	    return this.loanTime;    
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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

    public String getNotifyResult() {
        return notifyResult;
    }

    public void setNotifyResult(String notifyResult) {
        this.notifyResult = notifyResult;
    }

    public Integer getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Integer notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public Timestamp getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Timestamp issueTime) {
        this.issueTime = issueTime;
    }
}
