package com.juiniot.modules.dao.entity.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description  对应的实体
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 15:52:08
 */
public class UserListEntry implements Serializable {
   
    private static final long serialVersionUID = 1L;
     
    private Long  id; // 
    private Long  parentId; // 
    private String  account; // 
    private String  mobile; // 
    private String  pwd; // 
    private String  name; // 
    private Double  money; // 
    private String  treeCode; //
    private Double  surplus; //
    private Timestamp  createTime; //
    
     
    private String  parentName; //
     
    //默认空构造函数
	public UserListEntry(){
	
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
     * @param parentId 
     */
    public void setParentId(Long parentId){
	    this.parentId=parentId;    
    }
    /**
     * @return parentId 
     */
    public Long getParentId(  ){ 
	    return this.parentId;    
    }
     
    /**
     * @param account 
     */
    public void setAccount(String account){
	    this.account=account;    
    }
    /**
     * @return account 
     */
    public String getAccount(  ){ 
	    return this.account;    
    }
     
    /**
     * @param mobile 
     */
    public void setMobile(String mobile){
	    this.mobile=mobile;    
    }
    /**
     * @return mobile 
     */
    public String getMobile(  ){ 
	    return this.mobile;    
    }
     
    /**
     * @param pwd 
     */
    public void setPwd(String pwd){
	    this.pwd=pwd;    
    }
    /**
     * @return pwd 
     */
    public String getPwd(  ){ 
	    return this.pwd;    
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
     * @param money 
     */
    public void setMoney(Double money){
	    this.money=money;    
    }
    /**
     * @return money 
     */
    public Double getMoney(  ){ 
	    return this.money;    
    }
     
    /**
     * @param treeCode 
     */
    public void setTreeCode(String treeCode){
	    this.treeCode=treeCode;    
    }
    /**
     * @return treeCode 
     */
    public String getTreeCode(  ){ 
	    return this.treeCode;    
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }

    /**
     * @param createTime 
     */
    public void setCreateTime(Timestamp createTime){
	    this.createTime=createTime;    
    }
    /**
     * @return createTime 
     */
    public Timestamp getCreateTime(  ){ 
	    return this.createTime;    
    }
    
    
      
    /**
     * @param parentName 
     */
	public void setParentName(String parentName){
	   	this.parentName=parentName;    
    }
    /**
     * @return parentName 
     */
    public String getParentName(  ){ 
	    return this.parentName;    
    }
    
    
    
}
