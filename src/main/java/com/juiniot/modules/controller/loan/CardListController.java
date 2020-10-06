package com.juiniot.modules.controller.loan;

import cn.hutool.crypto.digest.MD5;
import com.juiniot.common.business.BusinessException;
import com.juiniot.common.business.OrderItem;
import com.juiniot.common.business.OrderType;
import com.juiniot.common.utils.*;
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
import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


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

		BaseResponse baseResponse = checkApi(requestBodyParams);
		if(baseResponse.getResultCode() != 0){
			return baseResponse;
		}

		//验证字段是否为空，请自行删除多于的验证和补全其他验证
		if(StringUtils.isEmpty(requestBodyParams.get("name"))){
			return BaseResponse.failure("姓名不能为空");
		}

		if(StringUtils.isEmpty(requestBodyParams.get("price"))){
			return BaseResponse.failure("金额不能为空");
		}

		if(StringUtils.isEmpty(requestBodyParams.get("bankNo"))){
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
		param.putValue(LoanListParamKey.phone, this.allFuzzy(requestBodyParams.get("orderNumber")));
		HashMap<LoanListParamKey, Object> keyMap = param.getKeyMap();
		int totalRows = LoanListInfo.getTotalRows(keyMap);
		if(totalRows > 0){
			return BaseResponse.failure("订单号已存在");
		} else {

		}

		LoanListInfo loanListInfo = new LoanListInfo();

		loanListInfo.setName(requestBodyParams.get("name"));

		loanListInfo.setBankNo(requestBodyParams.get("bankNo"));

		loanListInfo.setBankLocation(requestBodyParams.get("bankLocation"));

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
		BaseResponse baseResponse = checkApi(requestBodyParams);
		if(baseResponse.getResultCode() != 0){
			return baseResponse;
		}

		if(StringUtils.isEmpty(requestBodyParams.get("account"))){
			return BaseResponse.failure("商户账号不能为空");
		}

		if(StringUtils.isEmpty(requestBodyParams.get("orderNumber"))){
			return BaseResponse.failure("商户订单号不能为空");
		}

		LoanListParam param = new LoanListParam();
		param.putValue(LoanListParamKey.account, requestBodyParams.get("account"));
		param.putValue(LoanListParamKey.orderNumber, requestBodyParams.get("orderNumber"));
		HashMap<LoanListParamKey, Object> keyMap = param.getKeyMap();

		List<LoanListInfo> list = LoanListInfo.queryAll(keyMap, null);
		if(null == list || list.size() == 0){
			return BaseResponse.failure("订单号不存在");
		} else {
			LoanListInfo loanListInfo = list.get(0);

			JSONObject object = new JSONObject();
			object.put("name", loanListInfo.getName());
			object.put("bankNo", loanListInfo.getBankNo());
			object.put("bankLocation", loanListInfo.getBankLocation());
			object.put("status", loanListInfo.getStatus());
			object.put("orderNumber", loanListInfo.getOrderNumber());
			object.put("account", loanListInfo.getAccount());
			object.put("price", loanListInfo.getPrice());

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
			object.put("createTime", df.format(loanListInfo.getLoanTime()));
			object.put("issueTime", df.format(loanListInfo.getIssueTime()));

			return new BaseResponse(0,"success", object);
		}
	}

	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	@RequestMapping(value = "check-biz", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse checkBiz(@RequestBody Map<String, String> requestBodyParams) throws Exception{
		BaseResponse baseResponse = checkApi(requestBodyParams);
		if(baseResponse.getResultCode() != 0){
			return baseResponse;
		}

		if(StringUtils.isEmpty(requestBodyParams.get("account"))){
			return BaseResponse.failure("商户账号不能为空");
		}

		UserListParam param = new UserListParam();
		param.putValue(UserListParam.UserListParamKey.account, requestBodyParams.get("account"));
		HashMap<UserListParam.UserListParamKey, Object> keyMap = param.getKeyMap();

		List<UserListInfo> list = UserListInfo.queryAll(keyMap, null);
		if(null == list || list.size() == 0){
			return BaseResponse.failure("商户不存在");
		} else {
			UserListInfo userListInfo = list.get(0);

			JSONObject object = new JSONObject();
			object.put("account", userListInfo.getAccount());
			object.put("rate", userListInfo.getRate());
			object.put("surplus", userListInfo.getSurplus());
			object.put("money", userListInfo.getMoney());
			object.put("profit", userListInfo.getProfit());

			return new BaseResponse(0,"success", object);
		}
	}

	private BaseResponse checkApi(Map<String, String> requestBodyParams)  throws Exception{
		UserListParam param = new UserListParam();
		param.putValue(UserListParam.UserListParamKey.account, requestBodyParams.get("appKey"));
		HashMap<UserListParam.UserListParamKey, Object> keyMap1 = param.getKeyMap();
		//获取列表
		List<UserListInfo> list = UserListInfo.queryAll(keyMap1, null);
		if(list == null || list.size() == 0){
			return BaseResponse.failure("不合法的appKey");
		} else {
			List<String> ignoreParamNames = new ArrayList<>();
			ignoreParamNames.add("appKey");
			ignoreParamNames.add("sign");

			String sign = sign(requestBodyParams,ignoreParamNames,list.get(0).getMobile());
			if(!sign.equalsIgnoreCase(requestBodyParams.get("sign"))){
				return BaseResponse.failure("签名有误");
			} else {
				return BaseResponse.SUCCESS;
			}
		}
	}

	public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {
		Logger logger = LoggerFactory.getLogger("签名");

		StringBuilder sb = new StringBuilder();
		List<String> paramNames = new ArrayList<String>(paramValues.size());
		paramNames.addAll(paramValues.keySet());
		if(ignoreParamNames != null && ignoreParamNames.size() > 0){
			for (String ignoreParamName : ignoreParamNames) {
				paramNames.remove(ignoreParamName);
			}
		}
		Collections.sort(paramNames);

		sb.append(secret);
		for (String paramName : paramNames) {
			Object value = paramValues.get(paramName);
			if (value != null) {
				sb.append(paramName).append(value);
			}
		}
		sb.append(secret);
		logger.info(sb.toString());
		return MD5.create().digestHex16(sb.toString());
	}
}

