package com.qjzd.network.service;

import com.qjzd.network.dao.BasOrganizationMapper;
import com.qjzd.network.domain.BasOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @Description:
 * @Date Create on 15:07 2018/11/14
 * @MOdifyBy:
 * @parameter
 */
@Service
public class BasOrganizationService {
    @Autowired
    private BasOrganizationMapper basOrganizationMapper;

    public BasOrganization selectById(Long id){
        return basOrganizationMapper.selectByPrimaryKey(id);
    }

    public int updateById(BasOrganization basOrganization){
        return basOrganizationMapper.updateByPrimaryKey(basOrganization);
    }
}
