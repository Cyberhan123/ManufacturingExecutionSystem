package cn.hselfweb.mes.interfaces;
import cn.hselfweb.mes.enity.Client;

import java.util.List;


public interface ClientService {
    public int addClient(Client c);

    public void modifyClient(Client c);

    public void deleteClient(Client c);

    public Client findClientById(int id);

    public List<Client> findAllClient();

    public List<Client> findClientByPropertys(String model,
                                              String[] propertyName, String[] value);

}
