package com.qjzd.network.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qjzd.network.domain.Product;
import com.qjzd.network.domain.ProductType;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.service.ProductService;
import com.qjzd.network.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping("/list")
    public Result list(@RequestParam(value = "page",defaultValue = "1",required = false)int page,
                                @RequestParam(value = "pagesize",defaultValue = "10",required = false)int pagesize, Long type,String title){
        JSONObject param = new JSONObject();
        if(!CommonUtils.isNull(type)){
            param.put("type",type);
        }
        if(!CommonUtils.isNull(title)){
            param.put("title",title);
        }
        List<Product> list = productService.selectList(param,page,pagesize);
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo);
    }

    @RequestMapping("/edit")
    public String edit(Long id,Model model) {
        model.addAttribute("data",productService.selectById(id));
        model.addAttribute("types",productService.selectTypes(new JSONObject()));
        return "pages/product/product-edit";
    }

    @ResponseBody
    @RequestMapping("/update")
    public Result update(Product product){
        if(CommonUtils.isNull(product)||CommonUtils.isNull(product.getId())){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        if(CommonUtils.isNull(product.getTitle())){
            return Result.error(CodeMsg.TITLE_ERROR);
        }
        if(CommonUtils.isNull(product.getType())){
            return Result.error(CodeMsg.TYPE_ERROR);
        }
        if(CommonUtils.isNull(product.getContext())){
            return Result.error(CodeMsg.CONTEXT_ERROR);
        }
        product.setCreatetime(new Date());
        int res = productService.update(product);
        return res>0?Result.success(null):Result.error(CodeMsg.SERVER_ERROR);
    }


    @ResponseBody
    @RequestMapping("/del")
    public Result product_del(Long id){
        if(CommonUtils.isNull(id)){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        int res = productService.delProduct(id);
        return res>0?Result.success(null):Result.error(CodeMsg.SERVER_ERROR);

    }

    @RequestMapping("/product_view")
    public String product_view(){
        return "pages/product/productList";
    }


    @RequestMapping("/type")
    public String type(Model model){
        List<ProductType> list = productService.selectTypes(new JSONObject());
        model.addAttribute("data",list);

        return "pages/product/type";
    }
    @ResponseBody
    @RequestMapping("/type/add")
    public Result add_type(String name){
        if(CommonUtils.isNull(name)){
            return Result.error(CodeMsg.CONTEXT_ERROR);
        }
        JSONObject param = new JSONObject();
        param.put("name",name);
        List<ProductType> list = productService.selectTypes(param);
        if(!CommonUtils.isNull(list)){
            return Result.error(CodeMsg.TYPENAME_ALREADY_EXISTED);
        }
        ProductType productType = new ProductType();
        productType.setName(name);
        int res = productService.addType(productType);
        if(res>0){
            return Result.success(productType);
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping("/type/del")
    public Result add_type(Long id){
        if(CommonUtils.isNull(id)){
            return Result.error(CodeMsg.BIND_ERROR);
        }
        int res = productService.delType(id);
        if(res>0){
            return Result.success(null);
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @RequestMapping("/type/edit")
    public String edit_type(Long id,Model model)throws Exception{
        if(CommonUtils.isNull(id)){
            id= new Long("2");
        }
        ProductType productType = productService.selectTypeById(id);
        if(CommonUtils.isNull(productType)){
            throw new Exception("未查到此ID的产品类型");
        }
        model.addAttribute("data",productType);
        return "pages/product/editType";

    }


    @ResponseBody
    @RequestMapping("/type/update")
    public Result update_type(Long id,String name){
        if(CommonUtils.isNull(id)||CommonUtils.isNull(name)){
            return Result.error(CodeMsg.BIND_ERROR);
        }
        ProductType productType = productService.selectTypeById(id);
        if(CommonUtils.isNull(productType)){
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        productType.setName(name);
        int res = productService.updateType(productType);
        return res>0?Result.success(null):Result.error(CodeMsg.SERVER_ERROR);

    }

}
