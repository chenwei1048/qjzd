package com.qjzd.network.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.qjzd.network.dao.LeavewordMapper;
import com.qjzd.network.domain.Leaveword;
import com.qjzd.network.domain.LeavewordExample;
import com.qjzd.network.domain.Newsinformation;
import com.qjzd.network.domain.NewsinformationExample;
import com.qjzd.network.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date Create on 14:04 2018/11/19
 * @MOdifyBy:
 * @parameter
 */
@Service
public class LeaveWordService {

    @Autowired
    private LeavewordMapper leavewordMapper;

    public List<Leaveword> selectList(JSONObject param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        LeavewordExample example = new LeavewordExample();
        LeavewordExample.Criteria criteria = example.createCriteria();
        if(param.containsKey("orgName")){
            criteria.andOrgNameLike("%"+param.getString("orgName")+"%");
        }
        if(param.containsKey("contacts")){
            criteria.andContactsLike("%"+param.getString("contacts")+"%");
        }
        if(param.containsKey("contactNumber")){
            criteria.andContactNumberLike("%"+param.getString("contactNumber")+"%");
        } if(param.containsKey("mobilePhone")){
            criteria.andMobilePhoneLike("%"+param.getString("mobilePhone")+"%");
        }
        if(param.containsKey("email")){
            criteria.andEmailLike("%"+param.getString("email")+"%");
        }
        if(param.containsKey("address")){
            criteria.andAddressLike("%"+param.getString("address")+"%");
        }
        if(param.containsKey("isRead")){
            criteria.andIsReadEqualTo(param.getString("isRead"));
        }
        example.setOrderByClause("createtime desc");
        return leavewordMapper.selectByExample(example);
    }

    public int selectCount(JSONObject jsonObject){
        LeavewordExample example =new LeavewordExample();
        LeavewordExample.Criteria criteria = example.createCriteria();
        if(jsonObject.containsKey("isRead")){
            criteria.andIsReadEqualTo(jsonObject.getString("isRead"));
        }
        return leavewordMapper.countByExample(example);
    }

    public int updateByPrimaryKeySelective(Leaveword leaveword){
        LeavewordExample example = new LeavewordExample();
        LeavewordExample.Criteria criteria = example.createCriteria();
        if(CommonUtils.isNull(leaveword.getIsRead())){
            criteria.andIsReadEqualTo(leaveword.getIsRead());
        }
        return leavewordMapper.updateByPrimaryKeySelective(leaveword);
    }

    public int delete(Long id){
        return leavewordMapper.deleteByPrimaryKey(id);
    }
}
