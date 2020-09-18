package com.juiniot.common.web.preview;

import java.util.Date;

/**
 * 用户身份权限
 * @author ZHIFEN
 *
 */
public class UserIdentity {

	private Long userId; //商户账号ID
	private String account; //账号
    private String userName; //账号名称
    private boolean isSuperAdmin; //是否超级管理员
    private long userType;
    private Date loginTime;    //记录登录时间

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public long getUserType() {
        return userType;
    }

    public void setUserType(long userType) {
        this.userType = userType;
    }

    public Date getLoginTime(){ return loginTime;}
    public void setLoginTime(Date loginTime){ this.loginTime=loginTime; }
}
