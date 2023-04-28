package com.xll.model;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public enum OrderStatus {

    UNKNOWN("异常"),
    UNPAID("未付款"),
    PAID("已付款"),
    SHIPPED("已发货"),
    FINISHED("已完成"),
    CANCEL("已取消");

    OrderStatus(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}