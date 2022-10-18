package com.koalii.svs.client.service.impl;

import com.koalii.svs.client.dao.MarketQuoteMapper;
import com.koalii.svs.client.service.ManageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageFileServiceImpl implements ManageFileService {

    @Autowired
    private MarketQuoteMapper marketQuoteMapper;

}
