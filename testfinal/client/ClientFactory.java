package client;

import hotel.IClient;
import hotel.IClientFactory;

public class ClientFactory implements IClientFactory {

	@Override
	public IClient createClient(String nom, String prenom) {
		return new Client(nom, prenom);
	}

}
