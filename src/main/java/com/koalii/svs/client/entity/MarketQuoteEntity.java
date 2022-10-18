package com.koalii.svs.client.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;


@Getter
@Setter
@ToString
@ApiModel(value = "MarketQuoteEntity", description = "市场报价信息Bean")
public class MarketQuoteEntity implements Serializable {


    private static final long serialVersionUID = -3345411400122621140L;
    /**
     * 市场报价编号
     */
    private String MARKET_ID;

    /**
     *
     */
    private String CURVE_NAME;
    /**
     *
     */
    private String INSTRUMENT_TYPE;
    /**
     *
     */
    private String INSTRUMENT_NAME;
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
    private String MATURITY_DATE;
    /**
     *
     */
    private String M_H_REP_DATE;

    /**
     * 创建时间
     */
    private String CREATE_DATE;

    public MarketQuoteEntity(String MARKET_ID, String CURVE_NAME, String INSTRUMENT_TYPE, String INSTRUMENT_NAME, String TENOR, String QUOTE, String MATURITY_DATE, String m_H_REP_DATE, String CREATE_DATE) {
        this.MARKET_ID = MARKET_ID;
        this.CURVE_NAME = CURVE_NAME;
        this.INSTRUMENT_TYPE = INSTRUMENT_TYPE;
        this.INSTRUMENT_NAME = INSTRUMENT_NAME;
        this.TENOR = TENOR;
        this.QUOTE = QUOTE;
        this.MATURITY_DATE = MATURITY_DATE;
        M_H_REP_DATE = m_H_REP_DATE;
        this.CREATE_DATE = CREATE_DATE;
    }

    public String getMARKET_ID() {
        return MARKET_ID;
    }

    public void setMARKET_ID(String MARKET_ID) {
        this.MARKET_ID = MARKET_ID;
    }

    public String getCURVE_NAME() {
        return CURVE_NAME;
    }

    public void setCURVE_NAME(String CURVE_NAME) {
        this.CURVE_NAME = CURVE_NAME;
    }

    public String getINSTRUMENT_TYPE() {
        return INSTRUMENT_TYPE;
    }

    public void setINSTRUMENT_TYPE(String INSTRUMENT_TYPE) {
        this.INSTRUMENT_TYPE = INSTRUMENT_TYPE;
    }

    public String getINSTRUMENT_NAME() {
        return INSTRUMENT_NAME;
    }

    public void setINSTRUMENT_NAME(String INSTRUMENT_NAME) {
        this.INSTRUMENT_NAME = INSTRUMENT_NAME;
    }

    public String getTENOR() {
        return TENOR;
    }

    public void setTENOR(String TENOR) {
        this.TENOR = TENOR;
    }

    public String getQUOTE() {
        return QUOTE;
    }

    public void setQUOTE(String QUOTE) {
        this.QUOTE = QUOTE;
    }


    public String getMATURITY_DATE() {
        return MATURITY_DATE;
    }

    public void setMATURITY_DATE(String MATURITY_DATE) {
        this.MATURITY_DATE = MATURITY_DATE;
    }

    public String getM_H_REP_DATE() {
        return M_H_REP_DATE;
    }

    public void setM_H_REP_DATE(String m_H_REP_DATE) {
        M_H_REP_DATE = m_H_REP_DATE;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }




}
