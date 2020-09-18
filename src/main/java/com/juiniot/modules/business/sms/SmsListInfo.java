package com.juiniot.modules.business.sms;
 
import java.util.*;
import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juiniot.common.utils.SpringUtil;
import com.juiniot.common.business.BusinessException;
import com.juiniot.common.business.BaseBusiness;
import com.juiniot.common.business.OrderItem;
import com.juiniot.modules.dao.entity.sms.SmsListEntry;
import com.juiniot.modules.dao.mapper.sms.SmsListMapper;
import com.juiniot.modules.business.sms.SmsListParam.SmsListParamKey;


/**
 *
 * @description 短信记录 对应的（业务逻辑类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-12 15:16:39
 *
 */
@Scope("prototype") 
@Service("smsListInfo")
public class SmsListInfo extends BaseBusiness {

	private static final long serialVersionUID = 1L;

    //实体属性
	//*****************************************************************************************************************
	private SmsListEntry smsListEntry;
          
	 
	 //构造函数
	 //*****************************************************************************************************************
	
     /**
     * 默认构造函数
     */
	 public SmsListInfo() {
	     smsListEntry = new SmsListEntry();
	 }
	
     /**
     * 构造函数
     */
	 public SmsListInfo(SmsListEntry entry) {
	     this.smsListEntry = entry;
	 }
	
	
	//属性对应的get 和 set 方法
	//*****************************************************************************************************************
	
     
        /**
         * @param id 
         */
         public void setId(Long id){
	         this.smsListEntry.setId(id);     
         }
        /**
         * @return id 
         */
         public Long getId( ){ 
	         return this.smsListEntry.getId( );   
         }
     
        /**
         * @param phone 
         */
         public void setPhone(String phone){
	         this.smsListEntry.setPhone(phone);     
         }
        /**
         * @return phone 
         */
         public String getPhone( ){ 
	         return this.smsListEntry.getPhone( );   
         }
     
        /**
         * @param lastTime 
         */
         public void setLastTime(Timestamp lastTime){
	         this.smsListEntry.setLastTime(lastTime);     
         }
        /**
         * @return lastTime 
         */
         public Timestamp getLastTime( ){ 
	         return this.smsListEntry.getLastTime( );   
         }
     
        /**
         * @param nums 
         */
         public void setNums(Long nums){
	         this.smsListEntry.setNums(nums);     
         }
        /**
         * @return nums 
         */
         public Long getNums( ){
	         return this.smsListEntry.getNums( );   
         }
     
        /**
         * @param sms 
         */
         public void setSms(String sms){
	         this.smsListEntry.setSms(sms);     
         }
        /**
         * @return sms 
         */
         public String getSms( ){ 
	         return this.smsListEntry.getSms( );   
         }
     
        /**
         * @param code 
         */
         public void setCode(String code){
	         this.smsListEntry.setCode(code);     
         }
        /**
         * @return code 
         */
         public String getCode( ){ 
	         return this.smsListEntry.getCode( );   
         }

	public void setSource(String source) {
		this.smsListEntry.setSource(source);
	}

	public String getSource() {
		return this.smsListEntry.getSource();
	}
    
	
	//实现对应具体的业务功能
	//*****************************************************************************************************************
	
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onAdd() throws BusinessException {
	     
	    SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
	    smsListMapper.insertSmsList(this.smsListEntry);
	}

	
	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onModify() throws BusinessException {
		
	    SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
		smsListMapper.updateSmsList(this.smsListEntry);
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onDelete(Long ids[]) throws BusinessException {
		SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
		smsListMapper.deleteSmsList(ids);
	}
	
	
	
    /**
     * 根据主键（id）返回单条记录
     * @param id
     * @return  SmsListInfo
     */
    @Transactional(propagation=Propagation.NEVER)
	public static SmsListInfo findOne(Long id) throws BusinessException{
	    SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
		SmsListEntry entry = smsListMapper.findOne(id);
		if (entry == null) {
			entry = new SmsListEntry();
		}
		return new SmsListInfo(entry);
	}
	 
	
	
	/**
	 * 获取总纪录行数
	 *
	 * @param keyMap   参数Map
	 * @return 总纪录行数
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static int getTotalRows(HashMap<SmsListParamKey, Object> keyMap) throws BusinessException{
		SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
		return smsListMapper.querySmsListsRecordCount(toParamMap(keyMap));
	}	

	
	/**
	 * 分页查询
	 * @param startRow   开始记录的行数
	 * @param pageSize   每页显示的记录数
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<SmsListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<SmsListInfo> querySmsLists(int startRow, int pageSize,
				HashMap<SmsListParamKey, Object> keyMap, List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<SmsListInfo> list = new ArrayList<SmsListInfo>();
		//查询结果实体
		SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
		List<SmsListEntry> entryList = smsListMapper.querySmsLists(startRow, pageSize, toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (SmsListEntry entry : entryList) {
				list.add(new SmsListInfo(entry));
			}
		}
		return list;
	}
	
	/**
	 * 查询所有
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<SmsListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<SmsListInfo> queryAll(HashMap<SmsListParamKey, Object> keyMap, 
				List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<SmsListInfo> list = new ArrayList<SmsListInfo>();
		//查询结果实体
		SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
		List<SmsListEntry> entryList = smsListMapper.queryAll(toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (SmsListEntry entry : entryList) {
				list.add(new SmsListInfo(entry));
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
		SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
		int result = smsListMapper.isExist(columnName, columnValue, id);
		return result > 0;
	}	
	
	//自定义方法，方法要写上注释用途
	//*****************************************************************************************************************

	@Transactional(propagation=Propagation.NEVER)
	public static List<SmsListInfo> findSmsByPhoneAndDate(String phone, String lastTime, String code) throws BusinessException{
		//实例化List对象
		List<SmsListInfo> list = new ArrayList<SmsListInfo>();
		//查询结果实体
		SmsListMapper smsListMapper= SpringUtil.getBean("smsListMapper");
		List<SmsListEntry> entryList = smsListMapper.findSmsByPhoneAndDate(phone, lastTime, code);
		if (entryList != null){
			for (SmsListEntry entry : entryList) {
				list.add(new SmsListInfo(entry));
			}
		}
		return list;
	}
	
	
}

