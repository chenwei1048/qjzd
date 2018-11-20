package com.qjzd.network.dao;

import com.qjzd.network.domain.Lookcount;
import com.qjzd.network.domain.LookcountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LookcountMapper {
    int countByExample(LookcountExample example);

    int deleteByExample(LookcountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Lookcount record);

    int insertSelective(Lookcount record);

    List<Lookcount> selectByExample(LookcountExample example);

    Lookcount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Lookcount record, @Param("example") LookcountExample example);

    int updateByExample(@Param("record") Lookcount record, @Param("example") LookcountExample example);

    int updateByPrimaryKeySelective(Lookcount record);

    int updateByPrimaryKey(Lookcount record);
}