package com.qjzd.network.dao;

import com.qjzd.network.domain.Newsinformation;
import com.qjzd.network.domain.NewsinformationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsinformationMapper {
    int countByExample(NewsinformationExample example);

    int deleteByExample(NewsinformationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Newsinformation record);

    int insertSelective(Newsinformation record);

    List<Newsinformation> selectByExample(NewsinformationExample example);

    Newsinformation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Newsinformation record, @Param("example") NewsinformationExample example);

    int updateByExample(@Param("record") Newsinformation record, @Param("example") NewsinformationExample example);

    int updateByPrimaryKeySelective(Newsinformation record);

    int updateByPrimaryKey(Newsinformation record);
}