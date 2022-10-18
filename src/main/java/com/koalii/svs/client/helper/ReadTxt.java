package com.koalii.svs.client.helper;

import com.koalii.svs.client.entity.MarketQuoteEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTxt {

    /**
     * 1.一次性读取txt文件的内容 将文件内容存入集合
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static ArrayList<MarketQuoteEntity> txt2String(File file){
        ArrayList<MarketQuoteEntity> marketQuoteEntityArrayList = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            br.readLine();
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String[] sz = s.split(",");
                String marketQId="M"+TimeHelper.getUniqueString();
                String CREATE_DATE=TimeHelper.dateToLongString();
                MarketQuoteEntity marketQuoteEntity = new MarketQuoteEntity(marketQId,sz[0],sz[1],sz[2],sz[3],sz[4],sz[5],sz[6],CREATE_DATE);
                marketQuoteEntityArrayList.add(marketQuoteEntity);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return marketQuoteEntityArrayList;
    }
    /*2.获取指定集合范围内的数据存入集合 例如传递的是2  返回第2000条到第3000条*/
    public static List<MarketQuoteEntity> getReal(ArrayList<MarketQuoteEntity> marketQuoteEntityArrayList, int j){
        if (marketQuoteEntityArrayList == null) {
            return null;
        }
        /*判断i 和 集合长度  不同范围else 同一范围(20-23) if*/
        int i =(j-1)*1000;
        if(marketQuoteEntityArrayList.size()-i<1000){
            return marketQuoteEntityArrayList.subList(i,marketQuoteEntityArrayList.size());
        }else{
            return marketQuoteEntityArrayList.subList(i,j*1000);
        }
    }

}
