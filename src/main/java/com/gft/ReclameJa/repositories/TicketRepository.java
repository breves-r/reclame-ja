package com.gft.ReclameJa.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.ReclameJa.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

	List<Ticket> findByCnpj(String cnpj);
	
	List<Ticket> findByUpdatedDateBetween(LocalDateTime from, LocalDateTime to);
}
