package com.qjzd.network.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qjzd.network.domain.Newsinformation;
import com.qjzd.network.domain.Product;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.NewsinformationService;
import com.qjzd.network.util.CommonUtils;
import com.qjzd.network.util.HtmlContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
/**
 * @Author:
 * @Description:
 * @Date Create on 9:41 2018/11/19
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/admin/news")
public class NewsinformationController {

    @Autowired
    private NewsinformationService newsinformationService;
//    @ResponseBody
//    @RequestMapping("/list")
//    public Result selectList(@RequestParam(value = "page",defaultValue = "1",required = false)int page,
//                             @RequestParam(value = "pagesize",defaultValue = "10",required = false)int pagesize,
//                             Long type, String title){
//
//        JSONObject param = new JSONObject();
//        if(!CommonUtils.isNull(type)){
//            param.put("type",type);
//        }
//        if(!CommonUtils.isNull(title)){
//            param.put("title",title);
//        }
//        List<Newsinformation> list = newsinformationService.selectList(param,page,pagesize);
//        if(CommonUtils.isNull(list)){
//            return Result.error(CodeMsg.NODATA);
//        }else{
//            PageInfo pageInfo = new PageInfo(list);
//            return Result.success(pageInfo);
//        }
//
//    }


    @ResponseBody
    @RequestMapping("/insert")
    public Result insert(Newsinformation newsinformation){
        if(CommonUtils.isNull(newsinformation)){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        if(CommonUtils.isNull(newsinformation.getTitle())){
            return Result.error(CodeMsg.TITLE_ERROR);
        }
        if(CommonUtils.isNull(newsinformation.getContent())){
            return Result.error(CodeMsg.CONTEXT_ERROR);
        }
        newsinformation.setContentNoHtml(HtmlContextUtil.delHtmlTag(newsinformation.getContent()));
        newsinformation.setCreateTime(new Date());
        int res = newsinformationService.insert(newsinformation);
        return res>0?Result.success(null):Result.error(CodeMsg.SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(Newsinformation newsinformation){
        if(CommonUtils.isNull(newsinformation)||CommonUtils.isNull(newsinformation.getId())){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        if(CommonUtils.isNull(newsinformation.getTitle())){
            return Result.error(CodeMsg.TITLE_ERROR);
        }

        if(CommonUtils.isNull(newsinformation.getContent())){
            return Result.error(CodeMsg.CONTEXT_ERROR);
        }
        newsinformation.setContentNoHtml(HtmlContextUtil.delHtmlTag(newsinformation.getContent()));
        newsinformation.setCreateTime(new Date());
        int res = newsinformationService.update(newsinformation);
        return res>0?Result.success(null):Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/edit")
    public String edit(Long id,Model model) {
        model.addAttribute("data",newsinformationService.selectById(id));
        return "/pages/admin/news/edit";
    }

    @RequestMapping("/list_view")
    public String list_view(){
        return "pages/admin/news/list";
    }

    @RequestMapping("/add_view")
    public String add_view(){
        return "pages/admin/news/add";
    }

    @RequestMapping("/see")
    public String see(Long id,Model model)throws Exception{
        if(CommonUtils.isNull(id)){
            throw new Exception("服务异常，ID为空");
        }
        model.addAttribute("data",newsinformationService.selectById(id));

        return "pages/admin/news/see";
    }

    @ResponseBody
    @RequestMapping("/del")
    public Result del(Long id){
        if(CommonUtils.isNull(id)){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        return newsinformationService.delete(id)>0?Result.success(null):Result.error(CodeMsg.SERVER_ERROR);
    }
}
