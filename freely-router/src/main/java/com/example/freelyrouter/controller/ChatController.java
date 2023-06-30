package com.example.freelyrouter.controller;


import com.example.freelyrouter.domain.request.PrivateChatRequest;
import com.example.freelyrouter.service.IChatService;
import org.freely.commom.core.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 消息发送接口
 */
@RestController
@RequestMapping("chat")
public class ChatController extends BaseController{

    @Autowired
    IChatService iChatService;

    /**
     * 私聊
     * @return
     */
    @PostMapping("/private")
    public ResponseEntity<ApiResponse<Object>> privateChat(@RequestBody PrivateChatRequest request){
        if (iChatService.publishPrivateMeg(request)){
            return successResponse("发送成功");
        }
        return errorResponse("发送失败！");
    }
}
