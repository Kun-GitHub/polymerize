package com.juiniot.modules.business.deduction;

import com.juiniot.common.business.BaseBusiness;
import com.juiniot.common.business.BusinessException;
import com.juiniot.common.business.OrderItem;
import com.juiniot.common.utils.SpringUtil;
import com.juiniot.modules.business.deduction.DeductionListParam.DeductionListParamKey;
import com.juiniot.modules.dao.entity.deduction.DeductionListEntry;
import com.juiniot.modules.dao.mapper.deduction.DeductionListMapper;
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
@Service("deductionListInfo")
public class DeductionListInfo extends BaseBusiness {

	private static final long serialVersionUID = 1L;

    //实体属性
	//*****************************************************************************************************************
	private DeductionListEntry deductionListEntry;
          
	 
	 //构造函数
	 //*****************************************************************************************************************
	
     /**
     * 默认构造函数
     */
	 public DeductionListInfo() {
	     deductionListEntry = new DeductionListEntry();
	 }
	
     /**
     * 构造函数
     */
	 public DeductionListInfo(DeductionListEntry entry) {
	     this.deductionListEntry = entry;
	 }
	
	
	//属性对应的get 和 set 方法
	//*****************************************************************************************************************
	
     
        /**
         * @param id 
         */
         public void setId(Long id){
	         this.deductionListEntry.setId(id);     
         }
        /**
         * @return id 
         */
         public Long getId( ){ 
	         return this.deductionListEntry.getId( );   
         }
     
        /**
         * @param userId 
         */
         public void setUserId(Long userId){
	         this.deductionListEntry.setUserId(userId);     
         }
        /**
         * @return userId 
         */
         public Long getUserId( ){ 
	         return this.deductionListEntry.getUserId( );   
         }

        /**
         * @param userName 
         */
         private void setUserName(String userName){
	         this.deductionListEntry.setUserName(userName);
         }
         
        /**
          * @return userName 
         */
         public String getUserName( ){ 
	         return this.deductionListEntry.getUserName( );     
         }

	public Double getPrice() {
		return this.deductionListEntry.getPrice();
	}

	public void setPrice(Double price) {
		this.deductionListEntry.setPrice(price);
	}

	/**
	 * @param deductionTime
	 */
	public void setDeductionTime(Timestamp deductionTime){
		this.deductionListEntry.setDeductionTime(deductionTime);
	}
	/**
	 * @return deductionTime
	 */
	public Timestamp getDeductionTime( ){
		return this.deductionListEntry.getDeductionTime( );
	}
	
	//实现对应具体的业务功能
	//*****************************************************************************************************************
	
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onAdd() throws BusinessException {
	     
	    DeductionListMapper deductionListMapper= SpringUtil.getBean("deductionListMapper");
	    deductionListMapper.insertDeductionList(this.deductionListEntry);
	}

	
	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onModify() throws BusinessException {
		
	    DeductionListMapper deductionListMapper= SpringUtil.getBean("deductionListMapper");
		deductionListMapper.updateDeductionList(this.deductionListEntry);
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onDelete(Long ids[]) throws BusinessException {
		DeductionListMapper deductionListMapper= SpringUtil.getBean("deductionListMapper");
		deductionListMapper.deleteDeductionList(ids);
	}
	
	
	
    /**
     * 根据主键（id）返回单条记录
     * @param id
     * @return  DeductionListInfo
     */
    @Transactional(propagation=Propagation.NEVER)
	public static DeductionListInfo findOne(Long id) throws BusinessException{
	    DeductionListMapper deductionListMapper= SpringUtil.getBean("deductionListMapper");
		DeductionListEntry entry = deductionListMapper.findOne(id);
		if (entry == null) {
			entry = new DeductionListEntry();
		}
		return new DeductionListInfo(entry);
	}
	 
	
	
	/**
	 * 获取总纪录行数
	 *
	 * @param keyMap   参数Map
	 * @return 总纪录行数
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static int getTotalRows(HashMap<DeductionListParamKey, Object> keyMap) throws BusinessException{
		DeductionListMapper deductionListMapper= SpringUtil.getBean("deductionListMapper");
		return deductionListMapper.queryDeductionListsRecordCount(toParamMap(keyMap));
	}	

	
	/**
	 * 分页查询
	 * @param startRow   开始记录的行数
	 * @param pageSize   每页显示的记录数
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<DeductionListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<DeductionListInfo> queryDeductionLists(int startRow, int pageSize,
                                                             HashMap<DeductionListParamKey, Object> keyMap, List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<DeductionListInfo> list = new ArrayList<DeductionListInfo>();
		//查询结果实体
		DeductionListMapper deductionListMapper= SpringUtil.getBean("deductionListMapper");
		List<DeductionListEntry> entryList = deductionListMapper.queryDeductionLists(startRow, pageSize, toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (DeductionListEntry entry : entryList) {
				list.add(new DeductionListInfo(entry));
			}
		}
		return list;
	}
	
	/**
	 * 查询所有
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<DeductionListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<DeductionListInfo> queryAll(HashMap<DeductionListParamKey, Object> keyMap,
                                                   List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<DeductionListInfo> list = new ArrayList<DeductionListInfo>();
		//查询结果实体
		DeductionListMapper deductionListMapper= SpringUtil.getBean("deductionListMapper");
		List<DeductionListEntry> entryList = deductionListMapper.queryAll(toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (DeductionListEntry entry : entryList) {
				list.add(new DeductionListInfo(entry));
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
		DeductionListMapper deductionListMapper= SpringUtil.getBean("deductionListMapper");
		int result = deductionListMapper.isExist(columnName, columnValue, id);
		return result > 0;
	}	
	
	//自定义方法，方法要写上注释用途
	//*****************************************************************************************************************
	
	
	
	
}

