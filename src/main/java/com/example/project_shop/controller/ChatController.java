package com.example.project_shop.controller;

import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.MessageEntity;
import com.example.project_shop.entity.RoomEntity;
import com.example.project_shop.model.SendMessage;
import com.example.project_shop.service.impl.ChatService;
import com.example.project_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatService chatService;
    @GetMapping("/getRoomsByUser")
    public ResponseDto<List<RoomEntity>> getRoomsByUser(@RequestParam Long userId){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chatService.findAllRoomByUser(userId));
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }

    @PostMapping("/createRoom")
    public ResponseDto<RoomEntity> createRoom(@RequestBody RoomEntity room){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chatService.createRoom(room));
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }
    @PostMapping("/sendMessage")
    public ResponseDto<MessageEntity> sendMessage(@RequestBody SendMessage message){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chatService.sendMessage(message));
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }
    @GetMapping("/getAllMessByRoom")
    public ResponseDto<List<MessageEntity>> getAllMessByRoom(@RequestParam Long idRoom){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chatService.getAllMessByRoom(idRoom));
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }
}
