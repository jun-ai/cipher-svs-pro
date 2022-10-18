package com.koalii.svs.client.dao;

import com.koalii.svs.client.entity.MarketQuoteEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarketQuoteMapper {

    void insertTable(List<MarketQuoteEntity> marketQuoteEntityList);

}
