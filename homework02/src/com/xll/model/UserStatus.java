package com.xll.model;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public enum UserStatus {

    NORMAL("正常"),
    FREEZE("冻结"),
    UNKNOWN("不正常");

    UserStatus(String name) {
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
