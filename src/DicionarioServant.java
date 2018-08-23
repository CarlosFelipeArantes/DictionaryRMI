import java.util.HashMap;
import java.util.Map;

public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario {
	
	Map<String,Palavra> dicionario;

	private static final long serialVersionUID = 1L;

	public DicionarioServant() throws java.rmi.RemoteException {
		super();
	}
	
	public static void carregarDicionario(){
		
		Map<String,Palavra> dicionario = new HashMap<String,Palavra>();
		
	}


	public double somar(double a, double b) throws java.rmi.RemoteException {
		return a + b;
	}

	public double subtrair(double a, double b) throws java.rmi.RemoteException {
		return a - b;
	}

	public double multiplicar(double a, double b) throws java.rmi.RemoteException {
		return a * b;
	}

	public double dividir(double a, double b) throws java.rmi.RemoteException {
		return a / b;
	}
}
