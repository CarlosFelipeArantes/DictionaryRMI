import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
* Trata-se da classe servidor responsável por registrar e disponibilizar
* o objeto Dicionario para o cliente. Sem regra de negócio alguma adicionada.
*
* @author  Carlos Felipe de Almeida Arantes
* @version 2.0
* @since   2018-08-28 
*/
public class DicionarioServer
{
	private static Registry r;

	public static void main(String args[])
	{
		try 
		{
			Dicionario d = new DicionarioServant();
			r = LocateRegistry.createRegistry(1099);
	        System.setProperty("java.security.policy","/home/carlos/git/DictionaryRMI/bin/rmi.policy");
			r.rebind("DictionaryService", d);
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	
}
