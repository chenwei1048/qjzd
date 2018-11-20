package com.qjzd.network.service;

import com.alibaba.fastjson.JSON;
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

    public List<Product> selectList(JSONObject param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        if(param.containsKey("type")){
            criteria.andTypeEqualTo(param.getLong("type"));
        }
        if(param.containsKey("title")){
            criteria.andTitleLike("%"+param.getString("title")+"%");
        }
        example.setOrderByClause("createtime desc");
        return productMapper.selectByExample(example);
    }

    public int selectCount(){
        ProductExample example = new ProductExample();
        return productMapper.countByExample(example);
    }

    public Product selectById(Long id){
        return productMapper.selectByPrimaryKey(id);
    }

    public int insert(Product product){
        return productMapper.insert(product);
    }

    public int update(Product product){
        return productMapper.updateByPrimaryKey(product);
    }



    public List<ProductType> selectTypes(JSONObject param){
        ProductTypeExample example = new ProductTypeExample();
        ProductTypeExample.Criteria criteria = example.createCriteria();
        if(param.containsKey("name")){
            criteria.andNameEqualTo(param.getString("name"));
        }
        return productTypeMapper.selectByExample(example);
    }

    public ProductType selectTypeById(Long id){
        return productTypeMapper.selectByPrimaryKey(id);
    }

    public int addType(ProductType productType){
        return productTypeMapper.insert(productType);
    }

    public int delType(long id){
        return productTypeMapper.deleteByPrimaryKey(id);
    }

    public int updateType(ProductType productType){
        return productTypeMapper.updateByPrimaryKey(productType);
    }

    public int delProduct(Long id){
        return productMapper.deleteByPrimaryKey(id);
    }
}
