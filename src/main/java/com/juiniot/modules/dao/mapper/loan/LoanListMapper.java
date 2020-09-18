package com.juiniot.modules.dao.mapper.loan;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.juiniot.modules.dao.entity.loan.LoanListEntry;
import com.juiniot.common.business.OrderItem;

/**
 *
 * @description  对应的Mapper（持久化接口类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 *
 */
@Component("loanListMapper")
public interface LoanListMapper {

    
     //自动生成的方法
	 //************************************************************************************************************************
    
   
	/**
	 * 根据主键查询对应的记录
	 * @param id  记录对应的主键
	 * @return LoanListEntry
	 */
	LoanListEntry findOne(@Param("id") Long id);
	
	
	/**
	  * 分页查询
	  * @param startRow   开始记录的行数
	  * @param pageSize   每页显示的记录数
      * @param paramMap   参数Map
	  * @param orderList  控制排序
	  * @return List<LoanListEntry>
	  */
	List<LoanListEntry> queryLoanLists(@Param("startRow") int startRow, @Param("pageSize") int pageSize,
                                       @Param("paramMap") HashMap<String, Object> paramMap, @Param("orderList") List<OrderItem> orderList);

	/**
	  * 查询所有
      * @param paramMap   参数Map
	  * @param orderList  控制排序
	  * @return List<LoanListEntry>
	  */
	List<LoanListEntry> queryAll(@Param("paramMap") HashMap<String, Object> paramMap,
                                 @Param("orderList") List<OrderItem> orderList);
	

	/**
	 * 列表的记录总数统计
	 *
     * @param paramMap   参数Map
	 * @return 列表的记录数
	 */
	int queryLoanListsRecordCount(@Param("paramMap") HashMap<String, Object> paramMap);
	
	
	/**
	 * 新增记录
	 
	 * @param loanList
	 * @return
	 */
	int insertLoanList(@Param("loanList") LoanListEntry loanList);
	
	
	
	/**
	 * 更新记录 
	 
	 * @param loanList
	 * @return
	 */
	int updateLoanList(@Param("loanList") LoanListEntry loanList);
    
	
	
	/**
	 * 删除记录
	 
	 * @param ids  记录对应的主键
	 * @return
	 */
	int deleteLoanList(@Param("ids") Long[] ids);
	
	
	
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
	
	
	
}

