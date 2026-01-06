package com.soa.td1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soa.td1.model.entity.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

   
    
}
