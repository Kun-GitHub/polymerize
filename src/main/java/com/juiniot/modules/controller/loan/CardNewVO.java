package com.juiniot.modules.controller.loan;

import com.juiniot.common.web.BaseValueObject;

import java.sql.Timestamp;

/**
 * @description  对应的VO对象
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
public class CardNewVO {

    private String  appKey;

    private String  sgin;

 	private String  name; //
     
 	private String  account; //
     
 	private String  orderNumber; //
     
 	private String  cardNumber; //

	private Long  price; //

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getSgin() {
		return sgin;
	}

	public void setSgin(String sgin) {
		this.sgin = sgin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
