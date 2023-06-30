package com.example.freelyrouter.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.example.freelyrouter.domain.repsonse.InstanceResponse;
import com.example.freelyrouter.service.ImNacosService;
import org.freely.commom.core.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cluster")
public class ImServerController extends BaseController{
    @Autowired
    private ImNacosService imNacosService;


    @GetMapping("/get")
    public ResponseEntity<ApiResponse<InstanceResponse>> getImServer() throws NacosException {
        return successResponse(imNacosService.getImServerInstance(),"获取成功");
    }

}
