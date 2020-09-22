package com.juiniot.modules.controller.user;
 
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import com.juiniot.common.utils.CommonConfUtil;
import com.juiniot.common.utils.Cookies;
import com.juiniot.common.utils.StringUtil;
import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import com.juiniot.modules.business.recharge.RechargeListInfo;
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


import com.juiniot.modules.business.user.UserListInfo;
import com.juiniot.modules.business.user.UserListParam;
import com.juiniot.modules.business.user.UserListParam.UserListParamKey;


/**
 * @description  对应的控制类
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 15:52:09
 */
@Scope("prototype")
@Controller
@RequestMapping("user")
public class UserListController extends BaseController {

	@Autowired
	private UserListInfo userListInfo;

    /**
	 * 查询列表数据
	 */
	@RequestMapping("user-list")
	public String queryList(HttpServletRequest request, Model model, UserListVO vo) throws Exception{
	
		//设置查询参数，请自行修改或删除不需要的参数
		UserListParam param = new UserListParam();

     	param.putValue(UserListParamKey.account, this.allFuzzy(vo.getAccount()));

		param.putValue(UserListParamKey.name, this.allFuzzy(vo.getName()));

		param.putValue(UserListParamKey.parentName, this.allFuzzy(vo.getParentName()));
	 	 
		String treeCode = Cookies.getValue(request, "treeCode");

		if(!StringUtil.isBlank(treeCode)){
			param.putValue(UserListParamKey.treeCode, this.allFuzzy(treeCode));
		} else {
			return "login";
		}

     	param.putValue(UserListParamKey.createTime1, vo.getCreateTime1()); param.putValue(UserListParamKey.createTime2, vo.getCreateTime());

	 	//数据过滤。若需要过滤数据，请自行在下面设置参数
	 	
	 	
	 	//将查询参数转为HashMap
		HashMap<UserListParamKey, Object> keyMap = param.getKeyMap();
		
		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();
		if(StringUtils.hasText(vo.getColumnName()) && StringUtils.hasText(vo.getOrderType())) {
			orderList.add(new OrderItem(vo.getColumnName(), vo.getOrderType()));//自定义排序
		} else {
			orderList.add(new OrderItem(UserListParamKey.id, OrderType.ASC));//默认主键降序排序
		}
		
		//获取总数
		int totalRows = UserListInfo.getTotalRows(keyMap);
		//获取列表
		List<UserListInfo> list = UserListInfo.queryUserLists(vo.getStartRow(), vo.getPageSize(), keyMap, orderList);
		
		//获取分页模型对象
		PageModel<UserListInfo> pm = this.getPageModel(vo.getPage(), totalRows, vo.getPageSize(), list);

		String type = Cookies.getValue(request, "type");
		String userId = Cookies.getValue(request, "userId");

		//将数据集合返回到页面端
		model.addAttribute("userId", userId);
		model.addAttribute("type", type);
		model.addAttribute("page", pm);
		model.addAttribute("vo", vo);
		
		return "user/user-list"; //返回页面
	
	}
	
	/**
	 * 新增或修改页面初始化
	 */
	@RequestMapping("user-add") //请求路径
	public String addInit(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			userListInfo = UserListInfo.findOne(id);
		}
		
		model.addAttribute("info", userListInfo);
		return "user/user-add"; //返回页面
	}
	
	/**
	 * ajax加载单个数据
	 */
	@ResponseBody
	@RequestMapping("user-load")
	public UserListInfo load(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			userListInfo = UserListInfo.findOne(id);
		}
		
		return userListInfo; 
	}
	
	
	/**
	 * 保存方法
	 */
	@ResponseBody
	@RequestMapping(value = "user-save", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse save(HttpServletRequest request,UserListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	

		if(StringUtils.isEmpty(vo.getName())){
			return BaseResponse.failure("昵称不能为空");
		}

		if(StringUtils.isEmpty(vo.getAccount())){
			return BaseResponse.failure("账号不能为空");
		}

		if(StringUtils.isEmpty(vo.getPwd())){
			return BaseResponse.failure("密码不能为空");
		}

		if(null == vo.getRate()){
			return BaseResponse.failure("费率不能为空");
		}

		//设值，请自行修正或删除不正确的设值

		String userId = Cookies.getValue(request, "userId");
		String treeCode = Cookies.getValue(request, "treeCode");
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(treeCode)){
			return BaseResponse.failure("请重新登录");
		}

		UserListParam param = new UserListParam();

		param.putValue(UserListParamKey.account, vo.getAccount());

		//将查询参数转为HashMap
		HashMap<UserListParamKey, Object> keyMap = param.getKeyMap();

		//获取总数
		int totalRows = UserListInfo.getTotalRows(keyMap);

		if(totalRows > 0){
			return BaseResponse.failure("账号已存在，请更换一个");
		}

		userListInfo.setParentId(Long.valueOf(userId));
		
		userListInfo.setAccount(vo.getAccount());
		
		userListInfo.setPwd(vo.getPwd());

		userListInfo.setName(vo.getName());

		userListInfo.setMobile(vo.getMobile());

		userListInfo.setTreeCode(treeCode + StringUtil.createNoncestr(4));

		userListInfo.setMoney(0d);

		userListInfo.setSurplus(0d);

		userListInfo.setRate(vo.getRate());
		
		userListInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		try {
			userListInfo.add();
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
	@RequestMapping(value = "user-update", method = RequestMethod.POST) //请求路径
	public BaseResponse update(HttpServletRequest request,UserListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	

        if(StringUtils.isEmpty(vo.getMobile()) || vo.getMobile().length() != 11){
			return BaseResponse.failure("联系方式错误");
		}

		//检查ID值是否为空
		if(vo.getId() == null){
			return BaseResponse.failure("保存失败，请刷新页面再试试");
		}
		userListInfo = UserListInfo.findOne(vo.getId());
		
		//设值，请自行修正或删除不正确的设值
		
		userListInfo.setMobile(vo.getMobile());

		userListInfo.setName(vo.getName());

		userListInfo.setRate(vo.getRate());

		userListInfo.setPwd(vo.getPwd());
		
		try {
			userListInfo.modify();
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
	@RequestMapping(value = "user-recharge", method = RequestMethod.POST) //请求路径
	public BaseResponse recharge(HttpServletRequest request,UserListVO vo) throws Exception{

		//验证字段是否为空，请自行删除多于的验证和补全其他验证
		String userId = Cookies.getValue(request, "userId");
		String treeCode = Cookies.getValue(request, "treeCode");
		if(StringUtil.isBlank(userId) || StringUtil.isBlank(treeCode)){
			return BaseResponse.failure("请重新登录");
		}

		if(null == vo.getSurplus() || vo.getSurplus() <= 0){
			return BaseResponse.failure("充值金额必须大于0");
		}

		//检查ID值是否为空
		if(vo.getId() == null){
			return BaseResponse.failure("保存失败，请刷新页面再试试");
		}
		userListInfo = UserListInfo.findOne(vo.getId());

		RechargeListInfo rechargeListInfo = new RechargeListInfo();
		rechargeListInfo.setPrice(vo.getSurplus());
		rechargeListInfo.setRechargeTime(new Timestamp(System.currentTimeMillis()));
		rechargeListInfo.setUserId(userListInfo.getId());
		rechargeListInfo.add();

		//设值，请自行修正或删除不正确的设值

		userListInfo.setMoney(userListInfo.getMoney()+vo.getSurplus());

		userListInfo.setSurplus(userListInfo.getSurplus() + vo.getSurplus());

		try {
			userListInfo.modify();
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
	@RequestMapping(value = "user-delete", method = RequestMethod.POST) //请求路径
	public BaseResponse delete(HttpServletRequest request,String ids) throws Exception{
		
		try {
			userListInfo.delete(NumberUtil.toLongArray(ids));
			return BaseResponse.SUCCESS;
			
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	}
	
	//自定义方法，方法要写上注释用途和权限注解
	//*****************************************************************************************************************

	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	@RequestMapping(value = "pwd-update", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse pwdUpdate(HttpServletRequest request,String oldPassword, String newPassword) throws Exception{

		//设置查询参数，请自行修改或删除不需要的参数
		UserListParam param = new UserListParam();

		param.putValue(UserListParamKey.pwd, oldPassword);

		//将查询参数转为HashMap
		HashMap<UserListParamKey, Object> keyMap = param.getKeyMap();

		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();

		orderList.add(new OrderItem(UserListParamKey.id, OrderType.ASC));//默认主键降序排序

		//获取总数
		int totalRows = UserListInfo.getTotalRows(keyMap);

		if(totalRows > 0){

			//获取列表
			List<UserListInfo> list = UserListInfo.queryAll(keyMap, orderList);

			UserListInfo userListInfo = list.get(0);

			userListInfo.setPwd(newPassword);

			userListInfo.modify();
			return BaseResponse.SUCCESS;
		} else {
			return BaseResponse.failure("密码错误！");
		}
	}

	@ResponseBody
	@RequestMapping(value = "user-select") //请求路径
	public List<HashMap<String,Object>> selectUserName(HttpServletRequest request){
		String treeCode = Cookies.getValue(request, "treeCode");

		if(StringUtil.isBlank(treeCode)){
			return new ArrayList<>();
		}

		List<HashMap<String,Object>> list = userListInfo.selectUserName(treeCode+"%");
		return  list;
	}
}

