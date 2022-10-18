package com.koalii.svs.client.controller;


import com.koalii.svs.client.dto.ResultUtil;
import com.koalii.svs.client.service.ManageFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@Api(tags = {"ManageFile"}, description = "文件管理")
@RequestMapping("/mgrFile")
public class ManageFileController {

    @Autowired
    private ManageFileService manageFileService;


    @PostMapping(value = { "/uploadFile" }, consumes = "multipart/*", produces="application/json;charset=UTF-8")
    @ApiOperation(value = "上传文件(压缩)", notes = "上传文件(压缩)")
    public ResultUtil uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return ResultUtil.success("上传成功");
    }

}
