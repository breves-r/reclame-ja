package com.gft.ReclameJa.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gft.ReclameJa.entities.Ticket;
import com.gft.ReclameJa.repositories.TicketRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TicketService {

	private final TicketRepository ticketRepository;

	public TicketService(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}
	
	public Ticket saveTicket(Ticket ticket) {
		if(ticket.getId() == null)
			ticket.setUpdatedDate(ticket.getCreationDate());
		
		return ticketRepository.save(ticket);
	}
	
	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}
	
	public Ticket findTicket(Long id) {
		return ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
	}
	
	public List<Ticket> findTicketByCnpj(String cnpj) {
		return ticketRepository.findByCnpj(cnpj);
	}
	
	public List<Ticket> findTicketByDate(LocalDate date) {
		return ticketRepository.findByUpdatedDateBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
	}
	
	public Ticket update(Ticket ticket, Long id) {
		Ticket ticketBd = this.findTicket(id);
		ticket.setId(ticketBd.getId());
		ticket.setUpdatedDate(ticketBd.getUpdatedDate());
		
		return this.saveTicket(ticket);
	}
	
	public void delete(Long id) {
		Ticket ticket = this.findTicket(id);
		
		ticketRepository.delete(ticket);
	}
}
