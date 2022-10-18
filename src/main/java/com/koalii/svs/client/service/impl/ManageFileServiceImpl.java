package com.koalii.svs.client.service.impl;

import com.koalii.svs.client.dao.MarketQuoteMapper;
import com.koalii.svs.client.dto.ResultUtil;
import com.koalii.svs.client.entity.MarketQuoteEntity;
import com.koalii.svs.client.helper.ReadTxt;
import com.koalii.svs.client.service.ManageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManageFileServiceImpl implements ManageFileService {

    @Autowired
    private MarketQuoteMapper marketQuoteMapper;

    @Override
    public ResultUtil insertTable() {
            String path = this.getClass().getClassLoader().getResource("").getPath();
            System.out.println(path);
            /*1.读取文件所在位置*/
            File file = new File(path+"file/market_quote.txt");
            /*2.将文件存入集合 此处用了工具类ReadTxt.txt2String(file)方法*/
            ArrayList<MarketQuoteEntity> marketQuoteEntityArrayList = ReadTxt.txt2String(file);
            /*3，每次1000条，得到循环size次*/
            int size = marketQuoteEntityArrayList.size()/1000;
            /*4.遍历每一次*/
            for (int i = 1; i <= size+1; i++) {
                /*5.为了计算每插入1000条耗时*/
                long time1 = System.currentTimeMillis();
                /*6.精华：获取集合指定下标区间的数据 例如第3000条-4000条 调用了工具类ReadTxt.getReal(al,i)*/
                List<MarketQuoteEntity> insertMarketList = ReadTxt.getReal(marketQuoteEntityArrayList,i);
                if (insertMarketList == null) {
                    continue;
                }
                /*7.插入到数据库传递list使用foreach方式*/
                marketQuoteMapper.insertTable(insertMarketList);
                long time2 = System.currentTimeMillis();
                System.out.println("第"+i+"次耗时："+(time2-time1)+"毫秒");
            }
            return ResultUtil.success("插入数据成功");
    }
}
