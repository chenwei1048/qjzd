package com.qjzd.network.controller;

import com.qjzd.network.util.Uploader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  
        String callback = request.getParameter("callback");  
  
        String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";  
  
        result = result.replaceAll( "\\\\", "\\\\" );  
        System.out.println(result+"*******"+callback);
        if(callback == null ){  
            return result ;  
        }else{  
           return "<script>"+ callback +"(" + result + ")</script>";  
        }  
    } 
}
