package com.juiniot.modules.controller.recharge;

import com.juiniot.common.web.BaseValueObject;

import java.sql.Timestamp;

/**
 * @description  对应的VO对象
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
public class RechargeListVO extends BaseValueObject {
   
      
 	private Long  id; //  
     
 	private Long  userId; //  

    private Timestamp rechargeTime; // 
    private Timestamp rechargeTime1; //  开始时间
    private Timestamp rechargeTime2; //  结束时间

    private Double  price;

 	private String  userName; //

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

    public Timestamp getRechargeTime1() {
        return rechargeTime1;
    }

    public void setRechargeTime1(Timestamp rechargeTime1) {
        this.rechargeTime1 = rechargeTime1;
    }

    public Timestamp getRechargeTime2() {
        return rechargeTime2;
    }

    public void setRechargeTime2(Timestamp rechargeTime2) {
        this.rechargeTime2 = rechargeTime2;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
