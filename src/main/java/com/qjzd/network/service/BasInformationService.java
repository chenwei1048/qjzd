package com.qjzd.network.service;

import com.alibaba.fastjson.JSONObject;
import com.qjzd.network.dao.BasInformationMapper;
import com.qjzd.network.domain.BasInformation;
import com.qjzd.network.domain.BasInformationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
/**
 * @Author:
 * @Description:
 * @Date Create on 18:07 2018/8/15
 * @MOdifyBy:
 * @parameter
 */
@Service
public class BasInformationService {
    @Autowired
    private BasInformationMapper basInformationMapper;



    public BasInformation selectByExample(JSONObject param){
        BasInformationExample example = new BasInformationExample();
        BasInformationExample.Criteria criteria = example.createCriteria();
        if(param.containsKey("type")){
            criteria.andTypeEqualTo(param.getLong("type"));
        }
        if(param.containsKey("title")){
            criteria.andTitleEqualTo(param.getString("title"));
        }
        List<BasInformation> list =basInformationMapper.selectByExample(example);
        return list.size()>0?list.get(0):null ;
    }


    public int update(BasInformation basInformation){
        return basInformationMapper.updateByPrimaryKey(basInformation);
    }

    public int add(BasInformation basInformation){
        return basInformationMapper.insert(basInformation);
    }
}
