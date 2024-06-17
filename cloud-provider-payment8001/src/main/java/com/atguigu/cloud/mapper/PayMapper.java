package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Pay;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface PayMapper extends Mapper<Pay> {

    List<Map<String, Object>> selectColumns();

    List<Map<String, Object>> selectData(@Param("col") List<String> col);

    Integer replaceInto(List<Map<String, Object>> datas, @Param("col") List<String> col);

    Integer replaceIntoData(Map<String, Object> data, @Param("col") List<String> col);
}