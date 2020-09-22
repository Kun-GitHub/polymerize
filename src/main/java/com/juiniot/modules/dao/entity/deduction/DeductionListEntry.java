package com.juiniot.modules.dao.entity.deduction;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description  对应的实体
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
public class DeductionListEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long  id; //
    private Long  userId; //
    private Timestamp  deductionTime; //
    private Double price;

    private String  userName; //

    //默认空构造函数
	public DeductionListEntry(){
	
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

    public Timestamp getDeductionTime() {
        return deductionTime;
    }

    public void setDeductionTime(Timestamp deductionTime) {
        this.deductionTime = deductionTime;
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
