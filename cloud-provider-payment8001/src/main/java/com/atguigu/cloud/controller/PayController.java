package com.atguigu.cloud.controller;

import cn.hutool.json.JSONUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.dto.PayDTO;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    public String addPay(@RequestBody Pay pay) {
        log.error("插入数据：{}", JSONUtil.toJsonStr(pay));
        int add = payService.add(pay);
        return String.format("成功插入，返回记录值：%s", add);
    }

    @DeleteMapping("/pay/del/{id}")
    public Integer deletePay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PutMapping(value = "/pay/update")
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int update = payService.update(pay);
        return String.format("成功更新，返回记录值：%s", update);
    }

    @GetMapping(value = "/pay/get/{id}")
    public Pay getPay(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping(value = "/pay/get/all")
    public List<Pay> getAll() {
        return payService.getAll();
    }
}
