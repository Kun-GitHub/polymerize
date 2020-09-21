package com.juiniot.modules.business.user;
 
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
import com.juiniot.modules.dao.entity.user.UserListEntry;
import com.juiniot.modules.dao.mapper.user.UserListMapper;
import com.juiniot.modules.business.user.UserListParam.UserListParamKey;


/**
 *
 * @description  对应的（业务逻辑类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 15:52:09
 *
 */
@Scope("prototype") 
@Service("userListInfo")
public class UserListInfo extends BaseBusiness {

	private static final long serialVersionUID = 1L;

    //实体属性
	//*****************************************************************************************************************
	private UserListEntry userListEntry;
          
	 
	 //构造函数
	 //*****************************************************************************************************************
	
     /**
     * 默认构造函数
     */
	 public UserListInfo() {
	     userListEntry = new UserListEntry();
	 }
	
     /**
     * 构造函数
     */
	 public UserListInfo(UserListEntry entry) {
	     this.userListEntry = entry;
	 }
	
	
	//属性对应的get 和 set 方法
	//*****************************************************************************************************************
	
     
        /**
         * @param id 
         */
         public void setId(Long id){
	         this.userListEntry.setId(id);     
         }
        /**
         * @return id 
         */
         public Long getId( ){ 
	         return this.userListEntry.getId( );   
         }
     
        /**
         * @param parentId 
         */
         public void setParentId(Long parentId){
	         this.userListEntry.setParentId(parentId);     
         }
        /**
         * @return parentId 
         */
         public Long getParentId( ){ 
	         return this.userListEntry.getParentId( );   
         }
     
        /**
         * @param account 
         */
         public void setAccount(String account){
	         this.userListEntry.setAccount(account);     
         }
        /**
         * @return account 
         */
         public String getAccount( ){ 
	         return this.userListEntry.getAccount( );   
         }
     
        /**
         * @param mobile 
         */
         public void setMobile(String mobile){
	         this.userListEntry.setMobile(mobile);     
         }
        /**
         * @return mobile 
         */
         public String getMobile( ){ 
	         return this.userListEntry.getMobile( );   
         }
     
        /**
         * @param pwd 
         */
         public void setPwd(String pwd){
	         this.userListEntry.setPwd(pwd);     
         }
        /**
         * @return pwd 
         */
         public String getPwd( ){ 
	         return this.userListEntry.getPwd( );   
         }
     
        /**
         * @param name 
         */
         public void setName(String name){
	         this.userListEntry.setName(name);     
         }
        /**
         * @return name 
         */
         public String getName( ){ 
	         return this.userListEntry.getName( );   
         }
     
        /**
         * @param money 
         */
         public void setMoney(Double money){
	         this.userListEntry.setMoney(money);     
         }
        /**
         * @return money 
         */
         public Double getMoney( ){ 
	         return this.userListEntry.getMoney( );   
         }
     
        /**
         * @param treeCode 
         */
         public void setTreeCode(String treeCode){
	         this.userListEntry.setTreeCode(treeCode);     
         }
        /**
         * @return treeCode 
         */
         public String getTreeCode( ){ 
	         return this.userListEntry.getTreeCode( );   
         }
     
	public Double getSurplus() {
		return this.userListEntry.getSurplus();
	}

	public void setSurplus(Double surplus) {
		this.userListEntry.setSurplus(surplus);
	}

        /**
         * @param createTime 
         */
         public void setCreateTime(Timestamp createTime){
	         this.userListEntry.setCreateTime(createTime);     
         }
        /**
         * @return createTime 
         */
         public Timestamp getCreateTime( ){ 
	         return this.userListEntry.getCreateTime( );   
         }
    
    
               
        /**
         * @param parentName 
         */
         private void setParentName(String parentName){
	         this.userListEntry.setParentName(parentName);
         }
         
        /**
          * @return parentName 
         */
         public String getParentName( ){ 
	         return this.userListEntry.getParentName( );     
         }
    
    
	
	//实现对应具体的业务功能
	//*****************************************************************************************************************
	
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onAdd() throws BusinessException {
	     
	    UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
	    userListMapper.insertUserList(this.userListEntry);
	}

	
	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onModify() throws BusinessException {
		
	    UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
		userListMapper.updateUserList(this.userListEntry);
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onDelete(Long ids[]) throws BusinessException {
		UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
		userListMapper.deleteUserList(ids);
	}
	
	
	
    /**
     * 根据主键（id）返回单条记录
     * @param id
     * @return  UserListInfo
     */
    @Transactional(propagation=Propagation.NEVER)
	public static UserListInfo findOne(Long id) throws BusinessException{
	    UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
		UserListEntry entry = userListMapper.findOne(id);
		if (entry == null) {
			entry = new UserListEntry();
		}
		return new UserListInfo(entry);
	}
	 
	
	
	/**
	 * 获取总纪录行数
	 *
	 * @param keyMap   参数Map
	 * @return 总纪录行数
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static int getTotalRows(HashMap<UserListParamKey, Object> keyMap) throws BusinessException{
		UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
		return userListMapper.queryUserListsRecordCount(toParamMap(keyMap));
	}	

	
	/**
	 * 分页查询
	 * @param startRow   开始记录的行数
	 * @param pageSize   每页显示的记录数
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<UserListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<UserListInfo> queryUserLists(int startRow, int pageSize,
				HashMap<UserListParamKey, Object> keyMap, List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<UserListInfo> list = new ArrayList<UserListInfo>();
		//查询结果实体
		UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
		List<UserListEntry> entryList = userListMapper.queryUserLists(startRow, pageSize, toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (UserListEntry entry : entryList) {
				list.add(new UserListInfo(entry));
			}
		}
		return list;
	}
	
	/**
	 * 查询所有
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<UserListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<UserListInfo> queryAll(HashMap<UserListParamKey, Object> keyMap, 
				List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<UserListInfo> list = new ArrayList<UserListInfo>();
		//查询结果实体
		UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
		List<UserListEntry> entryList = userListMapper.queryAll(toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (UserListEntry entry : entryList) {
				list.add(new UserListInfo(entry));
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
		UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
		int result = userListMapper.isExist(columnName, columnValue, id);
		return result > 0;
	}	
	
	//自定义方法，方法要写上注释用途
	//*****************************************************************************************************************

	@Transactional(propagation=Propagation.NEVER)
	public List<HashMap<String,Object>>selectUserName(String treeCode){
		UserListMapper userListMapper= SpringUtil.getBean("userListMapper");
		return userListMapper.selectUserName(treeCode);
	}

}

