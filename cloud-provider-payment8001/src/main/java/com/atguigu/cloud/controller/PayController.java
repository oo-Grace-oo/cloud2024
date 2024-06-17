package com.atguigu.cloud.controller;

import cn.hutool.json.JSONUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/05/30
 */
@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        log.info("插入数据：{}", JSONUtil.toJsonStr(pay));
        int add = payService.add(pay);
        return ResultData.success(String.format("成功插入，返回记录值：%s", add));
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        log.info("删除数据：{}", id);
        return ResultData.success(payService.delete(id));
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改", description = "修改支付流水")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        log.info("修改数据：{}", JSONUtil.toJsonStr(payDTO));
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int update = payService.update(pay);
        return ResultData.success(String.format("成功更新，返回记录值：%s", update));
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "查询", description = "按照id查询流水")
    public ResultData<Pay> getPay(@PathVariable("id") Integer id) {
        log.info("查询数据：{}", id);
        if (Objects.isNull(id) || id < 0) {
            throw new RuntimeException("不合法的id");
        }
        return ResultData.success(payService.getById(id));
    }

    @GetMapping(value = "/pay/get/all")
    @Operation(summary = "查询全部", description = "查询全部流水")
    public ResultData<List<Pay>> getAll() {
        return ResultData.success(payService.getAll());
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    public String getPayInfo(@Value("${atguigu.info}") String info) {
        return "consul config : " + info + "\r\n<br>" + " port:" + port;
    }

    @Resource(name = "primaryDataSource")
    private DataSource primaryDataSource;

    @Resource(name = "secondaryDataSource")
    private DataSource secondaryDataSource;
    @Resource
    private PayMapper payMapper;

    @GetMapping(value = "/test")
    public Object mig() throws SQLException {
        // 查询相应表的列
        List<String> columns = payMapper.selectColumns().stream().map(e -> String.valueOf(e.get("Field"))).collect(Collectors.toList());
        // 根据列名查询数据
        List<Map<String, Object>> datas = payMapper.selectData(columns);
        payMapper.replaceIntoData(datas.get(0), columns);
        payMapper.replaceInto(datas, columns);
        return primaryDataSource.getConnection().nativeSQL("show COLUMNS from t_pay; ");
    }
}
