package com.juiniot.modules.controller.loan;
 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.http.HttpUtil;
import com.juiniot.common.utils.*;
import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import com.juiniot.modules.business.deduction.DeductionListInfo;
import com.juiniot.modules.business.user.UserListInfo;
import com.juiniot.modules.business.user.UserListParam;
import net.sf.json.JSONObject;
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
	public BaseResponse robbing(HttpServletRequest request, Model model, LoanListVO vo) throws Exception{
		synchronized (vo) {
			if(StringUtil.isBlank(vo.getStatus())){
				return BaseResponse.failure("请选择下发结果");
			}

			//检查ID值是否为空
			if(vo.getId() == null){
				return BaseResponse.failure("更新下发结果失败，请刷新页面再试试");
			}

			loanListInfo = LoanListInfo.findOne(vo.getId());

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

			Long t = System.currentTimeMillis();
			loanListInfo.setIssueTime(new Timestamp(t));
			loanListInfo.setStatus(vo.getStatus());
			loanListInfo.modify();

			Map<String, String> map = new HashMap<>();
			JSONObject rMap = new JSONObject();
			map.put("orderNumber",loanListInfo.getOrderNumber());
			map.put("account",loanListInfo.getAccount());
			map.put("price",new Double(loanListInfo.getPrice()*100).intValue()+"");
			map.put("status",loanListInfo.getStatus()+"");
			rMap.put("orderNumber",loanListInfo.getOrderNumber());
			rMap.put("account",loanListInfo.getAccount());
			rMap.put("price",new Double(loanListInfo.getPrice()*100).intValue());
			rMap.put("status",loanListInfo.getStatus());

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
			map.put("issueTime",df.format(t));
			rMap.put("issueTime",df.format(t));

			UserListParam param = new UserListParam();
			param.putValue(UserListParam.UserListParamKey.account, userListInfo.getAccount());
			//将查询参数转为HashMap
			HashMap<UserListParam.UserListParamKey, Object> keyMap = param.getKeyMap();
			List<UserListInfo> list = UserListInfo.queryAll(keyMap, null);

			String sign = CardListController.sign(map,null, list.get(0).getMobile());
			rMap.put("sign",sign.toUpperCase());

			logger.info("第三方平台回调参数："+rMap.toString()+"，"+list.get(0).getMobile());

			if(StringUtil.isNotBlank(loanListInfo.getNotifyUrl())){
				String result = HttpUtil.post(loanListInfo.getNotifyUrl(), rMap.toString(), 5000);
				if(StringUtil.isNotBlank(result)){
					logger.info("第一次第三方平台回调结果："+result);
					loanListInfo.setNotifyStatus(1);
					loanListInfo.setNotifyResult(result);
					loanListInfo.modify();

					if(result.contains("\"resultCode\":-1")){
						result = HttpUtil.post(loanListInfo.getNotifyUrl(), rMap.toString(), 5000);
						if(StringUtil.isNotBlank(result)){
							logger.info("第二次第三方平台回调结果："+result);
							loanListInfo.setNotifyStatus(1);
							loanListInfo.setNotifyResult(result);
							loanListInfo.modify();

							if(result.contains("\"resultCode\":-1")){
								result = HttpUtil.post(loanListInfo.getNotifyUrl(), rMap.toString(), 5000);
								if(StringUtil.isNotBlank(result)){
									logger.info("第三次第三方平台回调结果："+result);
									loanListInfo.setNotifyStatus(1);
									loanListInfo.setNotifyResult(result);
									loanListInfo.modify();
								}
							}
						}
					}
				}
			}
			return BaseResponse.success("下发成功");
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

		loanListInfo.setBankNo(vo.getBankNo());

		loanListInfo.setBankLocation(vo.getBankLocation());

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

		if(StringUtils.isEmpty(vo.getBankNo())){
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

		loanListInfo.setBankLocation(vo.getBankLocation());

		loanListInfo.setPrice(vo.getPrice());

		loanListInfo.setOrderNumber(IdUtils.snowflakeID()+"");

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

