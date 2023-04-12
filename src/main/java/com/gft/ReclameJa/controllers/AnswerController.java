package com.gft.ReclameJa.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.ReclameJa.entities.Answer;
import com.gft.ReclameJa.services.AnswerService;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	private final AnswerService answerService;

	public AnswerController(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	@GetMapping
	public ResponseEntity<List<Answer>> getAllAnswers() {
		return ResponseEntity.ok(answerService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<Answer> saveAnswer(@RequestBody Answer answer) {
		try {
			return ResponseEntity.status(201).body(answerService.saveAnswer(answer));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Answer> getAnswer(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(answerService.findAnswer(id));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/ticket/{ticketId}")
	public ResponseEntity<List<Answer>> getAnswersByTicket(@PathVariable Long ticketId) {
		return ResponseEntity.ok(answerService.findAnswerByTicket(ticketId));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Answer> updateAnswer(@RequestBody Answer answer, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(answerService.update(answer, id));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Answer> deleteAnswer(@PathVariable Long id) {
		try {
			answerService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
