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
@RequestMapping("order")
public class CardListController extends BaseController {


	/**
	 * 保存方法
	 */
	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	@RequestMapping(value = "order-new", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse loanNew(@RequestBody Map<String, String> requestBodyParams) throws Exception{

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

		UserListInfo userListInfo = new UserListInfo();

		LoanListParam param = new LoanListParam();
		param.putValue(LoanListParamKey.phone, this.allFuzzy(requestBodyParams.get("cardNumber")));
		HashMap<LoanListParamKey, Object> keyMap = param.getKeyMap();
		int totalRows = LoanListInfo.getTotalRows(keyMap);
		if(totalRows > 0){
			return BaseResponse.failure("卡号已存在");
		} else {
			param = new LoanListParam();
			param.putValue(LoanListParamKey.source, this.allFuzzy(requestBodyParams.get("orderNumber")));
			keyMap = param.getKeyMap();
			totalRows = LoanListInfo.getTotalRows(keyMap);
			if(totalRows > 0){
				return BaseResponse.failure("订单号已存在");
			} else {
				UserListParam param1 = new UserListParam();
				param1.putValue(UserListParam.UserListParamKey.account, requestBodyParams.get("account"));
				HashMap<UserListParam.UserListParamKey, Object> keyMap1 = param1.getKeyMap();
				//获取列表
				List<UserListInfo> list = UserListInfo.queryAll(keyMap1, null);
				if(list == null || list.size() == 0){
					return BaseResponse.failure("商户账号不存在");
				}
				userListInfo = list.get(0);
			}
		}


		LoanListInfo loanListInfo = new LoanListInfo();

		loanListInfo.setName(requestBodyParams.get("name"));

		loanListInfo.setPhone(requestBodyParams.get("cardNumber"));

		loanListInfo.setPrice(Double.parseDouble(requestBodyParams.get("cardNumber"))/100.0);

		loanListInfo.setSource(requestBodyParams.get("orderNumber"));

		loanListInfo.setStatus(0);

		loanListInfo.setUserId(userListInfo.getId());

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
