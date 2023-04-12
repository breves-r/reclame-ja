package com.gft.ReclameJa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.ReclameJa.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
