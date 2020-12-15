package com.qf.dao;

import com.qf.pojo.BulletChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BulletChatRepository extends JpaRepository<BulletChat,Integer> {

    List<BulletChat> findByMid(Integer mid);

}
