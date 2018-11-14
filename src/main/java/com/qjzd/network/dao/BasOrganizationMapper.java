package com.qjzd.network.dao;

import com.qjzd.network.domain.BasOrganization;
import com.qjzd.network.domain.BasOrganizationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasOrganizationMapper {
    int countByExample(BasOrganizationExample example);

    int deleteByExample(BasOrganizationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasOrganization record);

    int insertSelective(BasOrganization record);

    List<BasOrganization> selectByExample(BasOrganizationExample example);

    BasOrganization selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasOrganization record, @Param("example") BasOrganizationExample example);

    int updateByExample(@Param("record") BasOrganization record, @Param("example") BasOrganizationExample example);

    int updateByPrimaryKeySelective(BasOrganization record);

    int updateByPrimaryKey(BasOrganization record);
}