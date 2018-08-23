import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;


public class DictionaryServer {

	public DictionaryServer() {
		try {
			Dictionary d = new DictionaryServant();
			Naming.rebind("rmi://localhost/DictionaryService", d);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new DictionaryServer();
	}
	
	public static void carregarDicionario(){
		
		Map<String,Palavra> dicionario = new HashMap<String,Palavra>();
		
	}

}
