package com.qjzd.network.dao;

import com.qjzd.network.domain.BasInformation;
import com.qjzd.network.domain.BasInformationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasInformationMapper {
    int countByExample(BasInformationExample example);

    int deleteByExample(BasInformationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BasInformation record);

    int insertSelective(BasInformation record);

    List<BasInformation> selectByExample(BasInformationExample example);

    BasInformation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BasInformation record, @Param("example") BasInformationExample example);

    int updateByExample(@Param("record") BasInformation record, @Param("example") BasInformationExample example);

    int updateByPrimaryKeySelective(BasInformation record);

    int updateByPrimaryKey(BasInformation record);
}