import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;


public class DicionarioServer {

	public DicionarioServer() {
		try {
			Dicionario d = new DicionarioServant();
			Naming.rebind("rmi://localhost/DictionaryService", d);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new DicionarioServer();
	}
	
	
}
