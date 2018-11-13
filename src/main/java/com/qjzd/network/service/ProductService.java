package com.qjzd.network.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.qjzd.network.dao.ProductMapper;
import com.qjzd.network.dao.ProductTypeMapper;
import com.qjzd.network.domain.Product;
import com.qjzd.network.domain.ProductExample;
import com.qjzd.network.domain.ProductType;
import com.qjzd.network.domain.ProductTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Description:cherry
 * @Date Create on 17:04 2018/8/6
 * @MOdifyBy:
 * @parameter
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    public List<Product> list(Map<String,Object> param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        if(param.containsKey("type")){
            criteria.andTypeEqualTo(Long.parseLong(param.get("type").toString()));
        }
        if(param.containsKey("title")){
            criteria.andTitleLike("%"+param.get("title").toString()+"%");
        }
        return productMapper.selectByExample(example);
    }

    public List<ProductType> selectTypes(JSONObject param){
        ProductTypeExample example = new ProductTypeExample();
        ProductTypeExample.Criteria criteria = example.createCriteria();
        if(param.containsKey("name")){
            criteria.andNameEqualTo(param.getString("name"));
        }
        return productTypeMapper.selectByExample(example);
    }

    public int addType(ProductType productType){
        return productTypeMapper.insert(productType);
    }

    public int delType(long id){
        return productTypeMapper.deleteByPrimaryKey(id);
    }
}
