package com.juiniot.modules.business.loan;
 
import java.util.*;
import java.sql.Timestamp;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juiniot.common.utils.SpringUtil;
import com.juiniot.common.business.BusinessException;
import com.juiniot.common.business.BaseBusiness;
import com.juiniot.common.business.OrderItem;
import com.juiniot.modules.dao.entity.loan.LoanListEntry;
import com.juiniot.modules.dao.mapper.loan.LoanListMapper;
import com.juiniot.modules.business.loan.LoanListParam.LoanListParamKey;


/**
 *
 * @description  对应的（业务逻辑类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 *
 */
@Scope("prototype") 
@Service("loanListInfo")
public class LoanListInfo extends BaseBusiness {

	private static final long serialVersionUID = 1L;

    //实体属性
	//*****************************************************************************************************************
	private LoanListEntry loanListEntry;
          
	 
	 //构造函数
	 //*****************************************************************************************************************
	
     /**
     * 默认构造函数
     */
	 public LoanListInfo() {
	     loanListEntry = new LoanListEntry();
	 }
	
     /**
     * 构造函数
     */
	 public LoanListInfo(LoanListEntry entry) {
	     this.loanListEntry = entry;
	 }
	
	
	//属性对应的get 和 set 方法
	//*****************************************************************************************************************
	
     
        /**
         * @param id 
         */
         public void setId(Long id){
	         this.loanListEntry.setId(id);     
         }
        /**
         * @return id 
         */
         public Long getId( ){ 
	         return this.loanListEntry.getId( );   
         }
     
        /**
         * @param userId 
         */
         public void setUserId(Long userId){
	         this.loanListEntry.setUserId(userId);     
         }
        /**
         * @return userId 
         */
         public Long getUserId( ){ 
	         return this.loanListEntry.getUserId( );   
         }
     
        /**
         * @param name 
         */
         public void setName(String name){
	         this.loanListEntry.setName(name);     
         }
        /**
         * @return name 
         */
         public String getName( ){ 
	         return this.loanListEntry.getName( );   
         }
     
        /**
         * @param phone 
         */
         public void setPhone(String phone){
	         this.loanListEntry.setPhone(phone);     
         }
        /**
         * @return phone 
         */
         public String getPhone( ){ 
	         return this.loanListEntry.getPhone( );   
         }
     
        /**
         * @param sex 
         */
         public void setSex(String sex){
	         this.loanListEntry.setSex(sex);     
         }
        /**
         * @return sex 
         */
         public String getSex( ){ 
	         return this.loanListEntry.getSex( );   
         }
     
        /**
         * @param city 
         */
         public void setBankNo(String city){
	         this.loanListEntry.setBankNo(city);
         }
        /**
         * @return city 
         */
         public String getBankNo( ){
	         return this.loanListEntry.getBankNo( );
         }

	public String getNotifyUrl() {
		return this.loanListEntry.getNotifyUrl( );
	}

	public void setNotifyUrl(String notifyUrl) {
		this.loanListEntry.setNotifyUrl(notifyUrl);
	}

        /**
         */
         public void setBankLocation(String bankLocation){
	         this.loanListEntry.setBankLocation(bankLocation);
         }
        /**
         */
         public String getBankLocation( ){
	         return this.loanListEntry.getBankLocation( );
         }
     
        /**
         * @param status 0：未联系，1：无效信息，2：无需贷款，3：已放款；
         */
         public void setStatus(Integer status){
	         this.loanListEntry.setStatus(status);     
         }
        /**
         * @return status 0：未联系，1：无效信息，2：无需贷款，3：已放款；
         */
         public Integer getStatus( ){ 
	         return this.loanListEntry.getStatus( );   
         }
     
        /**
         * @param loanTime 
         */
         public void setLoanTime(Timestamp loanTime){
	         this.loanListEntry.setLoanTime(loanTime);     
         }
        /**
         * @return loanTime 
         */
         public Timestamp getLoanTime( ){ 
	         return this.loanListEntry.getLoanTime( );   
         }
     
        /**
         * @param orderNumber
         */
         public void setOrderNumber(String orderNumber){
	         this.loanListEntry.setOrderNumber(orderNumber);
         }
        /**
         * @return source 
         */
         public String getOrderNumber( ){
	         return this.loanListEntry.getOrderNumber( );
         }
     
        /**
         * @param remark 
         */
         public void setRemark(String remark){
	         this.loanListEntry.setRemark(remark);     
         }
        /**
         * @return remark 
         */
         public String getRemark( ){ 
	         return this.loanListEntry.getRemark( );   
         }

	public Double getRate() {
		return this.loanListEntry.getRate( );
	}

	public void setRate(Double rate) {
		this.loanListEntry.setRate(rate);
	}

	public String getAccount() {
		return this.loanListEntry.getAccount( );
	}

	public void setAccount(String account) {
		this.loanListEntry.setAccount(account);
	}

	public String getNotifyResult() {
		return this.loanListEntry.getNotifyResult( );
	}

	public void setNotifyResult(String notifyResult) {
		this.loanListEntry.setNotifyResult(notifyResult);
	}

	public Integer getNotifyStatus() {
		return this.loanListEntry.getNotifyStatus( );
	}

	public void setNotifyStatus(Integer notifyStatus) {
		this.loanListEntry.setNotifyStatus(notifyStatus);
	}

	public Timestamp getIssueTime() {
		return this.loanListEntry.getIssueTime( );
	}

	public void setIssueTime(Timestamp issueTime) {
		this.loanListEntry.setIssueTime(issueTime);
	}


	public Double getPrice() {
		return this.loanListEntry.getPrice();
	}

	public void setPrice(Double price) {
		this.loanListEntry.setPrice(price);
	}
	
	//实现对应具体的业务功能
	//*****************************************************************************************************************
	
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onAdd() throws BusinessException {
	     
	    LoanListMapper loanListMapper= SpringUtil.getBean("loanListMapper");
	    loanListMapper.insertLoanList(this.loanListEntry);
	}

	
	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onModify() throws BusinessException {
		
	    LoanListMapper loanListMapper= SpringUtil.getBean("loanListMapper");
		loanListMapper.updateLoanList(this.loanListEntry);
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onDelete(Long ids[]) throws BusinessException {
		LoanListMapper loanListMapper= SpringUtil.getBean("loanListMapper");
		loanListMapper.deleteLoanList(ids);
	}
	
	
	
    /**
     * 根据主键（id）返回单条记录
     * @param id
     * @return  LoanListInfo
     */
    @Transactional(propagation=Propagation.NEVER)
	public static LoanListInfo findOne(Long id) throws BusinessException{
	    LoanListMapper loanListMapper= SpringUtil.getBean("loanListMapper");
		LoanListEntry entry = loanListMapper.findOne(id);
		if (entry == null) {
			entry = new LoanListEntry();
		}
		return new LoanListInfo(entry);
	}
	 
	
	
	/**
	 * 获取总纪录行数
	 *
	 * @param keyMap   参数Map
	 * @return 总纪录行数
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static int getTotalRows(HashMap<LoanListParamKey, Object> keyMap) throws BusinessException{
		LoanListMapper loanListMapper= SpringUtil.getBean("loanListMapper");
		return loanListMapper.queryLoanListsRecordCount(toParamMap(keyMap));
	}	

	
	/**
	 * 分页查询
	 * @param startRow   开始记录的行数
	 * @param pageSize   每页显示的记录数
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<LoanListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<LoanListInfo> queryLoanLists(int startRow, int pageSize,
				HashMap<LoanListParamKey, Object> keyMap, List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<LoanListInfo> list = new ArrayList<LoanListInfo>();
		//查询结果实体
		LoanListMapper loanListMapper= SpringUtil.getBean("loanListMapper");
		List<LoanListEntry> entryList = loanListMapper.queryLoanLists(startRow, pageSize, toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (LoanListEntry entry : entryList) {
				list.add(new LoanListInfo(entry));
			}
		}
		return list;
	}
	
	/**
	 * 查询所有
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<LoanListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<LoanListInfo> queryAll(HashMap<LoanListParamKey, Object> keyMap, 
				List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<LoanListInfo> list = new ArrayList<LoanListInfo>();
		//查询结果实体
		LoanListMapper loanListMapper= SpringUtil.getBean("loanListMapper");
		List<LoanListEntry> entryList = loanListMapper.queryAll(toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (LoanListEntry entry : entryList) {
				list.add(new LoanListInfo(entry));
			}
		}
		return list;
	}
				
	/**
	 * 检查是字段值是否已存在
	 * @param columnName 字段名
	 * @param columnValue 字段值
	 * @param id 主键id值，新增时判断填null，修改时判断填主键id值
	 * @return
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static boolean isExist(String columnName, String columnValue, Long id) throws BusinessException{
		LoanListMapper loanListMapper= SpringUtil.getBean("loanListMapper");
		int result = loanListMapper.isExist(columnName, columnValue, id);
		return result > 0;
	}	
	
	//自定义方法，方法要写上注释用途
	//*****************************************************************************************************************
	
	
	
	
}

