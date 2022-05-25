package com.example.project_shop.repository;

import com.example.project_shop.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<MessageEntity,Long> {
    List<MessageEntity> getAllByIdRoom(Long idRoom);
}
