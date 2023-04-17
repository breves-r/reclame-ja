package com.gft.ReclameJa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gft.ReclameJa.entities.Client;
import com.gft.ReclameJa.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}
	
	public List<Client> getAll() {
		return clientRepository.findAll();
	}
	
	public Client findClient(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
	}
	
	public Client findClientByCpf(String cpf) {
		return clientRepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Client not found"));
	}
	
	public Client update(Client client, Long id) {
		Client clientBd = this.findClient(id);
		client.setId(clientBd.getId());
		
		return this.saveClient(client);
	}
	
	public void delete(Long id) {
		Client client = this.findClient(id);
		
		clientRepository.delete(client);
	}
}
