package com.juiniot.modules.dao.mapper.sms;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.juiniot.modules.dao.entity.sms.SmsListEntry;
import com.juiniot.common.business.OrderItem;

/**
 *
 * @description 短信记录 对应的Mapper（持久化接口类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-12 15:16:39
 *
 */
@Component("smsListMapper")
public interface SmsListMapper {

    
     //自动生成的方法
	 //************************************************************************************************************************
    
   
	/**
	 * 根据主键查询对应的记录
	 * @param id  记录对应的主键
	 * @return SmsListEntry
	 */
	SmsListEntry findOne(@Param("id") Long id);
	
	
	/**
	  * 分页查询
	  * @param startRow   开始记录的行数
	  * @param pageSize   每页显示的记录数
      * @param paramMap   参数Map
	  * @param orderList  控制排序
	  * @return List<SmsListEntry>
	  */
	List<SmsListEntry> querySmsLists(@Param("startRow") int startRow, @Param("pageSize") int pageSize,
                                     @Param("paramMap") HashMap<String, Object> paramMap, @Param("orderList") List<OrderItem> orderList);

	/**
	  * 查询所有
      * @param paramMap   参数Map
	  * @param orderList  控制排序
	  * @return List<SmsListEntry>
	  */
	List<SmsListEntry> queryAll(@Param("paramMap") HashMap<String, Object> paramMap,
                                @Param("orderList") List<OrderItem> orderList);
	

	/**
	 * 列表的记录总数统计
	 *
     * @param paramMap   参数Map
	 * @return 列表的记录数
	 */
	int querySmsListsRecordCount(@Param("paramMap") HashMap<String, Object> paramMap);
	
	
	/**
	 * 新增记录
	 
	 * @param smsList
	 * @return
	 */
	int insertSmsList(@Param("smsList") SmsListEntry smsList);
	
	
	
	/**
	 * 更新记录 
	 
	 * @param smsList
	 * @return
	 */
	int updateSmsList(@Param("smsList") SmsListEntry smsList);
    
	
	
	/**
	 * 删除记录
	 
	 * @param ids  记录对应的主键
	 * @return
	 */
	int deleteSmsList(@Param("ids") Long[] ids);
	
	
	
	/**
	 * 检查是字段值是否已存在
	 * @param columnName 字段名
	 * @param columnValue 字段值
	 * @param id 主键id值，新增时判断填null，修改时判断填主键id值
	 * @return
	 */
	int isExist(@Param("columnName") String columnName, @Param("columnValue") String columnValue, @Param("id") Long id);
	
	
	//自定义方法，方法要写上注释用途
	//************************************************************************************************************************

	List<SmsListEntry> findSmsByPhoneAndDate(@Param("phone") String phone,
								@Param("lastTime") String lastTime, @Param("code") String code);
	
}

