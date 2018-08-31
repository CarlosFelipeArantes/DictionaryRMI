/**
* Trata-se da interface a ser implementada no Servant a
* ser disponibilizado pelo servidor no RMIRegistry e que é
* conhecida pelo cliente, possibilitando-o realizar as operações.
*
* @author  Carlos Felipe de Almeida Arantes
* @version 3.0
* @since   2018-08-30 
*/
public interface Dicionario extends java.rmi.Remote {

	/**
	 * @param String palavra - Palavra a ser consultada
	 * @return String - Null, 1- ou Significado
	 * @throws java.rmi.RemoteException
	 */
	public String consultarPalavra(String x) throws java.rmi.RemoteException;
	/**
	 * @param String palavra - Palavra a ser excluída
	 * @return boolean - Sucesso ou Falha
	 * @throws java.rmi.RemoteException
	 */
	public boolean excluirPalavra(String x) throws java.rmi.RemoteException;
	/**
	 * @param String palavra - - Palavra a ser excluída
	 * @return boolean - Sucesso ou Falha
	 * @throws java.rmi.RemoteException
	 */
	public boolean incluirPalavra(String palavra, String significado) throws java.rmi.RemoteException;

}
