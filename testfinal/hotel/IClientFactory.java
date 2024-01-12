package hotel;

public interface IClientFactory {
	IClient createClient(String nom, String prenom);
}
