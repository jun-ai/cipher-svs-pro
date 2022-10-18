package com.koalii.svs.client.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;


@Getter
@Setter
@ToString
public class MarketQuoteEntity implements Serializable {


    private static final long serialVersionUID = -3345411400122621140L;
    /**
     * 市场报价编号
     */
    private String marketQId;

    /**
     *
     */
    private String CURVENAME;
    /**
     *
     */
    private String INSTRUMENTTYPE;
    /**
     *
     */
    private String INSTRUMENTNAME;
    /**
     *
     */
    private String TENOR;
    /**
     *
     */
    private String QUOTE;
    /**
     *
     */
    private String MATURITYDATE;
    /**
     *
     */
    private String M_H_REP_DATE;
}
