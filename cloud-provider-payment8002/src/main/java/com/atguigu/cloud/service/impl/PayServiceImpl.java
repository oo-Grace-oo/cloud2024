package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/05/30
 */
@Service
public class PayServiceImpl implements PayService {

	@Resource
	private PayMapper payMapper;

	@Override
	public int add(Pay pay) {
		return payMapper.insertSelective(pay);
	}

	@Override
	public int update(Pay pay) {
		return payMapper.updateByPrimaryKeySelective(pay);
	}

	@Override
	public int delete(Integer id) {
		return payMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Pay getById(Integer id) {
		return payMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Pay> getAll() {
		return payMapper.selectAll();
	}
}
