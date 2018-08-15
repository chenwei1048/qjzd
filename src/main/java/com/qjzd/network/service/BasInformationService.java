package com.qjzd.network.service;

import com.qjzd.network.dao.BasInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void list(){

    }
}
