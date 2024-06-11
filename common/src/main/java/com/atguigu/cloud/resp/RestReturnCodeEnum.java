package com.atguigu.cloud.resp;

import lombok.Getter;

import java.util.Arrays;

/**
 * <p>
 * 错误码枚举嘞
 * </p>
 *
 * @author ShaoHuaYuGong
 * @date 2024/06/04
 */
@Getter
public enum RestReturnCodeEnum {

    /**
     * 操作失败
     **/
    RC999("999", "操作XXX失败"),
    /**
     * 操作成功
     **/
    RC200("200", "success"),
    /**
     * 服务降级
     **/
    RC201("201", "服务开启降级保护,请稍后再试!"),
    /**
     * 热点参数限流
     **/
    RC202("202", "热点参数限流,请稍后再试!"),
    /**
     * 系统规则不满足
     **/
    RC203("203", "系统规则不满足要求,请稍后再试!"),
    /**
     * 授权规则不通过
     **/
    RC204("204", "授权规则不通过,请稍后再试!"),
    /**
     * access_denied
     **/
    RC403("403", "无访问权限,请联系管理员授予权限"),
    /**
     * access_denied
     **/
    RC401("401", "匿名用户访问无权限资源时的异常"),
    RC404("404", "404页面找不到的异常"),
    /**
     * 服务异常
     **/
    RC500("500", "系统异常，请稍后重试"),
    RC375("375", "数学运算异常，请稍后重试");
    /**
     * 自定义状态码
     **/
    private final String code;
    /**
     * 自定义描述
     **/
    private final String message;

    RestReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 遍历枚举V1
    public static RestReturnCodeEnum getReturnCodeEnum(String code) {
        for (RestReturnCodeEnum element : RestReturnCodeEnum.values()) {
            if (element.getCode().equalsIgnoreCase(code)) {
                return element;
            }
        }
        return null;
    }

    // 遍历枚举V2
    public static RestReturnCodeEnum getReturnCodeEnumV2(String code) {
        return Arrays.stream(RestReturnCodeEnum.values()).filter(x -> x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}

