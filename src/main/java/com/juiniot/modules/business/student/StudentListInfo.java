package com.juiniot.modules.business.student;
 
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
import com.juiniot.modules.dao.entity.student.StudentListEntry;
import com.juiniot.modules.dao.mapper.student.StudentListMapper;
import com.juiniot.modules.business.student.StudentListParam.StudentListParamKey;


/**
 *
 * @description  对应的（业务逻辑类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-09-05 21:37:11
 *
 */
@Scope("prototype") 
@Service("studentListInfo")
public class StudentListInfo extends BaseBusiness {

	private static final long serialVersionUID = 1L;

    //实体属性
	//*****************************************************************************************************************
	private StudentListEntry studentListEntry;
          
	 
	 //构造函数
	 //*****************************************************************************************************************
	
     /**
     * 默认构造函数
     */
	 public StudentListInfo() {
	     studentListEntry = new StudentListEntry();
	 }
	
     /**
     * 构造函数
     */
	 public StudentListInfo(StudentListEntry entry) {
	     this.studentListEntry = entry;
	 }
	
	
	//属性对应的get 和 set 方法
	//*****************************************************************************************************************
	
     
        /**
         * @param id 
         */
         public void setId(Long id){
	         this.studentListEntry.setId(id);     
         }
        /**
         * @return id 
         */
         public Long getId( ){ 
	         return this.studentListEntry.getId( );   
         }
     
        /**
         * @param name 
         */
         public void setName(String name){
	         this.studentListEntry.setName(name);     
         }
        /**
         * @return name 
         */
         public String getName( ){ 
	         return this.studentListEntry.getName( );   
         }
     
        /**
         * @param num 
         */
         public void setNum(String num){
	         this.studentListEntry.setNum(num);     
         }
        /**
         * @return num 
         */
         public String getNum( ){ 
	         return this.studentListEntry.getNum( );   
         }
     
        /**
         * @param phone 
         */
         public void setPhone(String phone){
	         this.studentListEntry.setPhone(phone);     
         }
        /**
         * @return phone 
         */
         public String getPhone( ){ 
	         return this.studentListEntry.getPhone( );   
         }
     
        /**
         * @param sex 
         */
         public void setSex(Integer sex){
	         this.studentListEntry.setSex(sex);     
         }
        /**
         * @return sex 
         */
         public Integer getSex( ){ 
	         return this.studentListEntry.getSex( );   
         }
     
        /**
         * @param age 
         */
         public void setAge(Integer age){
	         this.studentListEntry.setAge(age);     
         }
        /**
         * @return age 
         */
         public Integer getAge( ){ 
	         return this.studentListEntry.getAge( );   
         }
     
        /**
         * @param classes 
         */
         public void setClasses(String classes){
	         this.studentListEntry.setClasses(classes);     
         }
        /**
         * @return classes 
         */
         public String getClasses( ){ 
	         return this.studentListEntry.getClasses( );   
         }
     
        /**
         * @param createTime 
         */
         public void setCreateTime(Timestamp createTime){
	         this.studentListEntry.setCreateTime(createTime);     
         }
        /**
         * @return createTime 
         */
         public Timestamp getCreateTime( ){ 
	         return this.studentListEntry.getCreateTime( );   
         }
    
    
     
    
	
	//实现对应具体的业务功能
	//*****************************************************************************************************************
	
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onAdd() throws BusinessException {
	     
	    StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
	    studentListMapper.insertStudentList(this.studentListEntry);
	}

	
	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onModify() throws BusinessException {
		
	    StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
		studentListMapper.updateStudentList(this.studentListEntry);
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onDelete(Long ids[]) throws BusinessException {
		StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
		studentListMapper.deleteStudentList(ids);
	}
	
	
	
    /**
     * 根据主键（id）返回单条记录
     * @param id
     * @return  StudentListInfo
     */
    @Transactional(propagation=Propagation.NEVER)
	public static StudentListInfo findOne(Long id) throws BusinessException{
	    StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
		StudentListEntry entry = studentListMapper.findOne(id);
		if (entry == null) {
			entry = new StudentListEntry();
		}
		return new StudentListInfo(entry);
	}
	 
	
	
	/**
	 * 获取总纪录行数
	 *
	 * @param keyMap   参数Map
	 * @return 总纪录行数
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static int getTotalRows(HashMap<StudentListParamKey, Object> keyMap) throws BusinessException{
		StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
		return studentListMapper.queryStudentListsRecordCount(toParamMap(keyMap));
	}	

	
	/**
	 * 分页查询
	 * @param startRow   开始记录的行数
	 * @param pageSize   每页显示的记录数
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<StudentListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<StudentListInfo> queryStudentLists(int startRow, int pageSize,
				HashMap<StudentListParamKey, Object> keyMap, List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<StudentListInfo> list = new ArrayList<StudentListInfo>();
		//查询结果实体
		StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
		List<StudentListEntry> entryList = studentListMapper.queryStudentLists(startRow, pageSize, toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (StudentListEntry entry : entryList) {
				list.add(new StudentListInfo(entry));
			}
		}
		return list;
	}
	
	/**
	 * 查询所有
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<StudentListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<StudentListInfo> queryAll(HashMap<StudentListParamKey, Object> keyMap, 
				List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<StudentListInfo> list = new ArrayList<StudentListInfo>();
		//查询结果实体
		StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
		List<StudentListEntry> entryList = studentListMapper.queryAll(toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (StudentListEntry entry : entryList) {
				list.add(new StudentListInfo(entry));
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
		StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
		int result = studentListMapper.isExist(columnName, columnValue, id);
		return result > 0;
	}	
	
	//自定义方法，方法要写上注释用途
	//*****************************************************************************************************************

	public static List<HashMap<String,Object>> selectStudent() {
		StudentListMapper studentListMapper= SpringUtil.getBean("studentListMapper");
		return studentListMapper.selectStudent();
	}

}

