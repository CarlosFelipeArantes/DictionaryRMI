
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class DicionarioClient {

	public static void main(String[] args) {
		String servidor = "rmi://localhost/";
		String nome = "DictionaryService";
		try {
			Dicionario d = (Dicionario) Naming.lookup(servidor + nome);
			System.out.println("Objeto remoto \'"+ nome + "\' encontrado no servidor.");
			

		} catch (MalformedURLException e) {
			System.out.println("URL \'" + servidor + nome + "\' mal formatada.");
		} catch (RemoteException e) {
			System.out.println("Erro na invocação remota.");
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("Objeto remoto \'" + nome + "\' não está disponível.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
