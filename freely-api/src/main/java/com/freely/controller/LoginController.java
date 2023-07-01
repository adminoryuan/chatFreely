package com.freely.controller;


import com.freely.domain.request.LoginRequest;
import com.freely.service.LoginService;
import com.freely.service.TokenService;
import org.freely.commom.core.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController{
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> Login(@RequestBody LoginRequest request){
        return successResponse(loginService.Login(request),"登录成功！");
    }



}
