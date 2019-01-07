package com.qjzd.network.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.util.CosUtil;
import com.qjzd.network.util.Uploader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * umeditor 图片上传
 * @author cherry
 *
 */
@Controller
@RequestMapping("/ueditor")
public class UeEditController {
	
	@RequestMapping("/add.do")
	public void add(String content){
		System.out.println(content+"--------");
	}
	
	
	
	@ResponseBody
    @RequestMapping("/imageUp")
    public String imageUp(MultipartFile upfile, HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) {
		System.out.println("----------------+++----");
        Uploader up = new Uploader(request);
        up.setSavePath("upload");  
        String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};  
        up.setAllowFiles(fileType);  
        up.setMaxSize(10000); //单位KB  
        try {  
            up.upload(upfile);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
        JSONObject json = new JSONObject();
        json.put("name",up.getFileName());
        json.put("originalName",up.getOriginalName());
        json.put("size",up.getSize());

        json.put("state",up.getState());

        json.put("type",up.getType());

        String callback = request.getParameter("callback");
        if("SUCCESS".equals(up.getState())){
            String path = request.getSession().getServletContext()
                    .getRealPath("static")+ File.separator+up.getUrl() ;
            json.put("url",CosUtil.uploadFile(path,up.getFileName()));
        }else{
            json.put("url",up.getUrl());
        }
//        String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";
        String result = json.toJSONString();


//        result = result.replaceAll( "\\\\", "\\\\" );
        System.out.println(result+"*******"+callback);
        if(callback == null ){  
            return result ;  
        }else{  
           return "<script>"+ callback +"(" + result + ")</script>";  
        }  
    } 
}
