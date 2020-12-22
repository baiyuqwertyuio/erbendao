package com.qf.dao;

import com.qf.pojo.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog,Integer> {
}
