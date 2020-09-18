package com.juiniot.modules.business.feedback;
 
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
import com.juiniot.modules.dao.entity.feedback.FeedbackListEntry;
import com.juiniot.modules.dao.mapper.feedback.FeedbackListMapper;
import com.juiniot.modules.business.feedback.FeedbackListParam.FeedbackListParamKey;


/**
 *
 * @description  对应的（业务逻辑类）
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-26 20:52:56
 *
 */
@Scope("prototype") 
@Service("feedbackListInfo")
public class FeedbackListInfo extends BaseBusiness {

	private static final long serialVersionUID = 1L;

    //实体属性
	//*****************************************************************************************************************
	private FeedbackListEntry feedbackListEntry;
          
	 
	 //构造函数
	 //*****************************************************************************************************************
	
     /**
     * 默认构造函数
     */
	 public FeedbackListInfo() {
	     feedbackListEntry = new FeedbackListEntry();
	 }
	
     /**
     * 构造函数
     */
	 public FeedbackListInfo(FeedbackListEntry entry) {
	     this.feedbackListEntry = entry;
	 }
	
	
	//属性对应的get 和 set 方法
	//*****************************************************************************************************************
	
     
        /**
         * @param id 
         */
         public void setId(Long id){
	         this.feedbackListEntry.setId(id);     
         }
        /**
         * @return id 
         */
         public Long getId( ){ 
	         return this.feedbackListEntry.getId( );   
         }
     
        /**
         * @param studentId 
         */
         public void setStudentId(Long studentId){
	         this.feedbackListEntry.setStudentId(studentId);     
         }
        /**
         * @return studentId 
         */
         public Long getStudentId( ){ 
	         return this.feedbackListEntry.getStudentId( );   
         }
     
        /**
         * @param createTime 
         */
         public void setCreateTime(Timestamp createTime){
	         this.feedbackListEntry.setCreateTime(createTime);     
         }
        /**
         * @return createTime 
         */
         public Timestamp getCreateTime( ){ 
	         return this.feedbackListEntry.getCreateTime( );   
         }
     
        /**
         * @param learningContent 
         */
         public void setLearningContent(String learningContent){
	         this.feedbackListEntry.setLearningContent(learningContent);     
         }
        /**
         * @return learningContent 
         */
         public String getLearningContent( ){ 
	         return this.feedbackListEntry.getLearningContent( );   
         }
     
        /**
         * @param learningAchievement 
         */
         public void setLearningAchievement(Integer learningAchievement){
	         this.feedbackListEntry.setLearningAchievement(learningAchievement);     
         }
        /**
         * @return learningAchievement 
         */
         public Integer getLearningAchievement( ){ 
	         return this.feedbackListEntry.getLearningAchievement( );   
         }
     
        /**
         * @param clear 
         */
         public void setClear(Integer clear){
	         this.feedbackListEntry.setClear(clear);     
         }
        /**
         * @return clear 
         */
         public Integer getClear( ){ 
	         return this.feedbackListEntry.getClear( );   
         }
     
        /**
         * @param fluency 
         */
         public void setFluency(Integer fluency){
	         this.feedbackListEntry.setFluency(fluency);     
         }
        /**
         * @return fluency 
         */
         public Integer getFluency( ){ 
	         return this.feedbackListEntry.getFluency( );   
         }
     
        /**
         * @param creativity 
         */
         public void setCreativity(Integer creativity){
	         this.feedbackListEntry.setCreativity(creativity);     
         }
        /**
         * @return creativity 
         */
         public Integer getCreativity( ){ 
	         return this.feedbackListEntry.getCreativity( );   
         }
     
        /**
         * @param logicalThinking 
         */
         public void setLogicalThinking(Integer logicalThinking){
	         this.feedbackListEntry.setLogicalThinking(logicalThinking);     
         }
        /**
         * @return logicalThinking 
         */
         public Integer getLogicalThinking( ){ 
	         return this.feedbackListEntry.getLogicalThinking( );   
         }
     
        /**
         * @param homeworkCompleaon 
         */
         public void setHomeworkCompleaon(Integer homeworkCompleaon){
	         this.feedbackListEntry.setHomeworkCompleaon(homeworkCompleaon);     
         }
        /**
         * @return homeworkCompleaon 
         */
         public Integer getHomeworkCompleaon( ){ 
	         return this.feedbackListEntry.getHomeworkCompleaon( );   
         }
     
        /**
         * @param concentration 
         */
         public void setConcentration(Integer concentration){
	         this.feedbackListEntry.setConcentration(concentration);     
         }
        /**
         * @return concentration 
         */
         public Integer getConcentration( ){ 
	         return this.feedbackListEntry.getConcentration( );   
         }
     
        /**
         * @param expression 
         */
         public void setExpression(Integer expression){
	         this.feedbackListEntry.setExpression(expression);     
         }
        /**
         * @return expression 
         */
         public Integer getExpression( ){ 
	         return this.feedbackListEntry.getExpression( );   
         }
     
        /**
         * @param reaction 
         */
         public void setReaction(Integer reaction){
	         this.feedbackListEntry.setReaction(reaction);     
         }
        /**
         * @return reaction 
         */
         public Integer getReaction( ){ 
	         return this.feedbackListEntry.getReaction( );   
         }
     
        /**
         * @param interaction 
         */
         public void setInteraction(Integer interaction){
	         this.feedbackListEntry.setInteraction(interaction);     
         }
        /**
         * @return interaction 
         */
         public Integer getInteraction( ){ 
	         return this.feedbackListEntry.getInteraction( );   
         }
     
        /**
         * @param ruleConsciousness 
         */
         public void setRuleConsciousness(Integer ruleConsciousness){
	         this.feedbackListEntry.setRuleConsciousness(ruleConsciousness);     
         }
        /**
         * @return ruleConsciousness 
         */
         public Integer getRuleConsciousness( ){ 
	         return this.feedbackListEntry.getRuleConsciousness( );   
         }
     
        /**
         * @param concernForOthers 
         */
         public void setConcernForOthers(Integer concernForOthers){
	         this.feedbackListEntry.setConcernForOthers(concernForOthers);     
         }
        /**
         * @return concernForOthers 
         */
         public Integer getConcernForOthers( ){ 
	         return this.feedbackListEntry.getConcernForOthers( );   
         }
     
        /**
         * @param leaderShip 
         */
         public void setLeaderShip(Integer leaderShip){
	         this.feedbackListEntry.setLeaderShip(leaderShip);     
         }
        /**
         * @return leaderShip 
         */
         public Integer getLeaderShip( ){ 
	         return this.feedbackListEntry.getLeaderShip( );   
         }
     
        /**
         * @param corporation 
         */
         public void setCorporation(Integer corporation){
	         this.feedbackListEntry.setCorporation(corporation);     
         }
        /**
         * @return corporation 
         */
         public Integer getCorporation( ){ 
	         return this.feedbackListEntry.getCorporation( );   
         }
     
        /**
         * @param commentsOnStudent 
         */
         public void setCommentsOnStudent(String commentsOnStudent){
	         this.feedbackListEntry.setCommentsOnStudent(commentsOnStudent);     
         }
        /**
         * @return commentsOnStudent 
         */
         public String getCommentsOnStudent( ){ 
	         return this.feedbackListEntry.getCommentsOnStudent( );   
         }
     
        /**
         * @param todayAchievement 
         */
         public void setTodayAchievement(String todayAchievement){
	         this.feedbackListEntry.setTodayAchievement(todayAchievement);     
         }
        /**
         * @return todayAchievement 
         */
         public String getTodayAchievement( ){ 
	         return this.feedbackListEntry.getTodayAchievement( );   
         }
    
    
               
        /**
         * @param name 
         */
         private void setName(String name){
	         this.feedbackListEntry.setName(name);
         }
         
        /**
          * @return name 
         */
         public String getName( ){ 
	         return this.feedbackListEntry.getName( );     
         }
              
        /**
         * @param num 
         */
         private void setNum(String num){
	         this.feedbackListEntry.setNum(num);
         }
         
        /**
          * @return num 
         */
         public String getNum( ){ 
	         return this.feedbackListEntry.getNum( );     
         }
              
        /**
         * @param classes 
         */
         private void setClasses(String classes){
	         this.feedbackListEntry.setClasses(classes);
         }
         
        /**
          * @return classes 
         */
         public String getClasses( ){ 
	         return this.feedbackListEntry.getClasses( );     
         }
    
    
	
	//实现对应具体的业务功能
	//*****************************************************************************************************************
	
	
	/**
	 * 新增
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onAdd() throws BusinessException {
	     
	    FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
	    feedbackListMapper.insertFeedbackList(this.feedbackListEntry);
	}

	
	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onModify() throws BusinessException {
		
	    FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
		feedbackListMapper.updateFeedbackList(this.feedbackListEntry);
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	protected void onDelete(Long ids[]) throws BusinessException {
		FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
		feedbackListMapper.deleteFeedbackList(ids);
	}
	
	
	
    /**
     * 根据主键（id）返回单条记录
     * @param id
     * @return  FeedbackListInfo
     */
    @Transactional(propagation=Propagation.NEVER)
	public static FeedbackListInfo findOne(Long id) throws BusinessException{
	    FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
		FeedbackListEntry entry = feedbackListMapper.findOne(id);
		if (entry == null) {
			entry = new FeedbackListEntry();
		}
		return new FeedbackListInfo(entry);
	}
	 
	
	
	/**
	 * 获取总纪录行数
	 *
	 * @param keyMap   参数Map
	 * @return 总纪录行数
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static int getTotalRows(HashMap<FeedbackListParamKey, Object> keyMap) throws BusinessException{
		FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
		return feedbackListMapper.queryFeedbackListsRecordCount(toParamMap(keyMap));
	}	

	
	/**
	 * 分页查询
	 * @param startRow   开始记录的行数
	 * @param pageSize   每页显示的记录数
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<FeedbackListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<FeedbackListInfo> queryFeedbackLists(int startRow, int pageSize,
				HashMap<FeedbackListParamKey, Object> keyMap, List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<FeedbackListInfo> list = new ArrayList<FeedbackListInfo>();
		//查询结果实体
		FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
		List<FeedbackListEntry> entryList = feedbackListMapper.queryFeedbackLists(startRow, pageSize, toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (FeedbackListEntry entry : entryList) {
				list.add(new FeedbackListInfo(entry));
			}
		}
		return list;
	}
	
	/**
	 * 查询所有
     * @param keyMap   参数Map
	 * @param orderList  控制排序
	 * @return List<FeedbackListInfo>
	 */
	@Transactional(propagation=Propagation.NEVER)
	public static List<FeedbackListInfo> queryAll(HashMap<FeedbackListParamKey, Object> keyMap, 
				List<OrderItem>orderList) throws BusinessException{
		//实例化List对象		
		List<FeedbackListInfo> list = new ArrayList<FeedbackListInfo>();
		//查询结果实体
		FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
		List<FeedbackListEntry> entryList = feedbackListMapper.queryAll(toParamMap(keyMap), orderList);
	    if (entryList != null){
			for (FeedbackListEntry entry : entryList) {
				list.add(new FeedbackListInfo(entry));
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
		FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
		int result = feedbackListMapper.isExist(columnName, columnValue, id);
		return result > 0;
	}	
	
	//自定义方法，方法要写上注释用途
	//*****************************************************************************************************************

	@Transactional(propagation=Propagation.NEVER)
	public static FeedbackListInfo findOneByLast() throws BusinessException{
		FeedbackListMapper feedbackListMapper= SpringUtil.getBean("feedbackListMapper");
		FeedbackListEntry entry = feedbackListMapper.findOneByLast();
		if (entry == null) {
			entry = new FeedbackListEntry();
		}
		return new FeedbackListInfo(entry);
	}

}

