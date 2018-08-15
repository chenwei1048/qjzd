package com.qjzd.network.service;

import com.github.pagehelper.PageHelper;
import com.qjzd.network.dao.ProductMapper;
import com.qjzd.network.domain.Product;
import com.qjzd.network.domain.ProductExample;
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
}
