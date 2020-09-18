package com.juiniot.modules.controller.sms;
 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import com.juiniot.common.utils.ApiWebUtils;
import com.juiniot.common.utils.Cookies;
import com.juiniot.common.utils.StringUtil;
import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import com.juiniot.modules.business.loan.LoanListInfo;
import com.juiniot.modules.business.loan.LoanListParam;
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
import com.juiniot.common.utils.NumberUtil;


import com.juiniot.modules.business.sms.SmsListInfo;
import com.juiniot.modules.business.sms.SmsListParam;
import com.juiniot.modules.business.sms.SmsListParam.SmsListParamKey;


/**
 * @description 短信记录 对应的控制类
 * @version v1.0.0
 * @author Kun  
 * @CreateTime 2018-08-12 15:16:39
 */
@Scope("prototype")
@Controller
@RequestMapping("sms")
public class SmsListController extends BaseController {

	@Autowired
	private SmsListInfo smsListInfo;

	@RequestMapping("send-sms")
	@ResponseBody
	@Authority(needSession = NeedSession.NO)
	public String sendMsg(HttpServletRequest request, String phone, String source) throws BusinessException {

		JSONObject resultObj = new JSONObject();
		if(StringUtil.isBlank(phone) || phone.length()!=11){
			resultObj.accumulate("code",-1);
			resultObj.accumulate("msg","手机号码错误");
			return resultObj.toString();
		}

//		LoanListParam param = new LoanListParam();
//		param.putValue(LoanListParam.LoanListParamKey.phone, this.allFuzzy(phone));
//		HashMap<LoanListParam.LoanListParamKey, Object> keyMap = param.getKeyMap();
//		int totalRows = LoanListInfo.getTotalRows(keyMap);
//		if(totalRows > 0){
//			resultObj.accumulate("code",-1);
//			resultObj.accumulate("msg","号码已被使用");
//			return resultObj.toString();
//		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(new Date())+" 00:00:01";

		try {
			List<SmsListInfo> smsListInfos = SmsListInfo.findSmsByPhoneAndDate(phone, dateString, null);
			if(null != smsListInfos && smsListInfos.size() > 3){
				resultObj.accumulate("code",-1);
				resultObj.accumulate("msg","号码次数受限");
				return resultObj.toString();
			} else {
				String code = CreateNonceStr(4);
				String tpl_value = URLEncoder.encode("#code#="+code, "UTF-8") ;
				Map<String, String> map = new HashMap<>();
				map.put("mobile", phone);
				map.put("tpl_id", "93302");
				map.put("tpl_value", tpl_value);
				map.put("dtype", "json");
				map.put("key", "f734f8478e3f090b9c2f8f851b5ec964");

				String sms = "【指尖触动】您的验证码是"+code+"。如非本人操作，请忽略本短信";

				String url = "http://v.juhe.cn/sms/send";
				String result = ApiWebUtils.doGet(url, map, "UTF-8");

				SmsListInfo smsListInfo = new SmsListInfo();
				smsListInfo.setCode(code);
				smsListInfo.setPhone(phone);
				smsListInfo.setLastTime(new Timestamp(System.currentTimeMillis()));
				smsListInfo.setSms(sms);

//				smsListInfo.setNums(0l);
//				resultObj.accumulate("code",0);
//				resultObj.accumulate("msg","成功");

				if(!StringUtil.isBlank(result)){
					JSONObject jsonObject = JSONObject.fromObject(result);
					Long l = jsonObject.getLong("error_code");
					String reason = jsonObject.getString("reason");
					resultObj.accumulate("code",l);
					resultObj.accumulate("msg",reason);
					smsListInfo.setNums(l);
				} else {
					resultObj.accumulate("code",-1);
					resultObj.accumulate("msg","短信发送失败");
				}
				smsListInfo.add();
			}

		} catch (BusinessException | IOException e) {
			e.printStackTrace();
		}
		return resultObj.toString();
	}

    /**
	 * 查询列表数据
	 */
	@RequestMapping("sms-list")
	public String queryList(HttpServletRequest request, Model model, SmsListVO vo) throws Exception{
	
		//设置查询参数，请自行修改或删除不需要的参数
		SmsListParam param = new SmsListParam();
		 
     	param.putValue(SmsListParamKey.id, vo.getId());
	 	 
     	param.putValue(SmsListParamKey.phone, this.allFuzzy(vo.getPhone()));
	 	 
     	param.putValue(SmsListParamKey.lastTime1, vo.getLastTime1()); param.putValue(SmsListParamKey.lastTime2, vo.getLastTime());
	 	 
     	param.putValue(SmsListParamKey.nums, vo.getNums());
	 	 
     	param.putValue(SmsListParamKey.sms, this.allFuzzy(vo.getSms()));
	 	 
     	param.putValue(SmsListParamKey.code, this.allFuzzy(vo.getCode()));
	 	
	 	
	 	
	 	//数据过滤。若需要过滤数据，请自行在下面设置参数
	 	
	 	
	 	//将查询参数转为HashMap
		HashMap<SmsListParamKey, Object> keyMap = param.getKeyMap();
		
		//设置排序条件
		List<OrderItem> orderList = new ArrayList<>();
		if(StringUtils.hasText(vo.getColumnName()) && StringUtils.hasText(vo.getOrderType())) {
			orderList.add(new OrderItem(vo.getColumnName(), vo.getOrderType()));//自定义排序
		}
		else {
			orderList.add(new OrderItem(SmsListParamKey.id, OrderType.DESC));//默认主键降序排序
		}
		
		//获取总数
		int totalRows = SmsListInfo.getTotalRows(keyMap);
		//获取列表
		List<SmsListInfo> list = SmsListInfo.querySmsLists(vo.getStartRow(), vo.getPageSize(), keyMap, orderList);
		
		//获取分页模型对象
		PageModel<SmsListInfo> pm = this.getPageModel(vo.getPage(), totalRows, vo.getPageSize(), list);

		String type = Cookies.getValue(request, "type");

		//将数据集合返回到页面端
		model.addAttribute("type", type);
		model.addAttribute("page", pm);
		model.addAttribute("vo", vo);
		
		return "/sms/sms-list"; //返回页面
	
	}
	
	/**
	 * 新增或修改页面初始化
	 */
	@RequestMapping("sms-add") //请求路径
	public String addInit(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			smsListInfo = SmsListInfo.findOne(id);
		}
		
		model.addAttribute("info", smsListInfo);
		return "/sms/sms-add"; //返回页面
	}
	
	/**
	 * ajax加载单个数据
	 */
	@ResponseBody
	@RequestMapping("sms-load")
	public SmsListInfo load(HttpServletRequest request, Model model, Long id) throws Exception{
		
		if(id != null){
			smsListInfo = SmsListInfo.findOne(id);
		}
		
		return smsListInfo; 
	}
	
	
	/**
	 * 保存方法
	 */
	@ResponseBody
	@RequestMapping(value = "sms-save", method = RequestMethod.POST) //请求路径，请修改为正确的路径
	public BaseResponse save(HttpServletRequest request,SmsListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	
		
		if(StringUtils.isEmpty(vo.getId())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getPhone())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getLastTime())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getNums())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getSms())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCode())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		
		//设值，请自行修正或删除不正确的设值
		
		smsListInfo.setId(vo.getId());
		
		smsListInfo.setPhone(vo.getPhone());
		
		smsListInfo.setLastTime(vo.getLastTime());
		
		smsListInfo.setNums(vo.getNums());
		
		smsListInfo.setSms(vo.getSms());
		
		smsListInfo.setCode(vo.getCode());
		
		
		try {
			smsListInfo.add();
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
	@RequestMapping(value = "sms-update", method = RequestMethod.POST) //请求路径
	public BaseResponse update(HttpServletRequest request,SmsListVO vo) throws Exception{
		
		//验证字段是否为空，请自行删除多于的验证和补全其他验证	
		
		if(StringUtils.isEmpty(vo.getId())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getPhone())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getLastTime())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getNums())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getSms())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		if(StringUtils.isEmpty(vo.getCode())){
			return BaseResponse.failure("XXX不能为空");
		}
		
		
		//检查ID值是否为空
		if(vo.getId() == null){
			return BaseResponse.failure("保存失败，请刷新页面再试试");
		}
		smsListInfo = SmsListInfo.findOne(vo.getId());
		
		//设值，请自行修正或删除不正确的设值
		
		smsListInfo.setId(vo.getId());
		
		smsListInfo.setPhone(vo.getPhone());
		
		smsListInfo.setLastTime(vo.getLastTime());
		
		smsListInfo.setNums(vo.getNums());
		
		smsListInfo.setSms(vo.getSms());
		
		smsListInfo.setCode(vo.getCode());
		
		
		try {
			smsListInfo.modify();
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
	@RequestMapping(value = "sms-delete", method = RequestMethod.POST) //请求路径
	public BaseResponse delete(HttpServletRequest request,String ids) throws Exception{
		
		try {
			smsListInfo.delete(NumberUtil.toLongArray(ids));
			return BaseResponse.SUCCESS;
			
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			return BaseResponse.failure(e.getMessage());
		}
	}
	
	//自定义方法，方法要写上注释用途和权限注解
	//*****************************************************************************************************************

	private String CreateNonceStr(int length) {
		String chars = "0123456789";
		String res = "";
		for (int i = 0; i < length; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res.replace("-", "");
	}
	
	
}

