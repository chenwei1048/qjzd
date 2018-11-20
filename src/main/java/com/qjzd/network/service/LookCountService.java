package com.qjzd.network.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.qjzd.network.dao.LookcountMapper;
import com.qjzd.network.domain.Lookcount;
import com.qjzd.network.domain.LookcountExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
/**
 * @Author:
 * @Description:
 * @Date Create on 14:58 2018/11/20
 * @MOdifyBy:
 * @parameter
 */
@Service
public class LookCountService {

    @Autowired
    private LookcountMapper lookcountMapper;

    public List<Lookcount> selectList(JSONObject param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        LookcountExample example = new LookcountExample();
        LookcountExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("createtime desc");
        return lookcountMapper.selectByExample(example);
    }

    public int selectCount(JSONObject param){
        LookcountExample example = new LookcountExample();
        LookcountExample.Criteria criteria = example.createCriteria();
        if (param.containsKey("startTime") && param.containsKey("endTime")) {
            criteria.andCreatetimeBetween(param.getDate("startTime"),param.getDate("endTime"));
        }
        return lookcountMapper.countByExample(example);
    }

    public int insert(Lookcount lookcount){
        return lookcountMapper.insert(lookcount);
    }
}
