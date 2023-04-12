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

import com.gft.ReclameJa.entities.Client;
import com.gft.ReclameJa.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> getAllClients() {
		return ResponseEntity.ok(clientService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		return ResponseEntity.status(201).body(clientService.saveClient(client));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Client> getClient(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(clientService.findClient(id));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(clientService.update(client, id));
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
		try {
			clientService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
