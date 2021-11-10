package com.koalii.svs.client.dao;

import com.koalii.svs.client.dto.TSignRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SvsMapper {

    int insert(TSignRecord tSignRecord);
}
