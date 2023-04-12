package com.gft.ReclameJa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.ReclameJa.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

	List<Answer> findByTicketId(Long ticketId);
}
