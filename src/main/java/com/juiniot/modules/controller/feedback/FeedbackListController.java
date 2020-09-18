package com.juiniot.modules.controller.feedback;
 
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.util.StringUtils;

import com.juiniot.common.web.BaseController;
import com.juiniot.common.business.BusinessException;
import com.juiniot.common.business.OrderItem;
import com.juiniot.common.business.OrderType;
import com.juiniot.common.web.PageModel;
import com.juiniot.common.web.response.BaseResponse;
import com.juiniot.common.utils.NumberUtil;


import com.juiniot.modules.business.feedback.FeedbackListInfo;
import com.juiniot.modules.business.feedback.FeedbackListParam;
import com.juiniot.modules.business.feedback.FeedbackListParam.FeedbackListParamKey;


/**
 * @description  对应的控制类
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-26 20:52:56
 */
@Scope("prototype")
@Controller
@RequestMapping("feedback")
public class FeedbackListController extends BaseController {

    
    @Autowired
	private FeedbackListInfo feedbackListInfo;
    
    /**
	 * 查询列表数据
	 */
	@RequestMapping("feedback-list")
	public String queryList(HttpServletRequest request, Model model, FeedbackListVO vo) throws Exception{
	
		//设置查询参数，请自行修改或删除不需要的参数
		FeedbackListParam param = new FeedbackListParam();
		 
     	param.putValue(FeedbackListParamKey.id, vo.getId());
	 	 
     	param.putValue(FeedbackListParamKey.studentId, vo.getStudentId());
	 	 
     	param.putValue(FeedbackListParamKey.createTime1, vo.getCreateTime1()); param.putValue(FeedbackListParamKey.createTime2, vo.getCreateTime());
	 	 
     	param.putValue(FeedbackListParamKey.learningContent, this.allFuzzy(vo.getLearningContent()));
	 	 
     	param.putValue(FeedbackListParamKey.learningAchievement, vo.getLearningAchievement());
	 	 
     	param.putValue(FeedbackListParamKey.clear, vo.getClear());
	 	 
     	param.putValue(FeedbackListParamKey.fluency, vo.getFluency());
	 	 
     	param.putValue(FeedbackListParamKey.creativity, vo.getCreativity());
	 	 
     	param.putValue(FeedbackListParamKey.logicalThinking, vo.getLogicalThinking());
	 	 
     	param.putValue(FeedbackListParamKey.homeworkCompleaon, vo.getHomeworkCompleaon());
	 	 
     	param.putValue(FeedbackListParamKey.concentration, vo.getConcentration());
	 	 
     	param.putValue(FeedbackListParamKey.expression, vo.getExpression());
	 	 
     	param.putValue(FeedbackListParamKey.reaction, vo.getReaction());
	 	 
     	param.putValue(FeedbackListParamKey.interaction, vo.getInteraction());
	 	 
     	param.putValue(FeedbackListParamKey.ruleConsciousness, vo.getRuleConsciousness());
	 	 
     	param.putValue(FeedbackListParamKey.concernForOthers, vo.getConcernForOthers());
	 	 
     	param.putValue(FeedbackListParamKey.leaderShip, vo.getLeaderShip());
	 	 
     	param.putValue(FeedbackListParamKey.corporation, vo.getCorporation());
	 	 
     	param.putValue(FeedbackListParamKey.commentsOnStudent, this.allFuzzy(vo.getCommentsOnStudent()));
	 	 
     	param.putValue(FeedbackListParamKey.todayAchievement, this.allFuzzy(vo.getTodayAchievement()));
	 	
	 	          
	    param.putValue(FeedbackListParamKey.name, this.allFuzzy(vo.getName()));
	 	          
	    param.putValue(FeedbackListParamKey.num, this.allFuzzy(vo.getNum()));
	 	          
	    param.putValue(FeedbackListParamKey.classes, this.allFuzzy(vo.getClasses()));
	 	
	 	
	 	//数据过滤。若需要过滤数据，请自行在下面设置参数
	 	
	 	
	 	//将查询参数转为HashMap
		HashMap<FeedbackListParamKey, Object> keyMap = param.getKeyMap();
		
		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();
		if(StringUtils.hasText(vo.getColumnName()) && StringUtils.hasText(vo.getOrderType())) {
			orderList.add(new OrderItem(vo.getColumnName(), vo.getOrderType()));//自定义排序
		}
		else {
			orderList.add(new OrderItem(FeedbackListParamKey.id, OrderType.DESC));//默认主键降序排序
		}
		
		//获取总数
		int totalRows = FeedbackListInfo.getTotalRows(keyMap);
		//获取列表
		List<FeedbackListInfo> list = FeedbackListInfo.queryFeedbackLists(vo.getStartRow(), vo.getPageSize(), keyMap, orderList);
		
		//获取分页模型对象
		PageModel<FeedbackListInfo> pm = this.getPageModel(vo.getPage(), totalRows, vo.getPageSize(), list);
		
		//将数据集合返回到页面端
		model.addAttribute("page", pm);
		model.addAttribute("vo", vo);
		
		return "feedback/feedback-list"; //返回页面
	
	}
	
	/**
	 * 新增或修改页面初始化
	 */
	@RequestMapping("feedback-add") //请求路径
	public String addInit(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			feedbackListInfo = FeedbackListInfo.findOne(id);
		}
		
		model.addAttribute("info", feedbackListInfo);
		return "feedback/feedback-add"; //返回页面
	}
	
	/**
	 * ajax加载单个数据
	 */
	@ResponseBody
	@RequestMapping("feedback-load")
	public FeedbackListInfo load(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			feedbackListInfo = FeedbackListInfo.findOne(id);
		}
		
		return feedbackListInfo; 
	}
	
	
	/**
	 * 保存方法
	 */
	@ResponseBody
	@RequestMapping(value = "feedback-save", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse save(HttpServletRequest request,FeedbackListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	
		
		if(StringUtils.isEmpty(vo.getStudentId()) || "0".equals(vo.getStudentId())){
			return BaseResponse.failure("请选择学员");
		}
		
		if(StringUtils.isEmpty(vo.getLearningContent())){
			return BaseResponse.failure("学习内容不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCommentsOnStudent())){
			return BaseResponse.failure("教师评语不能为空");
		}
		
		//设值，请自行修正或删除不正确的设值
		
		feedbackListInfo.setStudentId(vo.getStudentId());
		
		feedbackListInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		feedbackListInfo.setLearningContent(vo.getLearningContent());
		
		feedbackListInfo.setLearningAchievement(vo.getLearningAchievement());
		
		feedbackListInfo.setClear(vo.getClear());
		
		feedbackListInfo.setFluency(vo.getFluency());
		
		feedbackListInfo.setCreativity(vo.getCreativity());
		
		feedbackListInfo.setLogicalThinking(vo.getLogicalThinking());
		
		feedbackListInfo.setHomeworkCompleaon(vo.getHomeworkCompleaon());
		
		feedbackListInfo.setConcentration(vo.getConcentration());
		
		feedbackListInfo.setExpression(vo.getExpression());
		
		feedbackListInfo.setReaction(vo.getReaction());
		
		feedbackListInfo.setInteraction(vo.getInteraction());
		
		feedbackListInfo.setRuleConsciousness(vo.getRuleConsciousness());
		
		feedbackListInfo.setConcernForOthers(vo.getConcernForOthers());
		
		feedbackListInfo.setLeaderShip(vo.getLeaderShip());
		
		feedbackListInfo.setCorporation(vo.getCorporation());
		
		feedbackListInfo.setCommentsOnStudent(vo.getCommentsOnStudent());
		
		feedbackListInfo.setTodayAchievement(vo.getTodayAchievement());
		
		
		try {
			feedbackListInfo.add();
			return BaseResponse.SUCCESS;
			
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	
	}
	
	/**
	 * 更新方法
	 */
	@ResponseBody
	@RequestMapping(value = "feedback-update", method = RequestMethod.POST) //请求路径
	public BaseResponse update(HttpServletRequest request,FeedbackListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	
		
		if(StringUtils.isEmpty(vo.getId())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getStudentId())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCreateTime())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getLearningContent())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getLearningAchievement())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getClear())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getFluency())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCreativity())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getLogicalThinking())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getHomeworkCompleaon())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getConcentration())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getExpression())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getReaction())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getInteraction())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getRuleConsciousness())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getConcernForOthers())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getLeaderShip())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCorporation())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCommentsOnStudent())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getTodayAchievement())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		
		//检查ID值是否为空
		if(vo.getId() == null){
			return BaseResponse.failure("保存失败，请刷新页面再试试");
		}
		feedbackListInfo = FeedbackListInfo.findOne(vo.getId());
		
		//设值，请自行修正或删除不正确的设值
		
		feedbackListInfo.setId(vo.getId());
		
		feedbackListInfo.setStudentId(vo.getStudentId());
		
		feedbackListInfo.setCreateTime(vo.getCreateTime());
		
		feedbackListInfo.setLearningContent(vo.getLearningContent());
		
		feedbackListInfo.setLearningAchievement(vo.getLearningAchievement());
		
		feedbackListInfo.setClear(vo.getClear());
		
		feedbackListInfo.setFluency(vo.getFluency());
		
		feedbackListInfo.setCreativity(vo.getCreativity());
		
		feedbackListInfo.setLogicalThinking(vo.getLogicalThinking());
		
		feedbackListInfo.setHomeworkCompleaon(vo.getHomeworkCompleaon());
		
		feedbackListInfo.setConcentration(vo.getConcentration());
		
		feedbackListInfo.setExpression(vo.getExpression());
		
		feedbackListInfo.setReaction(vo.getReaction());
		
		feedbackListInfo.setInteraction(vo.getInteraction());
		
		feedbackListInfo.setRuleConsciousness(vo.getRuleConsciousness());
		
		feedbackListInfo.setConcernForOthers(vo.getConcernForOthers());
		
		feedbackListInfo.setLeaderShip(vo.getLeaderShip());
		
		feedbackListInfo.setCorporation(vo.getCorporation());
		
		feedbackListInfo.setCommentsOnStudent(vo.getCommentsOnStudent());
		
		feedbackListInfo.setTodayAchievement(vo.getTodayAchievement());
		
		
		try {
			feedbackListInfo.modify();
			return BaseResponse.SUCCESS;
			
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	
	}
	
	/**
	 * 删除方法
	 */
	@ResponseBody
	@RequestMapping(value = "feedback-delete", method = RequestMethod.POST) //请求路径
	public BaseResponse delete(HttpServletRequest request,String ids) throws Exception{
		
		try {
			feedbackListInfo.delete(NumberUtil.toLongArray(ids));
			return BaseResponse.SUCCESS;
			
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	}
	
	//自定义方法，方法要写上注释用途和权限注解
	//*****************************************************************************************************************

	/**
	 * ajax加载单个数据
	 */
	@RequestMapping("feedback-new")
	public String loadNew(HttpServletRequest request, Model model) throws Exception{

		model.addAttribute("info", new FeedbackListInfo());

		return "feedback/feedback-add";
	}

	/**
	 * ajax加载单个数据
	 */
	@RequestMapping("feedback-show")
	@Authority(needSession = NeedSession.NO)
	public String loadShow(HttpServletRequest request, Model model, Long id) throws Exception{

		FeedbackListInfo feedbackListInfo = FeedbackListInfo.findOne(id);
		if(null  == feedbackListInfo.getId()){
			feedbackListInfo = new FeedbackListInfo();
		}
		model.addAttribute("info", feedbackListInfo);

		return "feedback/feedback-show";
	}
	
}

