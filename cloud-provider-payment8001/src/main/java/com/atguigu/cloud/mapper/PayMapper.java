package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Pay;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface PayMapper extends Mapper<Pay> {

    List<Map<String, Object>> selectColumns();

    List<Map<String, Object>> selectData(@Param("col") List<String> col);
}