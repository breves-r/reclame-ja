package com.gft.ReclameJa.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;
	
	private boolean isCompany = true;
	
	@ManyToOne
	private Ticket ticket;

	public Answer() {}
	
	public Answer(Long id, String message, LocalDateTime creationDate, boolean isCompany, Ticket ticket) {	
		this.id = id;
		this.message = message;
		this.creationDate = creationDate;
		this.isCompany = isCompany;
		this.ticket = ticket;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@JsonProperty("is_company")
	public boolean isCompany() {
		return isCompany;
	}

	@JsonProperty("is_company")
	public void setCompany(boolean isCompany) {
		this.isCompany = isCompany;
	}

	public Long getTicket() {
		return ticket.getId();
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
}
