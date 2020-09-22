package com.juiniot.modules.controller.deduction;

import com.juiniot.common.web.BaseValueObject;

import java.sql.Timestamp;

/**
 * @description  对应的VO对象
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
public class DeductionListVO extends BaseValueObject {
   
      
 	private Long  id; //  
     
 	private Long  userId; //  

    private Timestamp deductionTime; // 
    private Timestamp deductionTime1; //  开始时间
    private Timestamp deductionTime2; //  结束时间

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

    public Timestamp getDeductionTime() {
        return deductionTime;
    }

    public void setDeductionTime(Timestamp deductionTime) {
        this.deductionTime = deductionTime;
    }

    public Timestamp getDeductionTime1() {
        return deductionTime1;
    }

    public void setDeductionTime1(Timestamp deductionTime1) {
        this.deductionTime1 = deductionTime1;
    }

    public Timestamp getDeductionTime2() {
        return deductionTime2;
    }

    public void setDeductionTime2(Timestamp deductionTime2) {
        this.deductionTime2 = deductionTime2;
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
