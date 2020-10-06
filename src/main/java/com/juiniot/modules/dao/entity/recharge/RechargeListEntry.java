package com.juiniot.modules.dao.entity.recharge;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description  对应的实体
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
public class RechargeListEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long  id; //
    private Long  userId; //
    private Timestamp  rechargeTime; //
    private Double price;

    private String account; //

    //默认空构造函数
	public RechargeListEntry(){
	
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Timestamp rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
