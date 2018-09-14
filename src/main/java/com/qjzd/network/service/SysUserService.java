package com.qjzd.network.service;

import com.qjzd.network.dao.SysUserMapper;
import com.qjzd.network.domain.SysUser;
import com.qjzd.network.domain.SysUserExample;
import com.qjzd.network.exception.GlobalException;
import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.util.CommonUtils;
import com.qjzd.network.util.MD5Util;
import com.qjzd.network.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Description:
 * @Date Create on 10:04 2018/9/14
 * @MOdifyBy:
 * @parameter
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysUser> selectByExample(Map<String,Object> params) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        if(params.containsKey("username")){
            criteria.andUsernameEqualTo(params.get("username").toString());
        }
        if(params.containsKey("password")){
            criteria.andPasswordEqualTo(params.get("password").toString());
        }

        return sysUserMapper.selectByExample(example);
    }

    public SysUser login(LoginVo loginVo) {
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        Map<String,Object> params = new HashMap<>();
        params.put("username",loginVo.getUsername());
        List<SysUser> list = selectByExample(params);
        if(CommonUtils.isNull(list)){
            throw new GlobalException(CodeMsg.USERNAME_NOT_EXIST);
        }
        SysUser user = list.get(0);
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(loginVo.getPassword(), saltDB);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return user;
    }
}
