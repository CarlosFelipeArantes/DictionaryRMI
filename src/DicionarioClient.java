import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;

public class DicionarioClient {

	public static void main(String[] args) {
//		try {
//	        System.setProperty("java.security.policy","/home/carlos/git/DictionaryRMI/bin/rmi.policy");
//			Registry r = LocateRegistry.getRegistry("localhost");
//			Dicionario d = (Dicionario) r.lookup("DictionaryService");
//			System.out.println(d.incluirPalavra("MBS-se-", "Eh pu-ra beleza-."));
//
//			System.out.println(d.consultarPalavra("MBS-se-"));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		JFrame janela = new JFrame("Meu primeiro frame em Java");
        janela.setSize(300,200);
        janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}
}
