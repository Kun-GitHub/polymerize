package com.juiniot.modules.business.recharge;

import com.juiniot.common.business.BaseBusiness;
import com.juiniot.common.business.BusinessException;
import com.juiniot.common.business.OrderItem;
import com.juiniot.common.utils.SpringUtil;
import com.juiniot.modules.business.recharge.RechargeListParam.RechargeListParamKey;
import com.juiniot.modules.dao.entity.recharge.RechargeListEntry;
import com.juiniot.modules.dao.mapper.recharge.RechargeListMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @description  对应的（业务逻辑类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 *
 */
@Scope("prototype") 
@Service("rechargeListInfo")
public class RechargeListInfo extends BaseBusiness {

	private static final long serialVersionUID = 1L;

    //实体属性
	//*****************************************************************************************************************
	private RechargeListEntry rechargeListEntry;
          
	 
	 //构造函数
	 //*****************************************************************************************************************
	
     /**
     * 默认构造函数
     */
	 public RechargeListInfo() {
	     rechargeListEntry = new RechargeListEntry();
	 }
	
     /**
     * 构造函数
     */
	 public RechargeListInfo(RechargeListEntry entry) {
	     this.rechargeListEntry = entry;
	 }
	
	
	//属性对应的get 和 set 方法
	//*****************************************************************************************************************
	
     
        /**
         * @param id 
         */
         public void setId(Long id){
	         this.rechargeListEntry.setId(id);     
         }
        /**
         * @return id 
         */
         public Long getId( ){ 
	         return this.rechargeListEntry.getId( );   
         }
     
        /**
         * @param userId 
         */
         public void setUserId(Long userId){
	         this.rechargeListEntry.setUserId(userId);     
         }
        /**
         * @return userId 
         */
         public Long getUserId( ){ 
	         return this.rechargeListEntry.getUserId( );   
         }

        /**
         * @param userName 
         */
         private void setUserName(String userName){
	         this.rechargeListEntry.setUserName(userName);
         }
         
        /**
          * @return userName 
         */
         public String getUserName( ){ 
	         return this.rechargeListEntry.getUserName( );     
         }

	public Double getPrice() {
		return this.rechargeListEntry.getPrice();
	}

	public void setPrice(Double price) {
		this.rechargeListEntry.setPrice(price);
	}

	/**
	 * @param rechargeTime
	 */
	public void setRechargeTime(Timestamp rechargeTime){
		this.rechargeListEntry.setRechargeTime(rechargeTime);
	}
	/**
	 * @return rechargeTime
	 */
	public Timestamp getRechargeTime( ){
		return this.rechargeListEntry.getRechargeTime( );
	}
	
	//实现对应具体的业务功能
	//*****************************************************************************************************************
	
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onAdd() throws BusinessException {
	     
	    RechargeListMapper rechargeListMapper= SpringUtil.getBean("rechargeListMapper");
	    rechargeListMapper.insertRechargeList(this.rechargeListEntry);
	}

	
	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onModify() throws BusinessException {
		
	    RechargeListMapper rechargeListMapper= SpringUtil.getBean("rechargeListMapper");
		rechargeListMapper.updateRechargeList(this.rechargeListEntry);
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onDelete(Long ids[]) throws BusinessException {
		RechargeListMapper rechargeListMapper= SpringUtil.getBean("rechargeListMapper");
		rechargeListMapper.deleteRechargeList(ids);
	}
	
	
	
    /**
     * 根据主键（id）返回单条记录
     * @param id
     * @return  RechargeListInfo
     */
    @Transactional(propagation=Propagation.NEVER)
	public static RechargeListInfo findOne(Long id) throws BusinessException{
	    RechargeListMapper rechargeListMapper= SpringUtil.getBean("rechargeListMapper");
		RechargeListEntry entry = rechargeListMapper.findOne(id);
		if (entry == null) {
			entry = new RechargeListEntry();
		}
		return new RechargeListInfo(entry);
	}
	 
	
	
	/**
	 * 获取总纪录行数
	 *
	 * @param keyMap   参数Map
	 * @return 总纪录行数
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static int getTotalRows(HashMap<RechargeListParamKey, Object> keyMap) throws BusinessException{
		RechargeListMapper rechargeListMapper= SpringUtil.getBean("rechargeListMapper");
		return rechargeListMapper.queryRechargeListsRecordCount(toParamMap(keyMap));
	}	

	
	/**
	 * 分页查询
	 * @param startRow   开始记录的行数
	 * @param pageSize   每页显示的记录数
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<RechargeListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<RechargeListInfo> queryRechargeLists(int startRow, int pageSize,
														HashMap<RechargeListParamKey, Object> keyMap, List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<RechargeListInfo> list = new ArrayList<RechargeListInfo>();
		//查询结果实体
		RechargeListMapper rechargeListMapper= SpringUtil.getBean("rechargeListMapper");
		List<RechargeListEntry> entryList = rechargeListMapper.queryRechargeLists(startRow, pageSize, toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (RechargeListEntry entry : entryList) {
				list.add(new RechargeListInfo(entry));
			}
		}
		return list;
	}
	
	/**
	 * 查询所有
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<RechargeListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<RechargeListInfo> queryAll(HashMap<RechargeListParamKey, Object> keyMap,
												  List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<RechargeListInfo> list = new ArrayList<RechargeListInfo>();
		//查询结果实体
		RechargeListMapper rechargeListMapper= SpringUtil.getBean("rechargeListMapper");
		List<RechargeListEntry> entryList = rechargeListMapper.queryAll(toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (RechargeListEntry entry : entryList) {
				list.add(new RechargeListInfo(entry));
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
		RechargeListMapper rechargeListMapper= SpringUtil.getBean("rechargeListMapper");
		int result = rechargeListMapper.isExist(columnName, columnValue, id);
		return result > 0;
	}	
	
	//自定义方法，方法要写上注释用途
	//*****************************************************************************************************************
	
	
	
	
}

