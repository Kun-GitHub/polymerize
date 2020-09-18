package com.juiniot.modules.controller.loan;
 
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import com.juiniot.common.utils.*;
import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import com.juiniot.modules.business.sms.SmsListInfo;
import com.juiniot.modules.business.user.UserListInfo;
import com.juiniot.modules.business.user.UserListParam;
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


import com.juiniot.modules.business.loan.LoanListInfo;
import com.juiniot.modules.business.loan.LoanListParam;
import com.juiniot.modules.business.loan.LoanListParam.LoanListParamKey;


/**
 * @description  对应的控制类
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
@Scope("prototype")
@Controller
@RequestMapping("loan")
public class LoanListController extends BaseController {

    @Autowired
	private LoanListInfo loanListInfo;

	@RequestMapping("add-loan")
	@Authority(needSession = NeedSession.NO)
	public String addLoan(HttpServletRequest request, Model model, String source) throws BusinessException {
		model.addAttribute("source", source);
		return "/loan/add-loan";
	}

	@RequestMapping("add-loan-success")
	@Authority(needSession = NeedSession.NO)
	public String addLoanSuccess(HttpServletRequest request, Model model, String source) throws BusinessException {
		model.addAttribute("source", source);
		return "/loan/add-loan-success";
	}

    /**
	 * 查询列表数据
	 */
	@RequestMapping("loan-list")
	public String queryList(HttpServletRequest request, Model model, LoanListVO vo) throws Exception{

		String type = Cookies.getValue(request, "type");
		String treeCode = Cookies.getValue(request, "treeCode");

		if(StringUtil.isBlank(type) || StringUtil.isBlank(treeCode)){
			return "/login";
		}

		//设置查询参数，请自行修改或删除不需要的参数
		LoanListParam param1 = new LoanListParam();

		param1.putValue(LoanListParamKey.name, this.allFuzzy(vo.getName()));

		param1.putValue(LoanListParamKey.userName, this.allFuzzy(vo.getUserName()));
	 	 
     	param1.putValue(LoanListParamKey.phone, this.allFuzzy(vo.getPhone()));
	 	 
     	param1.putValue(LoanListParamKey.status, vo.getStatus());
	 	 
     	param1.putValue(LoanListParamKey.loanTime1, vo.getLoanTime1());

     	param1.putValue(LoanListParamKey.loanTime2, vo.getLoanTime());

		param1.putValue(LoanListParamKey.source, this.allFuzzy(vo.getSource()));

	 	//数据过滤。若需要过滤数据，请自行在下面设置参数
	 	
	 	
	 	//将查询参数转为HashMap
		HashMap<LoanListParamKey, Object> keyMap1 = param1.getKeyMap();
		
		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();
		if(StringUtils.hasText(vo.getColumnName()) && StringUtils.hasText(vo.getOrderType())) {
			orderList.add(new OrderItem(vo.getColumnName(), vo.getOrderType()));//自定义排序
		} else {
			orderList.add(new OrderItem(LoanListParamKey.id, OrderType.DESC));//默认主键降序排序
		}
		
		//获取总数
		int totalRows = LoanListInfo.getTotalRows(keyMap1);
		//获取列表
		List<LoanListInfo> list1 = LoanListInfo.queryLoanLists(vo.getStartRow(), vo.getPageSize(), keyMap1, orderList);

		//获取分页模型对象
		PageModel<LoanListInfo> pm = this.getPageModel(vo.getPage(), totalRows, vo.getPageSize(), list1);

		String userId = Cookies.getValue(request, "userId");
		UserListInfo userListInfo = UserListInfo.findOne(Long.valueOf(userId));

		//将数据集合返回到页面端
		model.addAttribute("uniqueIdentify", userListInfo.getUniqueIdentify());
		model.addAttribute("type", type);
		model.addAttribute("page", pm);
		model.addAttribute("vo", vo);

		return "/loan/loan-list"; //返回页面
	
	}
	
	/**
	 * 新增或修改页面初始化
	 */
	@RequestMapping("loan-add") //请求路径
	public String addInit(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			loanListInfo = LoanListInfo.findOne(id);
		}
		
		model.addAttribute("info", loanListInfo);
		return "/loan/loan-add"; //返回页面
	}
	
	/**
	 * ajax加载单个数据
	 */
	@ResponseBody
	@RequestMapping("loan-load")
	public LoanListInfo load(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			loanListInfo = LoanListInfo.findOne(id);
		}
		
		return loanListInfo; 
	}
	
	
	/**
	 * 保存方法
	 */
	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	@RequestMapping(value = "loan-save", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse save(HttpServletRequest request,LoanListVO vo, String code) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证

		if(StringUtils.isEmpty(vo.getName())){
			return BaseResponse.failure("姓名不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCity())){
			return BaseResponse.failure("居住地不能为空");
		}

		if(StringUtils.isEmpty(vo.getQuota())){
			return BaseResponse.failure("贷款额度不能为空");
		}

		if(StringUtils.isEmpty(code) || code.length() != 4){
			return BaseResponse.failure("请输入正确的验证码");
		}

		if(StringUtils.isEmpty(vo.getPhone()) || vo.getPhone().length() != 11){
			return BaseResponse.failure("联系方式错误");
		}

//		LoanListParam param = new LoanListParam();
//		param.putValue(LoanListParamKey.phone, this.allFuzzy(vo.getPhone()));
//		HashMap<LoanListParamKey, Object> keyMap = param.getKeyMap();
//		int totalRows = LoanListInfo.getTotalRows(keyMap);
//		if(totalRows > 0){
//			return BaseResponse.failure("该手机号码已填写过信息");
//		}

		//设值，请自行修正或删除不正确的设值

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(new Date())+" 00:00:01";

		List<SmsListInfo> smsListInfos = SmsListInfo.findSmsByPhoneAndDate(vo.getPhone(), dateString, code);

		if(null == smsListInfos || smsListInfos.size() == 0){
			return BaseResponse.failure("请输入正确的验证码");
		}

		loanListInfo.setName(vo.getName());
		
		loanListInfo.setPhone(vo.getPhone());
		
		loanListInfo.setSex(vo.getSex());
		
		loanListInfo.setCity(vo.getCity());

		loanListInfo.setQuota(vo.getQuota());

		loanListInfo.setPrice(100.0);

		//设置查询参数，请自行修改或删除不需要的参数
		UserListParam userListParam = new UserListParam();

		userListParam.putValue(UserListParam.UserListParamKey.uniqueIdentify, vo.getSource());

		//将查询参数转为HashMap
		HashMap<UserListParam.UserListParamKey, Object> UserListkeyMap = userListParam.getKeyMap();

		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();

		orderList.add(new OrderItem(UserListParam.UserListParamKey.id, OrderType.ASC));//默认主键降序排序

		//获取总数
		int totalRow = UserListInfo.getTotalRows(UserListkeyMap);

		if(totalRow > 0){
			//获取列表
			List<UserListInfo> list = UserListInfo.queryAll(UserListkeyMap, orderList);

			loanListInfo.setSource(list.get(0).getName());

			loanListInfo.setUserId(list.get(0).getId());

			loanListInfo.setStatus(1);
		} else {
			loanListInfo.setSource(vo.getSource());

			loanListInfo.setStatus(0);

			loanListInfo.setUserId(0l);
		}

		loanListInfo.setLoanTime(new Timestamp(System.currentTimeMillis()));


		try {
			loanListInfo.add();

			String phone = CommonConfUtil.getInstance().getGlobalParams("phone");

			String tpl_value = URLEncoder.encode("#code#=0000", "UTF-8") ;
			Map<String, String> map = new HashMap<>();
			map.put("mobile", phone);
			map.put("tpl_id", "93302");
			map.put("tpl_value", tpl_value);
			map.put("dtype", "json");
			map.put("key", "f734f8478e3f090b9c2f8f851b5ec964");

			String sms = "【指尖触动】您的验证码是0000。如非本人操作，请忽略本短信";

			String url = "http://v.juhe.cn/sms/send";
			String result = ApiWebUtils.doGet(url, map, "UTF-8");

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
	@RequestMapping(value = "loan-user", method = RequestMethod.POST) //请求路径
	public BaseResponse setUser(HttpServletRequest request,LoanListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	
		
		if(StringUtil.isBlank(vo.getUserId())){
			return BaseResponse.failure("请选择跟进人员");
		}
		
		//检查ID值是否为空
		if(vo.getId() == null){
			return BaseResponse.failure("保存失败，请刷新页面再试试");
		}
		loanListInfo = LoanListInfo.findOne(vo.getId());
		
		//设值，请自行修正或删除不正确的设值
		
		loanListInfo.setUserId(vo.getUserId());
		
		loanListInfo.setStatus(1);
		
		try {
			loanListInfo.modify();

			UserListInfo userListInfo = UserListInfo.findOne(vo.getUserId());

			if(userListInfo.getMoney()-loanListInfo.getPrice()<0){

				return BaseResponse.success("推广余额不足，请先充值");

			} else {
				userListInfo.setMoney(userListInfo.getMoney()-loanListInfo.getPrice());

				userListInfo.modify();
				return BaseResponse.SUCCESS;
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	}

	/**
	 * 更新方法
	 */
	@ResponseBody
	@RequestMapping(value = "loan-status", method = RequestMethod.POST) //请求路径
	public BaseResponse updateStatus(HttpServletRequest request,LoanListVO vo) throws Exception{

		//验证字段是否为空，请自行删除多于的验证和补全其他验证

		if(StringUtil.isBlank(vo.getStatus())){
			return BaseResponse.failure("请选择跟进结果");
		}

		//检查ID值是否为空
		if(vo.getId() == null){
			return BaseResponse.failure("保存失败，请刷新页面再试试");
		}
		loanListInfo = LoanListInfo.findOne(vo.getId());

		//设值，请自行修正或删除不正确的设值

		loanListInfo.setStatus(vo.getStatus());

		loanListInfo.setRemark(vo.getRemark());

		try {
			loanListInfo.modify();

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
	@RequestMapping(value = "loan-delete", method = RequestMethod.POST) //请求路径
	public BaseResponse delete(HttpServletRequest request,String ids) throws Exception{
		
		try {
			loanListInfo.delete(NumberUtil.toLongArray(ids));
			return BaseResponse.SUCCESS;
			
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	}
	
	//自定义方法，方法要写上注释用途和权限注解
	//*****************************************************************************************************************

	@ResponseBody
	@RequestMapping("loan-robbing")
	public BaseResponse robbing(HttpServletRequest request, Model model, Long id) throws Exception{
		synchronized (id) {
			if(id != null){
				loanListInfo = LoanListInfo.findOne(id);

				loanListInfo.setStatus(1);
				loanListInfo.modify();
				return BaseResponse.success("下发成功");
			} else {
				return BaseResponse.failure("下发失败");
			}
		}
	}


	/**
	 * 更新方法
	 */
	@ResponseBody
	@RequestMapping(value = "loan-update", method = RequestMethod.POST) //请求路径
	public BaseResponse setUpdate(HttpServletRequest request,LoanListVO vo) throws Exception{

		//验证字段是否为空，请自行删除多于的验证和补全其他验证

		if(vo.getId() == null){
			return BaseResponse.failure("保存失败，请刷新页面再试试");
		}
		loanListInfo = LoanListInfo.findOne(vo.getId());

		//设值，请自行修正或删除不正确的设值

		loanListInfo.setName(vo.getName());

		loanListInfo.setCity(vo.getCity());

		loanListInfo.setQuota(vo.getQuota());

		try {
			loanListInfo.modify();

			return BaseResponse.SUCCESS;
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	}

	/**
	 * 保存方法
	 */
	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	@RequestMapping(value = "loan-new", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse loanNew(HttpServletRequest request,LoanListVO vo) throws Exception{

		//验证字段是否为空，请自行删除多于的验证和补全其他验证

		if(StringUtils.isEmpty(vo.getName())){
			return BaseResponse.failure("姓名不能为空");
		}

		if(StringUtils.isEmpty(vo.getPrice())){
			return BaseResponse.failure("金额不能为空");
		}

		if(StringUtils.isEmpty(vo.getPhone())){
			return BaseResponse.failure("卡号不能为空");
		}

		LoanListParam param = new LoanListParam();
		param.putValue(LoanListParamKey.phone, this.allFuzzy(vo.getPhone()));
		HashMap<LoanListParamKey, Object> keyMap = param.getKeyMap();
		int totalRows = LoanListInfo.getTotalRows(keyMap);
		if(totalRows > 0){
			return BaseResponse.failure("卡号已存在");
		}

		loanListInfo.setName(vo.getName());

		loanListInfo.setPhone(vo.getPhone());

		loanListInfo.setQuota(vo.getQuota());

		loanListInfo.setPrice(vo.getPrice());

		loanListInfo.setSource(IdUtils.snowflakeID()+"");

		loanListInfo.setStatus(0);

		String u = Cookies.getValue(request, "userId");

		loanListInfo.setUserId(Long.parseLong(u));

		loanListInfo.setLoanTime(new Timestamp(System.currentTimeMillis()));

		try {
			loanListInfo.add();

			return BaseResponse.SUCCESS;

		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}

	}
	
}

