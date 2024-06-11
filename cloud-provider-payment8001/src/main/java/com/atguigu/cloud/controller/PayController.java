package com.atguigu.cloud.controller;

import cn.hutool.json.JSONUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
}
