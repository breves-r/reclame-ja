package com.gft.ReclameJa.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.ReclameJa.entities.Ticket;
import com.gft.ReclameJa.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	private final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@GetMapping
	public ResponseEntity<List<Ticket>> getAllTickets() {
		return ResponseEntity.ok(ticketService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) {
		return ResponseEntity.status(201).body(ticketService.saveTicket(ticket));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(ticketService.findTicket(id));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("company/{cnpj}")
	public ResponseEntity<List<Ticket>> getTicketByCnpj(@PathVariable String cnpj) {
		return ResponseEntity.ok(ticketService.findTicketByCnpj(cnpj));
	}
	
	@GetMapping("date/{date}")
	public ResponseEntity<List<Ticket>> getTicketByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		return ResponseEntity.ok(ticketService.findTicketByDate(date));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(ticketService.update(ticket, id));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Ticket> deleteTicket(@PathVariable Long id) {
		try {
			ticketService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
