package com.koalii.svs.client.controller;


import com.koalii.svs.client.dto.ResultUtil;
import com.koalii.svs.client.service.ManageFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = {"ManageFile"}, description = "文件管理")
@RequestMapping("/mgrFile")
public class ManageFileController {

    @Autowired
    private ManageFileService manageFileService;


    @PostMapping(value = { "/uploadFile" },produces="application/json;charset=UTF-8")
    @ApiOperation(value = "市场报价信息导入", notes = "市场报价信息导入")
    public ResultUtil uploadFile() {
        //@RequestParam(value = "file") MultipartFile file
        return  manageFileService.insertTable();
    }

}
