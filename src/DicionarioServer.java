import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


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
