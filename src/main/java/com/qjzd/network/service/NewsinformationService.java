package com.qjzd.network.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.qjzd.network.dao.NewsinformationMapper;
import com.qjzd.network.domain.Newsinformation;
import com.qjzd.network.domain.NewsinformationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
/**
 * @Author:
 * @Description:
 * @Date Create on 9:39 2018/11/19
 * @MOdifyBy:
 * @parameter
 */
@Service
public class NewsinformationService {

    @Autowired
    private NewsinformationMapper newsinformationMapper;

    public List<Newsinformation> selectList(JSONObject param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        NewsinformationExample example = new NewsinformationExample();
        NewsinformationExample.Criteria criteria = example.createCriteria();
        if(param.containsKey("type")){
            criteria.andTypeEqualTo(param.getLong("type"));
        }
        if(param.containsKey("title")){
            criteria.andTitleLike("%"+param.getString("title")+"%");
        }
        example.setOrderByClause("createtime desc");
        return newsinformationMapper.selectByExample(example);
    }

    public int insert(Newsinformation newsinformation){
        return newsinformationMapper.insert(newsinformation);
    }

    public int selectCount(){
        return newsinformationMapper.countByExample(new NewsinformationExample());
    }

    public Newsinformation selectById(Long id){
        return newsinformationMapper.selectByPrimaryKey(id);
    }

    public int update(Newsinformation newsinformation){
        return newsinformationMapper.updateByPrimaryKey(newsinformation);
    }

    public int delete(Long id){
        return newsinformationMapper.deleteByPrimaryKey(id);
    }
}
