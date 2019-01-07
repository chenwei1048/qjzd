package com.qjzd.network.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qjzd.network.activemq.Producer;
import com.qjzd.network.activemq.QueueConstant;
import com.qjzd.network.domain.*;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.LeaveWordService;
import com.qjzd.network.service.NewsinformationService;
import com.qjzd.network.service.ProductService;
import com.qjzd.network.util.CommonUtils;
import com.qjzd.network.util.MailUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date Create on 15:02 2018/11/29
 * @MOdifyBy:
 * @parameter
 */
@RestController
@RequestMapping("/api")
public class DataApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private NewsinformationService newsinformationService;

    @Autowired
    private LeaveWordService leaveWordService;

    @Autowired
    private Producer producer;

    @RequestMapping("/productList")
    public Result list(@RequestParam(value = "page",defaultValue = "1",required = false)int page,
                       @RequestParam(value = "pagesize",defaultValue = "10",required = false)int pagesize, Long type, String title){
        JSONObject param = new JSONObject();
        if(!CommonUtils.isNull(type)){
            param.put("type",type);
        }
        if(!CommonUtils.isNull(title)){
            param.put("title",title);
        }
        List<Product> list = productService.selectList(param,page,pagesize);
        List<ProductType> types = productService.selectTypes(new JSONObject());
        for(Product product : list){
            for(ProductType type1 : types){
                if(product.getType().equals(type1.getId())){
                    product.setTypeName(type1.getName());
                }
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo);
    }


    @RequestMapping("/newsList")
    public Result selectList(@RequestParam(value = "page",defaultValue = "1",required = false)int page,
                             @RequestParam(value = "pagesize",defaultValue = "10",required = false)int pagesize,
                             Long type, String title){

        JSONObject param = new JSONObject();
        if(!CommonUtils.isNull(type)){
            param.put("type",type);
        }
        if(!CommonUtils.isNull(title)){
            param.put("title",title);
        }
        List<Newsinformation> list = newsinformationService.selectList(param,page,pagesize);
        if(CommonUtils.isNull(list)){
            return Result.error(CodeMsg.NODATA);
        }else{
            PageInfo pageInfo = new PageInfo(list);
            return Result.success(pageInfo);
        }

    }

    @RequestMapping("/types")
    public Result types(){
        List<ProductType> list = productService.selectTypes(new JSONObject());
        return Result.success(list);
    }

    @RequestMapping("/addLeave")
    public Result addLeave(Leaveword leaveword, HttpSession session){
        if(CommonUtils.isNull(leaveword.getContactNumber())||CommonUtils.isNull(leaveword.getMobilePhone())){
            return Result.error(CodeMsg.MOBILE_OR_CONTACT_EMPTY);
        }
        if(CommonUtils.isNull(leaveword.getContent())){
            return Result.error(CodeMsg.CONTEXT_ERROR);
        }
        if(session.getAttribute("addTime")==null){
            session.setAttribute("addTime",new Date());
            session.setMaxInactiveInterval(5);
        }else{
            return Result.error(CodeMsg.HANDLE_OFTNE);
        }
        leaveword.setIsRead("0");
        leaveword.setCreatetime(new Date());
        leaveWordService.insert(leaveword);
        BasOrganization basOrganization = (BasOrganization)session.getServletContext().getAttribute("basOrganization");
        if(basOrganization.getEmail()!=null){
            MailUtil.sendMail(basOrganization.getEmail(),"您有新的客户留言，请进后台查看！","您有新的客户留言，请进后台查看！<br><a href=\"www.shqjcd.com\">www.shqjcd.com</a>");
        }
//        producer.sendData(new ActiveMQQueue(QueueConstant.LEAVE_MAIL), JSON.toJSONString(leaveword));
        return Result.success(null);
    }
}
