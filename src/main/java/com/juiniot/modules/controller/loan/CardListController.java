package com.juiniot.modules.controller.loan;

import com.juiniot.common.business.BusinessException;
import com.juiniot.common.business.OrderItem;
import com.juiniot.common.business.OrderType;
import com.juiniot.common.utils.Cookies;
import com.juiniot.common.utils.IdUtils;
import com.juiniot.common.utils.NumberUtil;
import com.juiniot.common.utils.StringUtil;
import com.juiniot.common.web.BaseController;
import com.juiniot.common.web.PageModel;
import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import com.juiniot.common.web.response.BaseResponse;
import com.juiniot.modules.business.deduction.DeductionListInfo;
import com.juiniot.modules.business.loan.LoanListInfo;
import com.juiniot.modules.business.loan.LoanListParam;
import com.juiniot.modules.business.loan.LoanListParam.LoanListParamKey;
import com.juiniot.modules.business.user.UserListInfo;
import com.juiniot.modules.business.user.UserListParam;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description  对应的控制类
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
@Scope("prototype")
@Controller
@RequestMapping("api")
public class CardListController extends BaseController {


	/**
	 * 保存方法
	 */
	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	@RequestMapping(value = "create-order", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse createOrder(@RequestBody Map<String, String> requestBodyParams) throws Exception{

		//验证字段是否为空，请自行删除多于的验证和补全其他验证
		if(StringUtils.isEmpty(requestBodyParams.get("name"))){
			return BaseResponse.failure("姓名不能为空");
		}

		if(StringUtils.isEmpty(requestBodyParams.get("price"))){
			return BaseResponse.failure("金额不能为空");
		}

		if(StringUtils.isEmpty(requestBodyParams.get("cardNumber"))){
			return BaseResponse.failure("卡号不能为空");
		}

		UserListParam param1 = new UserListParam();
		param1.putValue(UserListParam.UserListParamKey.account, requestBodyParams.get("account"));
		HashMap<UserListParam.UserListParamKey, Object> keyMap1 = param1.getKeyMap();
		//获取列表
		List<UserListInfo> list = UserListInfo.queryAll(keyMap1, null);
		if(list == null || list.size() == 0){
			return BaseResponse.failure("商户账号不存在");
		}
		UserListInfo userListInfo = list.get(0);

		LoanListParam param = new LoanListParam();
		param.putValue(LoanListParamKey.phone, this.allFuzzy(requestBodyParams.get("cardNumber")));
		HashMap<LoanListParamKey, Object> keyMap = param.getKeyMap();
		int totalRows = LoanListInfo.getTotalRows(keyMap);
		if(totalRows > 0){
			return BaseResponse.failure("卡号已存在");
		} else {
			param = new LoanListParam();
			param.putValue(LoanListParamKey.orderNumber, this.allFuzzy(requestBodyParams.get("orderNumber")));
			keyMap = param.getKeyMap();
			totalRows = LoanListInfo.getTotalRows(keyMap);
			if(totalRows > 0){
				return BaseResponse.failure("订单号已存在");
			}
		}


		LoanListInfo loanListInfo = new LoanListInfo();

		loanListInfo.setName(requestBodyParams.get("name"));

		loanListInfo.setBankNo(requestBodyParams.get("bankNo"));

		loanListInfo.setQuota(requestBodyParams.get("bankLocation"));

		loanListInfo.setRemark(requestBodyParams.get("accountUser"));

		loanListInfo.setPhone(requestBodyParams.get("cardNumber"));

		loanListInfo.setNotifyUrl(requestBodyParams.get("notifyUrl"));

		loanListInfo.setPrice(Double.parseDouble(requestBodyParams.get("price"))/100.0);

		loanListInfo.setOrderNumber(requestBodyParams.get("orderNumber"));

		loanListInfo.setStatus(0);

		loanListInfo.setUserId(userListInfo.getId());

		loanListInfo.setLoanTime(new Timestamp(System.currentTimeMillis()));

		try {
			loanListInfo.add();

			return BaseResponse.success("创建订单成功");

		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}

	}

	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	@RequestMapping(value = "check-order", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse checkOrder(@RequestBody Map<String, String> requestBodyParams) throws Exception{
		if(StringUtils.isEmpty(requestBodyParams.get("account"))){
			return BaseResponse.failure("商户账号不能为空");
		}

		if(StringUtils.isEmpty(requestBodyParams.get("orderNumber"))){
			return BaseResponse.failure("商户订单号不能为空");
		}

		LoanListParam param = new LoanListParam();
		param.putValue(LoanListParamKey.account, requestBodyParams.get("account"));
		param.putValue(LoanListParamKey.phone, requestBodyParams.get("orderNumber"));
		HashMap<LoanListParamKey, Object> keyMap = param.getKeyMap();

		List<LoanListInfo> list = LoanListInfo.queryAll(keyMap, null);
		if(null == list || list.size() == 0){
			return BaseResponse.failure("订单号不存在");
		} else {
			return new BaseResponse(0,"success", JSONObject.fromObject(list.get(0)).toString());
		}
	}

	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	@RequestMapping(value = "check-biz", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse checkBiz(@RequestBody Map<String, String> requestBodyParams) throws Exception{
		if(StringUtils.isEmpty(requestBodyParams.get("account"))){
			return BaseResponse.failure("商户账号不能为空");
		}

		if(StringUtils.isEmpty(requestBodyParams.get("mobile"))){
			return BaseResponse.failure("联系方式不能为空");
		}

		UserListParam param = new UserListParam();
		param.putValue(UserListParam.UserListParamKey.account, requestBodyParams.get("account"));
		param.putValue(UserListParam.UserListParamKey.mobile, requestBodyParams.get("orderNumber"));
		HashMap<UserListParam.UserListParamKey, Object> keyMap = param.getKeyMap();

		List<UserListInfo> list = UserListInfo.queryAll(keyMap, null);
		if(null == list || list.size() == 0){
			return BaseResponse.failure("商户不存在");
		} else {
			return new BaseResponse(0,"success", JSONObject.fromObject(list.get(0)).toString());
		}
	}

//	private BaseResponse s(Map<String, String> requestBodyParams){
//		UserListParam param1 = new UserListParam();
//		param1.putValue(UserListParam.UserListParamKey.account, requestBodyParams.get("account"));
//		HashMap<UserListParam.UserListParamKey, Object> keyMap1 = param1.getKeyMap();
//		//获取列表
//		List<UserListInfo> list = UserListInfo.queryAll(keyMap1, null);
//		if(list == null || list.size() == 0){
//			return BaseResponse.failure("商户账号不存在");
//		}
//	}
}

