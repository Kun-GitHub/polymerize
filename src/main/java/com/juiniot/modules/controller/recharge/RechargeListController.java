package com.juiniot.modules.controller.recharge;

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
import com.juiniot.modules.business.recharge.RechargeListInfo;
import com.juiniot.modules.business.recharge.RechargeListParam;
import com.juiniot.modules.business.recharge.RechargeListParam.RechargeListParamKey;
import com.juiniot.modules.business.user.UserListInfo;
import com.juiniot.modules.business.user.UserListParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @description  对应的控制类
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-10-03 19:34:30
 */
@Scope("prototype")
@Controller
@RequestMapping("recharge")
public class RechargeListController extends BaseController {

    @Autowired
	private RechargeListInfo rechargeListInfo;

    /**
	 * 查询列表数据
	 */
	@RequestMapping("recharge-list")
	public String queryList(HttpServletRequest request, Model model, RechargeListVO vo) throws Exception{

		String type = Cookies.getValue(request, "type");
		String treeCode = Cookies.getValue(request, "treeCode");

		if(StringUtil.isBlank(type) || StringUtil.isBlank(treeCode)){
			return "/login";
		}

		//设置查询参数，请自行修改或删除不需要的参数
		RechargeListParam param1 = new RechargeListParam();

		param1.putValue(RechargeListParamKey.userName, this.allFuzzy(vo.getUserName()));

		if(!StringUtil.isBlank(treeCode)){
			param1.putValue(RechargeListParamKey.treeCode, this.allFuzzy(treeCode));
		}
	 	//数据过滤。若需要过滤数据，请自行在下面设置参数
	 	
	 	
	 	//将查询参数转为HashMap
		HashMap<RechargeListParamKey, Object> keyMap1 = param1.getKeyMap();
		
		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();
		if(StringUtils.hasText(vo.getColumnName()) && StringUtils.hasText(vo.getOrderType())) {
			orderList.add(new OrderItem(vo.getColumnName(), vo.getOrderType()));//自定义排序
		} else {
			orderList.add(new OrderItem(RechargeListParamKey.id, OrderType.DESC));//默认主键降序排序
		}
		
		//获取总数
		int totalRows = RechargeListInfo.getTotalRows(keyMap1);
		//获取列表
		List<RechargeListInfo> list1 = RechargeListInfo.queryRechargeLists(vo.getStartRow(), vo.getPageSize(), keyMap1, orderList);

		//获取分页模型对象
		PageModel<RechargeListInfo> pm = this.getPageModel(vo.getPage(), totalRows, vo.getPageSize(), list1);

		//将数据集合返回到页面端
		model.addAttribute("type", type);
		model.addAttribute("page", pm);
		model.addAttribute("vo", vo);

		return "/recharge/recharge-list"; //返回页面
	}
	
	/**
	 * ajax加载单个数据
	 */
	@ResponseBody
	@RequestMapping("recharge-load")
	public RechargeListInfo load(HttpServletRequest request, Model model, Long id) throws Exception{
		if(id != null){
			rechargeListInfo = RechargeListInfo.findOne(id);
		}
		return rechargeListInfo;
	}

	/**
	 * 删除方法
	 */
	@ResponseBody
	@RequestMapping(value = "recharge-delete", method = RequestMethod.POST) //请求路径
	public BaseResponse delete(HttpServletRequest request,String ids) throws Exception{
		
		try {
			rechargeListInfo.delete(NumberUtil.toLongArray(ids));
			return BaseResponse.SUCCESS;
			
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	}
	
	//自定义方法，方法要写上注释用途和权限注解
	//*****************************************************************************************************************

//
//	/**
//	 * 保存方法
//	 */
//	@ResponseBody
//	@Authority(needSession = NeedSession.NO)
//	@RequestMapping(value = "recharge-new", method = RequestMethod.POST) //请求路径，请修改为正确的路径
//	public BaseResponse rechargeNew(HttpServletRequest request, RechargeListVO vo) throws Exception{
//
//		//验证字段是否为空，请自行删除多于的验证和补全其他验证
//
//		if(StringUtils.isEmpty(vo.getName())){
//			return BaseResponse.failure("姓名不能为空");
//		}
//
//		if(StringUtils.isEmpty(vo.getPrice())){
//			return BaseResponse.failure("金额不能为空");
//		}
//
//		if(StringUtils.isEmpty(vo.getPhone())){
//			return BaseResponse.failure("卡号不能为空");
//		}
//
//		RechargeListParam param = new RechargeListParam();
//		param.putValue(RechargeListParamKey.phone, this.allFuzzy(vo.getPhone()));
//		HashMap<RechargeListParamKey, Object> keyMap = param.getKeyMap();
//		int totalRows = RechargeListInfo.getTotalRows(keyMap);
//		if(totalRows > 0){
//			return BaseResponse.failure("卡号已存在");
//		}
//
//		rechargeListInfo.setName(vo.getName());
//
//		rechargeListInfo.setPhone(vo.getPhone());
//
//		rechargeListInfo.setQuota(vo.getQuota());
//
//		rechargeListInfo.setPrice(vo.getPrice());
//
//		rechargeListInfo.setSource(IdUtils.snowflakeID()+"");
//
//		rechargeListInfo.setStatus(0);
//
//		String u = Cookies.getValue(request, "userId");
//
//		rechargeListInfo.setUserId(Long.parseLong(u));
//
//		rechargeListInfo.setRechargeTime(new Timestamp(System.currentTimeMillis()));
//
//		try {
//			rechargeListInfo.add();
//
//			return BaseResponse.SUCCESS;
//
//		} catch (BusinessException e) {
//			logger.error(e.getMessage(), e);
//			return BaseResponse.failure(e.getMessage());
//		}
//
//	}
	
}

