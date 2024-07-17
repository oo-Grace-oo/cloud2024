package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/05/30
 */
public interface PayService {

	int add(Pay pay);

	int update(Pay pay);

	int delete(Integer id);

	Pay getById(Integer id);

	List<Pay> getAll();
}
