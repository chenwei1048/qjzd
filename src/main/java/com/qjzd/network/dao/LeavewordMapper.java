package com.qjzd.network.dao;

import com.qjzd.network.domain.Leaveword;
import com.qjzd.network.domain.LeavewordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeavewordMapper {
    int countByExample(LeavewordExample example);

    int deleteByExample(LeavewordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Leaveword record);

    int insertSelective(Leaveword record);

    List<Leaveword> selectByExample(LeavewordExample example);

    Leaveword selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Leaveword record, @Param("example") LeavewordExample example);

    int updateByExample(@Param("record") Leaveword record, @Param("example") LeavewordExample example);

    int updateByPrimaryKeySelective(Leaveword record);

    int updateByPrimaryKey(Leaveword record);
}