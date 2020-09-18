package com.juiniot.modules.controller.student;
 
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.juiniot.common.utils.Cookies;
import com.juiniot.common.utils.StringUtil;
import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import com.juiniot.modules.business.feedback.FeedbackListInfo;
import com.juiniot.modules.business.feedback.FeedbackListParam;
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


import com.juiniot.modules.business.student.StudentListInfo;
import com.juiniot.modules.business.student.StudentListParam;
import com.juiniot.modules.business.student.StudentListParam.StudentListParamKey;


/**
 * @description  对应的控制类
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-09-05 21:37:11
 */
@Scope("prototype")
@Controller
@RequestMapping("student")
public class StudentListController extends BaseController {

    
    @Autowired
	private StudentListInfo studentListInfo;
    
    /**
	 * 查询列表数据
	 */
	@RequestMapping("student-list")
	public String queryList(HttpServletRequest request, Model model, StudentListVO vo) throws Exception{
	
		//设置查询参数，请自行修改或删除不需要的参数
		StudentListParam param = new StudentListParam();
		 
     	param.putValue(StudentListParamKey.id, vo.getId());
	 	 
     	param.putValue(StudentListParamKey.name, this.allFuzzy(vo.getName()));
	 	 
     	param.putValue(StudentListParamKey.num, this.allFuzzy(vo.getNum()));
	 	 
     	param.putValue(StudentListParamKey.phone, this.allFuzzy(vo.getPhone()));
	 	 
     	param.putValue(StudentListParamKey.sex, vo.getSex());
	 	 
     	param.putValue(StudentListParamKey.age, vo.getAge());
	 	 
     	param.putValue(StudentListParamKey.classes, this.allFuzzy(vo.getClasses()));
	 	 
     	param.putValue(StudentListParamKey.createTime1, vo.getCreateTime1()); param.putValue(StudentListParamKey.createTime2, vo.getCreateTime());
	 	
	 	
	 	
	 	//数据过滤。若需要过滤数据，请自行在下面设置参数
	 	
	 	
	 	//将查询参数转为HashMap
		HashMap<StudentListParamKey, Object> keyMap = param.getKeyMap();
		
		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();
		if(StringUtils.hasText(vo.getColumnName()) && StringUtils.hasText(vo.getOrderType())) {
			orderList.add(new OrderItem(vo.getColumnName(), vo.getOrderType()));//自定义排序
		}
		else {
			orderList.add(new OrderItem(StudentListParamKey.id, OrderType.DESC));//默认主键降序排序
		}
		
		//获取总数
		int totalRows = StudentListInfo.getTotalRows(keyMap);
		//获取列表
		List<StudentListInfo> list = StudentListInfo.queryStudentLists(vo.getStartRow(), vo.getPageSize(), keyMap, orderList);

		if(list.size()>0){
			list.remove(list.size()-1);
			totalRows = totalRows - 1;
		}

		//获取分页模型对象
		PageModel<StudentListInfo> pm = this.getPageModel(vo.getPage(), totalRows, vo.getPageSize(), list);
		
		//将数据集合返回到页面端
		model.addAttribute("page", pm);
		model.addAttribute("vo", vo);
		
		return "student/student-list"; //返回页面
	
	}
	
	/**
	 * 新增或修改页面初始化
	 */
	@RequestMapping("student-add") //请求路径
	public String addInit(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			studentListInfo = StudentListInfo.findOne(id);
		}
		
		model.addAttribute("info", studentListInfo);
		return "student/student-add"; //返回页面
	}
	
	/**
	 * ajax加载单个数据
	 */
	@ResponseBody
	@RequestMapping("student-load")
	public StudentListInfo load(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			studentListInfo = StudentListInfo.findOne(id);
		}
		
		return studentListInfo; 
	}
	
	
	/**
	 * 保存方法
	 */
	@ResponseBody
	@RequestMapping(value = "student-save", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse save(HttpServletRequest request,StudentListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	

		if(StringUtils.isEmpty(vo.getName())){
			return BaseResponse.failure("姓名不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getNum())){
			return BaseResponse.failure("编号不能为空");
		}

		if(StringUtils.isEmpty(vo.getSex())){
			return BaseResponse.failure("性别不能为空");
		}

		if(StringUtils.isEmpty(vo.getPhone())){
			return BaseResponse.failure("联系方式不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getAge())){
			return BaseResponse.failure("年龄不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getClasses())){
			return BaseResponse.failure("班级不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCreateTime())){
			return BaseResponse.failure("入园不能为空");
		}

		//设值，请自行修正或删除不正确的设值

		studentListInfo.setName(vo.getName());

		studentListInfo.setNum(vo.getNum());

		studentListInfo.setPhone(vo.getPhone());

		studentListInfo.setSex(vo.getSex());
		
		studentListInfo.setAge(vo.getAge());
		
		studentListInfo.setClasses(vo.getClasses());
		
		studentListInfo.setCreateTime(vo.getCreateTime());
		
		
		try {
			studentListInfo.add();
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
	@RequestMapping(value = "student-update", method = RequestMethod.POST) //请求路径
	public BaseResponse update(HttpServletRequest request,StudentListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	
		if(StringUtils.isEmpty(vo.getName())){
			return BaseResponse.failure("姓名不能为空");
		}

		if(StringUtils.isEmpty(vo.getPhone())){
			return BaseResponse.failure("联系不能为空");
		}

		if(StringUtils.isEmpty(vo.getAge())){
			return BaseResponse.failure("年龄不能为空");
		}

		if(StringUtils.isEmpty(vo.getClasses())){
			return BaseResponse.failure("班级不能为空");
		}

		//检查ID值是否为空
		if(vo.getId() == null){
			return BaseResponse.failure("保存失败，请刷新页面再试试");
		}
		studentListInfo = StudentListInfo.findOne(vo.getId());
		
		//设值，请自行修正或删除不正确的设值
		studentListInfo.setName(vo.getName());

		studentListInfo.setPhone(vo.getPhone());
		
		studentListInfo.setAge(vo.getAge());
		
		studentListInfo.setClasses(vo.getClasses());

		try {
			studentListInfo.modify();
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
	@RequestMapping(value = "student-delete", method = RequestMethod.POST) //请求路径
	public BaseResponse delete(HttpServletRequest request,String ids) throws Exception{
		
		try {
			studentListInfo.delete(NumberUtil.toLongArray(ids));
			return BaseResponse.SUCCESS;
			
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	}
	
	//自定义方法，方法要写上注释用途和权限注解
	//*****************************************************************************************************************

	@ResponseBody
	@RequestMapping("student-select")
	public List<HashMap<String,Object>> selectStudent(HttpServletRequest request) throws Exception{
		List<HashMap<String,Object>> list =  StudentListInfo.selectStudent();
		return list;
	}

	@RequestMapping("student-feedback")
	@Authority(needSession = NeedSession.NO)
	public String feedbackStudent(HttpServletRequest request, Model model, String phone) throws Exception{

		//设置查询参数，请自行修改或删除不需要的参数
		FeedbackListParam param = new FeedbackListParam();

		if(!StringUtil.isBlank(phone)){
			param.putValue(FeedbackListParam.FeedbackListParamKey.phone, phone);
		} else {
			String account = Cookies.getValue(request, "account");
			String studentId = Cookies.getValue(request, "studentId");

			if(StringUtil.isBlank(account) || StringUtil.isBlank(studentId)){
				return "login";
			}

			param.putValue(FeedbackListParam.FeedbackListParamKey.studentId, studentId);
			param.putValue(FeedbackListParam.FeedbackListParamKey.name, account);
		}
		//数据过滤。若需要过滤数据，请自行在下面设置参数

		//将查询参数转为HashMap
		HashMap<FeedbackListParam.FeedbackListParamKey, Object> keyMap = param.getKeyMap();

		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();
		orderList.add(new OrderItem(FeedbackListParam.FeedbackListParamKey.createTime, OrderType.DESC));

		//获取总数
		int totalRows = FeedbackListInfo.getTotalRows(keyMap);
		//获取列表
		List<FeedbackListInfo> list = FeedbackListInfo.queryAll(keyMap, orderList);

		//将数据集合返回到页面端
		model.addAttribute("list", list);

		return "student/student-feedback";
	}

	
}

