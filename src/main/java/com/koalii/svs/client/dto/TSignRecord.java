package com.koalii.svs.client.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Data
@Getter
@Setter
public class TSignRecord implements Serializable {

    /**
     * 主键）
     */
    private Integer t_id;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 签名类型
     */
    private String sign_type;

    /**
     * b64签名原文
     */
    private String sign_b64OriginData;

    /**
     * b64位签名信息
     */
    private String sign_b64SignedMessage;

    public TSignRecord() {
    }

    public TSignRecord(Integer t_id, Integer user_id, String sign_type, String sign_b64OriginData, String sign_b64SignedMessage) {
        this.t_id = t_id;
        this.user_id = user_id;
        this.sign_type = sign_type;
        this.sign_b64OriginData = sign_b64OriginData;
        this.sign_b64SignedMessage = sign_b64SignedMessage;
    }

    public Integer getT_id() {
        return t_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getSign_b64OriginData() {
        return sign_b64OriginData;
    }

    public String getSign_b64SignedMessage() {
        return sign_b64SignedMessage;
    }


}
