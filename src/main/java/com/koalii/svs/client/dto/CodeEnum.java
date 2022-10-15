package com.koalii.svs.client.dto;

public enum CodeEnum {
    /** 成功 */
    SUCCESS("200", "成功"),

    /** 操做失败 */
    ERROR("500", "操作失败");

    CodeEnum(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;
}
