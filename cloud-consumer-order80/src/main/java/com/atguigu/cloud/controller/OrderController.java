package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/06/11
 */
@RestController
@RequestMapping("/consumer")
public class OrderController {
	// private final static String PAYMENT_SRV_URL = "http://localhost:8001";
	public static final String PAYMENT_SRV_URL = "http://cloud-payment-service";
	@Resource
	private RestTemplate restTemplate;

	/**
	 * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
	 * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
	 * 参数可以不添加@RequestBody
	 *
	 * @param payDTO
	 * @return
	 */
	@PostMapping("/pay/add")
	public ResultData addOrder(@RequestBody PayDTO payDTO) {
		return restTemplate.postForObject(PAYMENT_SRV_URL + "/pay/add", payDTO, ResultData.class);
	}

	@GetMapping("/pay/get/{id}")
	public ResultData getPayInfo(@PathVariable("id") Integer id) {
		return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/" + id, ResultData.class, id);
	}

	@DeleteMapping("/pay/del/{id}")
	public ResultData delPayInfo(@PathVariable("id") Integer id) {
		return restTemplate.exchange(PAYMENT_SRV_URL + "/pay/del/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, ResultData.class).getBody();
	}

	@PutMapping(value = "/pay/update")
	public ResultData updatePay(@RequestBody PayDTO payDTO) {
		HttpEntity<PayDTO> entity = new HttpEntity<>(payDTO, new HttpHeaders());
		ResponseEntity<ResultData> exchange = restTemplate.exchange(PAYMENT_SRV_URL + "/pay/update", HttpMethod.PUT, entity, ResultData.class);
		return exchange.getBody();
	}

	@GetMapping(value = "/pay/get/info")
	private String getInfoByConsul() {
		return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/info", String.class);
	}
}
