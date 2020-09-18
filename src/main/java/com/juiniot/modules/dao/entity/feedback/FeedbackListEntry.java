package com.juiniot.modules.dao.entity.feedback;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description  对应的实体
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-26 20:52:56
 */
public class FeedbackListEntry implements Serializable {
   
    private static final long serialVersionUID = 1L;
     
    private Long  id; // 
    private Long  studentId; // 
    private Timestamp  createTime; // 
    private String  learningContent; // 
    private Integer  learningAchievement; // 
    private Integer  clear; // 
    private Integer  fluency; // 
    private Integer  creativity; // 
    private Integer  logicalThinking; // 
    private Integer  homeworkCompleaon; // 
    private Integer  concentration; // 
    private Integer  expression; // 
    private Integer  reaction; // 
    private Integer  interaction; // 
    private Integer  ruleConsciousness; // 
    private Integer  concernForOthers; // 
    private Integer  leaderShip; // 
    private Integer  corporation; // 
    private String  commentsOnStudent; // 
    private String  todayAchievement; //
    
     
    private String  name; // 
    private String  num; // 
    private String  classes; //
    private String  phone; //
     
    //默认空构造函数
	public FeedbackListEntry(){
	
	}
	
	
	
	
	 
    /**
     * @param id 
     */
    public void setId(Long id){
	    this.id=id;    
    }
    /**
     * @return id 
     */
    public Long getId(  ){ 
	    return this.id;    
    }
     
    /**
     * @param studentId 
     */
    public void setStudentId(Long studentId){
	    this.studentId=studentId;    
    }
    /**
     * @return studentId 
     */
    public Long getStudentId(  ){ 
	    return this.studentId;    
    }
     
    /**
     * @param createTime 
     */
    public void setCreateTime(Timestamp createTime){
	    this.createTime=createTime;    
    }
    /**
     * @return createTime 
     */
    public Timestamp getCreateTime(  ){ 
	    return this.createTime;    
    }
     
    /**
     * @param learningContent 
     */
    public void setLearningContent(String learningContent){
	    this.learningContent=learningContent;    
    }
    /**
     * @return learningContent 
     */
    public String getLearningContent(  ){ 
	    return this.learningContent;    
    }
     
    /**
     * @param learningAchievement 
     */
    public void setLearningAchievement(Integer learningAchievement){
	    this.learningAchievement=learningAchievement;    
    }
    /**
     * @return learningAchievement 
     */
    public Integer getLearningAchievement(  ){ 
	    return this.learningAchievement;    
    }
     
    /**
     * @param clear 
     */
    public void setClear(Integer clear){
	    this.clear=clear;    
    }
    /**
     * @return clear 
     */
    public Integer getClear(  ){ 
	    return this.clear;    
    }
     
    /**
     * @param fluency 
     */
    public void setFluency(Integer fluency){
	    this.fluency=fluency;    
    }
    /**
     * @return fluency 
     */
    public Integer getFluency(  ){ 
	    return this.fluency;    
    }
     
    /**
     * @param creativity 
     */
    public void setCreativity(Integer creativity){
	    this.creativity=creativity;    
    }
    /**
     * @return creativity 
     */
    public Integer getCreativity(  ){ 
	    return this.creativity;    
    }
     
    /**
     * @param logicalThinking 
     */
    public void setLogicalThinking(Integer logicalThinking){
	    this.logicalThinking=logicalThinking;    
    }
    /**
     * @return logicalThinking 
     */
    public Integer getLogicalThinking(  ){ 
	    return this.logicalThinking;    
    }
     
    /**
     * @param homeworkCompleaon 
     */
    public void setHomeworkCompleaon(Integer homeworkCompleaon){
	    this.homeworkCompleaon=homeworkCompleaon;    
    }
    /**
     * @return homeworkCompleaon 
     */
    public Integer getHomeworkCompleaon(  ){ 
	    return this.homeworkCompleaon;    
    }
     
    /**
     * @param concentration 
     */
    public void setConcentration(Integer concentration){
	    this.concentration=concentration;    
    }
    /**
     * @return concentration 
     */
    public Integer getConcentration(  ){ 
	    return this.concentration;    
    }
     
    /**
     * @param expression 
     */
    public void setExpression(Integer expression){
	    this.expression=expression;    
    }
    /**
     * @return expression 
     */
    public Integer getExpression(  ){ 
	    return this.expression;    
    }
     
    /**
     * @param reaction 
     */
    public void setReaction(Integer reaction){
	    this.reaction=reaction;    
    }
    /**
     * @return reaction 
     */
    public Integer getReaction(  ){ 
	    return this.reaction;    
    }
     
    /**
     * @param interaction 
     */
    public void setInteraction(Integer interaction){
	    this.interaction=interaction;    
    }
    /**
     * @return interaction 
     */
    public Integer getInteraction(  ){ 
	    return this.interaction;    
    }
     
    /**
     * @param ruleConsciousness 
     */
    public void setRuleConsciousness(Integer ruleConsciousness){
	    this.ruleConsciousness=ruleConsciousness;    
    }
    /**
     * @return ruleConsciousness 
     */
    public Integer getRuleConsciousness(  ){ 
	    return this.ruleConsciousness;    
    }
     
    /**
     * @param concernForOthers 
     */
    public void setConcernForOthers(Integer concernForOthers){
	    this.concernForOthers=concernForOthers;    
    }
    /**
     * @return concernForOthers 
     */
    public Integer getConcernForOthers(  ){ 
	    return this.concernForOthers;    
    }
     
    /**
     * @param leaderShip 
     */
    public void setLeaderShip(Integer leaderShip){
	    this.leaderShip=leaderShip;    
    }
    /**
     * @return leaderShip 
     */
    public Integer getLeaderShip(  ){ 
	    return this.leaderShip;    
    }
     
    /**
     * @param corporation 
     */
    public void setCorporation(Integer corporation){
	    this.corporation=corporation;    
    }
    /**
     * @return corporation 
     */
    public Integer getCorporation(  ){ 
	    return this.corporation;    
    }
     
    /**
     * @param commentsOnStudent 
     */
    public void setCommentsOnStudent(String commentsOnStudent){
	    this.commentsOnStudent=commentsOnStudent;    
    }
    /**
     * @return commentsOnStudent 
     */
    public String getCommentsOnStudent(  ){ 
	    return this.commentsOnStudent;    
    }
     
    /**
     * @param todayAchievement 
     */
    public void setTodayAchievement(String todayAchievement){
	    this.todayAchievement=todayAchievement;    
    }
    /**
     * @return todayAchievement 
     */
    public String getTodayAchievement(  ){ 
	    return this.todayAchievement;    
    }
    
    
      
    /**
     * @param name 
     */
	public void setName(String name){
	   	this.name=name;    
    }
    /**
     * @return name 
     */
    public String getName(  ){ 
	    return this.name;    
    }
     
    /**
     * @param num 
     */
	public void setNum(String num){
	   	this.num=num;    
    }
    /**
     * @return num 
     */
    public String getNum(  ){ 
	    return this.num;    
    }
     
    /**
     * @param classes 
     */
	public void setClasses(String classes){
	   	this.classes=classes;    
    }
    /**
     * @return classes 
     */
    public String getClasses(  ){ 
	    return this.classes;    
    }


    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
