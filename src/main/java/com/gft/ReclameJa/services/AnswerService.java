package com.gft.ReclameJa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gft.ReclameJa.entities.Answer;
import com.gft.ReclameJa.entities.Ticket;
import com.gft.ReclameJa.entities.TicketStatus;
import com.gft.ReclameJa.repositories.AnswerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnswerService {

	private final AnswerRepository answerRepository;
	private final TicketService ticketService;

	public AnswerService(AnswerRepository answerRepository, TicketService ticketService) {
		this.answerRepository = answerRepository;
		this.ticketService = ticketService;
	}
	
	public Answer saveAnswer(Answer answer) {
		this.updateTicket(answer);
		return answerRepository.save(answer);
	}
	
	public List<Answer> getAll() {
		return answerRepository.findAll();
	}
	
	public List<Answer> findAnswerByTicket(Long ticketId) {
		return answerRepository.findByTicketId(ticketId);
	}
	
	public Answer findAnswer(Long id) {
		return answerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Answer not found"));
	}
	
	public Answer update(Answer answer, Long id) {
		Answer answerBd = this.findAnswer(id);
		answer.setId(answerBd.getId());
		
		return this.saveAnswer(answer);
	}
	
	public void delete(Long id) {
		Answer answer = this.findAnswer(id);
		
		answerRepository.delete(answer);
	}
	
	private void updateTicket(Answer answer) throws EntityNotFoundException{
		Ticket ticket = ticketService.findTicket(answer.getTicket());
		
		if(answer.isCompany()) {
			ticket.setStatus(TicketStatus.ANSWERED);
		} else {
			ticket.setStatus(TicketStatus.CONSUMER_ANSWERED);
		}
		
		ticket.setUpdatedDate(answer.getCreationDate());
		
		ticketService.saveTicket(ticket);
	}
}
