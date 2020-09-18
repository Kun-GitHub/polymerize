package com.juiniot.modules.controller;

import com.juiniot.common.business.BusinessException;
import com.juiniot.common.business.OrderItem;
import com.juiniot.common.business.OrderType;
import com.juiniot.common.utils.CommonConfUtil;
import com.juiniot.common.utils.Cookies;
import com.juiniot.common.utils.EncryptUtil;
import com.juiniot.common.utils.StringUtil;
import com.juiniot.common.web.BaseController;
import com.juiniot.common.web.Sessions;
import com.juiniot.common.web.preview.Authority;
import com.juiniot.common.web.preview.NeedSession;
import com.juiniot.common.web.response.BaseResponse;
import com.juiniot.modules.business.feedback.FeedbackListInfo;
import com.juiniot.modules.business.student.StudentListInfo;
import com.juiniot.modules.business.student.StudentListParam;
import com.juiniot.modules.business.user.UserListInfo;
import com.juiniot.modules.business.user.UserListParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHIFEN on 2016/11/23.
 *
 */

@Scope("prototype")
@Controller
@RequestMapping("")
public class IndexController  extends BaseController {

    @RequestMapping("login")
    @Authority(needSession = NeedSession.NO)
    public String login(HttpServletRequest request) throws BusinessException {
        return isLogin(request);
    }

    @RequestMapping("index")
    @Authority(needSession = NeedSession.NO)
    public String index(HttpServletRequest request, Model model) throws BusinessException {

//        model.addAttribute("source", "3rd_360");
//        return "/loan/add-loan";

        return isLogin(request);
    }

    private String isLogin(HttpServletRequest request){
        //判断Cookie
        if(Cookies.getValue(request, "account") != null){

            Map<String, String> mapCookie = Cookies.ReadCookieMap(request);
            String account = mapCookie.get("account");
            String type = mapCookie.get("type");
            if(account != null){
                if("admin".equals(type)){
                    String loan = CommonConfUtil.getInstance().getGlobalParams("type");
                    if(!StringUtil.isBlank(loan) && "loan".equals(loan)){
                        return "redirect:/loan/loan-list";
                    } else {
                        return "redirect:/student/student-list";
                    }
                } else if("user".equals(type)){
                    return "redirect:/student/student-feedback";
                }
            }
        }
        return "login";
    }

    @RequestMapping("add-loan-uc")
    @Authority(needSession = NeedSession.NO)
    public String addLoanUC(HttpServletRequest request, Model model) throws BusinessException {
        model.addAttribute("source", "3rd_UC_信用卡");
        return "/loan/add-loan-xyk";
    }

    @RequestMapping("add-loan")
    @Authority(needSession = NeedSession.NO)
    public String addLoan(HttpServletRequest request, Model model) throws BusinessException {
        model.addAttribute("source", "3rd_UC");
        return "/loan/add-loan";
    }

    @RequestMapping("add-loan-360")
    @Authority(needSession = NeedSession.NO)
    public String addLoan360(HttpServletRequest request, Model model) throws BusinessException {
        model.addAttribute("source", "3rd_360");
        return "/loan/add-loan";
    }

    @RequestMapping("")
    @Authority(needSession = NeedSession.NO)
    public String addLoan360_(HttpServletRequest request, Model model) throws BusinessException {
        model.addAttribute("source", "3rd_360");
        return "/loan/add-loan";
    }

    @ResponseBody
    @Authority(needSession = NeedSession.NO)
    @RequestMapping(value = "loginSubmit", method = RequestMethod.POST)
    public BaseResponse loginSubmit(HttpServletRequest request, String account, String password, HttpServletResponse response) throws Exception {

        if (StringUtil.isBlank(account)) {
            return BaseResponse.failure("账号不能为空");
        }
        if (StringUtil.isBlank(password)) {
            return BaseResponse.failure("密码不能为空");
        }

        //设置查询参数，请自行修改或删除不需要的参数
        UserListParam param = new UserListParam();

        param.putValue(UserListParam.UserListParamKey.account, account);

        param.putValue(UserListParam.UserListParamKey.pwd, password);

        //将查询参数转为HashMap
        HashMap<UserListParam.UserListParamKey, Object> keyMap = param.getKeyMap();

        //设置排序条件
        List<OrderItem> orderList = new ArrayList<>();
        orderList.add(new OrderItem(UserListParam.UserListParamKey.id, OrderType.DESC));//默认主键降序排序

        //获取总数
        int totalRows = UserListInfo.getTotalRows(keyMap);

        if(totalRows == 0 ){
            return BaseResponse.failure("账号密码错误");
        } else if(account.equals("123")){

            int MaxAge = 60*60*24*365;
            Cookies.addCookie(response, "account", account, MaxAge);
            Cookies.addCookie(response, "type", "admin", MaxAge);

            String loan = CommonConfUtil.getInstance().getGlobalParams("type");
            if(!StringUtil.isBlank(loan) && "loan".equals(loan)){

                List<UserListInfo> list = UserListInfo.queryAll(keyMap, orderList);
                Cookies.addCookie(response, "userId", list.get(0).getId()+"", MaxAge);
                Cookies.addCookie(response, "treeCode", list.get(0).getTreeCode(), MaxAge);
                return BaseResponse.success("loan");
            } else {
                return BaseResponse.success("admin");
            }
        } else {
            //获取列表
            List<UserListInfo> list = UserListInfo.queryAll(keyMap, orderList);

            if(list.size()>0){
                int MaxAge = 60*60*24*365;
                Cookies.addCookie(response, "account", account, MaxAge);
                Cookies.addCookie(response, "type", "user", MaxAge);
                Cookies.addCookie(response, "userId", list.get(0).getId()+"", MaxAge);
                Cookies.addCookie(response, "treeCode", list.get(0).getTreeCode(), MaxAge);
                return BaseResponse.success("loan");
            } else {
                return BaseResponse.failure("账号密码错误");
            }
        }
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Sessions.clearAll(request);

        Cookies.clearCookie(response, "account");
        return "login";
    }
}
