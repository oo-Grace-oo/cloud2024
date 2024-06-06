package com.atguigu.cloud.resp;

import com.atguigu.cloud.base.ReturnCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/06/04
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ResultData<T> {

    private String code;
    /**
     * 结果状态 ,具体状态码参见枚举类ReturnCodeEnum.java
     */
    private String message;
    private T data;
    private long timestamp;


    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> ResultData<T> fail(ReturnCodeEnum codeEnum) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(codeEnum.getCode());
        resultData.setMessage(codeEnum.getMessage());
        return resultData;
    }


}
