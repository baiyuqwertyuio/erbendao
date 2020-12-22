package com.qf.dao;

import com.qf.pojo.Seconde;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecondeRepository extends JpaRepository<Seconde,Integer> {
    List<Seconde> findByCid(Integer cid);
}
