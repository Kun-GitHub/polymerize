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
import com.juiniot.modules.business.deduction.DeductionListInfo;
import com.juiniot.modules.business.recharge.RechargeListInfo;
import com.juiniot.modules.business.recharge.RechargeListParam;
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

		if(!StringUtil.isBlank(treeCode)){
			param1.putValue(LoanListParamKey.treeCode, this.allFuzzy(treeCode));
		}

		param1.putValue(LoanListParamKey.name, this.allFuzzy(vo.getName()));

		param1.putValue(LoanListParamKey.account, this.allFuzzy(vo.getAccount()));
	 	 
     	param1.putValue(LoanListParamKey.phone, this.allFuzzy(vo.getPhone()));
	 	 

     	param1.putValue(LoanListParamKey.loanTime1, vo.getLoanTime1());

     	param1.putValue(LoanListParamKey.loanTime2, vo.getLoanTime());

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

		//将数据集合返回到页面端
		model.addAttribute("type", type);
		model.addAttribute("page", pm);
		model.addAttribute("vo", vo);

		return "/loan/loan-list"; //返回页面
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

				UserListInfo userListInfo = UserListInfo.findOne(loanListInfo.getUserId());
				if(userListInfo.getSurplus()-loanListInfo.getPrice() < 0){
					return BaseResponse.failure("下发失败,商户可用余额不足");
				}
				if(userListInfo != null && 0 <= userListInfo.getRate() && userListInfo.getRate() <= 100){
					userListInfo.setSurplus(userListInfo.getSurplus()-(loanListInfo.getPrice()+loanListInfo.getPrice()*(userListInfo.getRate()/100d)));
					userListInfo.setProfit(userListInfo.getProfit()+loanListInfo.getPrice()*(userListInfo.getRate()/100d));
					userListInfo.modify();
				}

				DeductionListInfo rechargeListInfo = new DeductionListInfo();
				rechargeListInfo.setPrice(loanListInfo.getPrice());
				rechargeListInfo.setDeductionTime(new Timestamp(System.currentTimeMillis()));
				rechargeListInfo.setUserId(loanListInfo.getUserId());
				rechargeListInfo.add();

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

