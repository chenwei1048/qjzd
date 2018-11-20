package com.qjzd.network.controller;

import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.annotation.MyOperation;
import com.qjzd.network.domain.Product;
import com.qjzd.network.domain.ProductType;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.ProductService;
import com.qjzd.network.vo.ClientIpVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date Create on 15:18 2018/11/16
 * @MOdifyBy:
 * @parameter
 */
@RequestMapping("/index")
@Controller
public class IndexController {
    @Autowired
    private ProductService productService;
    @MyOperation("前台主页")
    @RequestMapping
    public String index(Model model){
        List<Product> list = productService.selectList(new JSONObject(),1,12);
        List<ProductType> types = productService.selectTypes(new JSONObject());
        List<List<Product>> lists = new ArrayList<>();
        int i=0;
        List<Product> proList = null;
        for(Product product : list){
            i++;
            if(i==1){
                proList = new ArrayList<>();
            }
            for(ProductType type1 : types){
                if(product.getType().equals(type1.getId())){
                    product.setTypeName(type1.getName());
                    break;
                }
            }
            proList.add(product);
            if(i==3){
                lists.add(proList);
                i=0;
            }
        }
        model.addAttribute("proLists",lists);
        model.addAttribute("types",types);
        return "pages/qt/index";
    }


    @RequestMapping("/header")
    public String header(){
        return "pages/qt/public/header";
    }

    @RequestMapping("/footer")
    public String footer(){
        return "pages/qt/public/footer";
    }



}
