package cn.hselfweb.mes.service;


import cn.hselfweb.mes.enity.Client;
import cn.hselfweb.mes.interfaces.ClientService;

import java.util.List;

public class ClientServiceImp implements ClientService {


	@Override
	public int addClient(Client c) {
		return 0;
	}

	@Override
	public void modifyClient(Client c) {

	}

	@Override
	public void deleteClient(Client c) {

	}

	@Override
	public Client findClientById(int id) {
		return null;
	}

	@Override
	public List<Client> findAllClient() {
		return null;
	}

	@Override
	public List<Client> findClientByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}
}
