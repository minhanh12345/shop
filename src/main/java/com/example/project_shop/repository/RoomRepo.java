package com.example.project_shop.repository;

import com.example.project_shop.entity.MessageEntity;
import com.example.project_shop.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepo extends JpaRepository<RoomEntity, Long> {

    @Query("select r from RoomEntity r where r.user1 = ?1 or r.user2 = ?2 order by r.timeCreate DESC")
    List<RoomEntity> findAllByUser1OrUser2OrderByTimeCreateDesc(Long user);

}

