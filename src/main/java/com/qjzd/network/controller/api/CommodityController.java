package com.qjzd.network.controller.api;
import com.qjzd.network.result.Result;
import com.qjzd.network.util.CosUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/commodity")
public class CommodityController {


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam(value = "oneFile", required = false) MultipartFile cardFile,
                         HttpServletRequest request, HttpServletResponse response) {
        System.out.println("*************接收上传文件*************");
        String path= request.getSession().getServletContext()
                .getRealPath("static"+File.separator+"upload");
//        path= path+"static/uploadfiles";
        String imgUrl = "";
        try {
            if (cardFile != null) {
                String oldFileName = cardFile.getOriginalFilename();// 获取文件名称
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = sdf.format(new Date()) + "_" + oldFileName;
                String realPath = path;
                File targetFile = new File(realPath, newFileName);
                // 检测是否存在目录
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream(targetFile));
                out1.write(cardFile.getBytes());
                out1.flush();
                out1.close();
                if(targetFile.exists()){
                    System.out.println("文件已经存在");
                }
//                imgUrl = "http://" + ip +"/uploadfiles/" + newFileName;
                imgUrl = CosUtil.uploadFile(realPath+"/"+newFileName,newFileName);
            } else {
                System.out.println("************上传文件为空***************");
            }
            System.out.println("*************接收上传文件结束*************");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl==""?Result.error(null):Result.success(imgUrl);
    }

}
