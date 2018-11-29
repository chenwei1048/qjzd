package com.qjzd.network.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qjzd.network.domain.Product;
import com.qjzd.network.domain.ProductType;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.ProductService;
import com.qjzd.network.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date Create on 15:02 2018/11/29
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/api")
public class DataApiController {
    @Autowired
    private ProductService productService;
    @ResponseBody
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

}
