package com.example.project_shop.service.impl;


import com.example.project_shop.entity.MessageEntity;
import com.example.project_shop.entity.RoomEntity;
import com.example.project_shop.model.SendMessage;
import com.example.project_shop.repository.MessageRepo;
import com.example.project_shop.repository.RoomRepo;
import com.example.project_shop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    MessageRepo messageRepo;
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    UserRepo userRepo;

    public RoomEntity createRoom(RoomEntity room) {
        return roomRepo.save(room);
    }

    public List<RoomEntity> findAllRoomByUser(Long idUser) {
        return roomRepo.findAllByUser1OrUser2OrderByTimeCreateDesc(idUser);
    }

    public MessageEntity sendMessage(SendMessage message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContent(message.getContent());
        messageEntity.setByUser(message.getByUser());
        messageEntity.setIdRoom(message.getIdRoom());
        messageEntity.setTimeCreate(LocalDateTime.now());
        return messageEntity;
    }


    public List<MessageEntity> getAllMessByRoom(Long idRoom){
        return messageRepo.getAllByIdRoom(idRoom);
    }

}
