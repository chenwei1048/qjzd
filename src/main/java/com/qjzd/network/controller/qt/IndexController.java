package com.qjzd.network.controller.qt;

import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.annotation.MyOperation;
import com.qjzd.network.domain.Newsinformation;
import com.qjzd.network.domain.Product;
import com.qjzd.network.domain.ProductType;
import com.qjzd.network.service.NewsinformationService;
import com.qjzd.network.service.ProductService;
import com.qjzd.network.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private NewsinformationService newsinformationService;
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

    @MyOperation("产品展示")
    @RequestMapping("/product")
    public String product(Model model,@RequestParam(value = "page", defaultValue = "1", required = false)int page,
                          Long type){
        model.addAttribute("page",page);
        model.addAttribute("type",type);
        if(CommonUtils.isNull(type)){
            model.addAttribute("typeName","所有产品");
        }else{
            ProductType productType = productService.selectTypeById(type);
            model.addAttribute("typeName",productType.getName());
            }
        return "pages/qt/product";
    }
    @MyOperation("产品详情")
    @RequestMapping("/productDetail")
    public String productDetail(Model model,Long id){
        Product product = productService.selectById(id);
        model.addAttribute("product",product);
        return "pages/qt/productDetail";
    }

    @MyOperation("关于我们")
    @RequestMapping("/about")
    public String about(){
        return "pages/qt/about";
    }

    @MyOperation("新闻资讯")
    @RequestMapping("/news")
    public String news(Model model,@RequestParam(value = "page", defaultValue = "1", required = false)int page,
                          Long type){
        return "pages/qt/news";
    }

    @MyOperation("新闻详情")
    @RequestMapping("/newsDetail")
    public String newsDetail(Model model,Long id){
        Newsinformation newsinformation = newsinformationService.selectById(id);
        model.addAttribute("news",newsinformation);
        return "pages/qt/newsDetail";
    }

    @MyOperation("联系我们")
    @RequestMapping("/contract")
    public String contract(Model model){
        return "pages/qt/contract";
    }

    @MyOperation("留言中心")
    @RequestMapping("/leave")
    public String leave(Model model){
        return "pages/qt/leave";
    }
//
//    @RequestMapping("/header")
//    public String header(){
//        return "pages/qt/public/header";
//    }
//
//    @RequestMapping("/footer")
//    public String footer(){
//        return "pages/qt/public/footer";
//    }



}
