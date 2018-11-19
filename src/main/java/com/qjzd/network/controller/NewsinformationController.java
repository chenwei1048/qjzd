package com.qjzd.network.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qjzd.network.domain.Newsinformation;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.NewsinformationService;
import com.qjzd.network.util.CommonUtils;
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
@RequestMapping("/news")
public class NewsinformationController {

    @Autowired
    private NewsinformationService newsinformationService;
    @ResponseBody
    @RequestMapping("/list")
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

    @ResponseBody
    @RequestMapping("/update")
    public Result update(Newsinformation product){
        if(CommonUtils.isNull(product)||CommonUtils.isNull(product.getId())){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        if(CommonUtils.isNull(product.getTitle())){
            return Result.error(CodeMsg.TITLE_ERROR);
        }

        if(CommonUtils.isNull(product.getContext())){
            return Result.error(CodeMsg.CONTEXT_ERROR);
        }
        product.setCreatetime(new Date());
        int res = newsinformationService.update(product);
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
