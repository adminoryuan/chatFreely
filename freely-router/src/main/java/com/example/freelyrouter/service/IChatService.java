package com.example.freelyrouter.service;

import com.example.freelyrouter.domain.request.PrivateChatRequest;

public interface IChatService {

    boolean publishPrivateMeg(PrivateChatRequest request);
}
