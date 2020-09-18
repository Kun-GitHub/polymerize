package com.juiniot.modules.controller;

import com.juiniot.common.utils.Global;
import com.juiniot.common.utils.MD5Util;
import com.juiniot.common.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by zhifen
 */
@Scope("prototype")
@Controller
@RequestMapping("file")
public class FileUploadController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request,String savePath) throws IOException {
        if(StringUtils.isEmpty(savePath)){
            //savePath参数是子保存路径，为空时放在指定默认路径
            savePath = Global.getConfig("defaultSavePath");
        }
        if(!savePath.startsWith("/")){
            savePath = "/"+savePath;
        }
        if(!savePath.endsWith("/")){
            savePath = savePath+"/";
        }
        return upload(request, savePath);
    }

    private String upload(HttpServletRequest request, String savePath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        /**得到图片保存目录的真实路径**/
        String realPathDir = Global.getFilePath();
        if(StringUtils.isEmpty(realPathDir)){
            realPathDir = request.getSession().getServletContext().getRealPath("/");
        }
        realPathDir = realPathDir+savePath;
        /**根据真实路径创建目录**/
        File saveFile = new File(realPathDir);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        /**页面控件的文件流**/
        MultipartFile multipartFile = multipartRequest.getFile("file");
        /**获取文件的后缀**/
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        /**拼成完整的文件保存路径加文件**/
        String name = MD5Util.MD5(System.currentTimeMillis()+""+Math.random()).toLowerCase() + suffix;
        String fileName = realPathDir + File.separator + name;
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (Exception e) {
            logger.error("上传图片出错", e);
        }
        return savePath + name;
    }

}
