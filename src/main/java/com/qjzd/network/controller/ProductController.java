package com.qjzd.network.controller;

import com.qjzd.network.domain.Product;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.ProductService;
import com.qjzd.network.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Description:
 * @Date Create on 17:05 2018/8/6
 * @MOdifyBy:
 * @parameter
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/list")
    public Result<List> list(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, long type,String title){
        Map<String,Object> param = new HashMap<>();
        if(!CommonUtils.isNull(type)){
            param.put("type",type);
        }
        if(!CommonUtils.isNull(title)){
            param.put("title",title);
        }

        List<Product> list = productService.list(param,pageNum,pageSize);
        return Result.success(list);

    }




}
