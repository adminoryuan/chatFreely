package com.freely.service;

import com.freely.domain.request.LoginRequest;
import com.freely.domain.response.LoginResponse;

public interface LoginService {

    LoginResponse Login(LoginRequest request);
}
