public interface Dicionario extends java.rmi.Remote {

	public String consultarPalavra(String x) throws java.rmi.RemoteException;
	public boolean excluirPalavra(String x) throws java.rmi.RemoteException;
	public boolean incluirPalavra(String palavra, String significado) throws java.rmi.RemoteException;

}
