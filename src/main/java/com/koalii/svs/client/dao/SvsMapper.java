package com.koalii.svs.client.dao;

import com.koalii.svs.client.dto.TSignRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface SvsMapper {

    int insert(TSignRecord tSignRecord);

    List<TSignRecord> selectBySigntype(String sign_type);

}
