import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
* Trata-se da classe que irá disponibilizar o objeto no servidor
* para ser acessado pelo cliente. 
* Implementa toda a regra de negócio com excessão dos erros, que
* é tratado no cliente como resposta às operações.
*
* @author  Carlos Felipe de Almeida Arantes
* @version 4.0
* @since   2018-08-29 
*/
public class DicionarioServant extends java.rmi.server.UnicastRemoteObject implements Dicionario,Runnable {
	
	private static final long serialVersionUID = 1L;
	private volatile Map<String,String> dicionario;
	private volatile boolean sinal=false;
	private File dicionarioFile;

	/**
	   * Construtor do objeto a ser disponibilizado remotamente.
	   * Obs: Cria a si mesmo como uma thread que fará de tempos 
	   * em tempos a gravação em arquivo, um "dump" do dicionario
	   * implementado como HashMap
	   */
	public DicionarioServant() throws java.rmi.RemoteException {
		super();
		this.dicionario = new HashMap<String,String>();
		dicionarioFile = new File("dicionario.txt");
		this.carregarDicionario();
		new Thread (this).start();
		sinal = true;
	}
	
	private void carregarDicionario()
	{
		
		String [] linhas;
		
		try
		{
			FileReader file = new FileReader(dicionarioFile);
			BufferedReader br = new BufferedReader(file);
			String line = br.readLine();

			while (line!=null)
			{
				linhas = line.split("-");

				if (linhas.length == 2)
				{
					dicionario.put(linhas[0], linhas[1]);
				}
				
				line = br.readLine();
				
			}			
			
			file.close();
		}
		catch (IOException e)
		{
			System.out.println(e.getLocalizedMessage());	
		}
		
	}

	public String consultarPalavra(String palavra) throws java.rmi.RemoteException
	{
		palavra=this.tratarString(palavra);
		if (this.sinal==true)
		{
			return dicionario.get(palavra);
		}
		else 
		{
			return "1-Sistema em atualização!";
		}
	}

	public synchronized boolean incluirPalavra(String palavra, String significado) throws java.rmi.RemoteException
	{
		this.sinal = false;
		palavra = tratarString(palavra);
		significado = tratarString(significado);
		if (this.dicionario.get(palavra)==null)
		{
			this.dicionario.put(palavra, significado);
			this.sinal = true;
			return true;
		}
		else 
		{
			this.sinal = true;
			return false;
		}

	}

	public synchronized boolean excluirPalavra(String palavra) throws java.rmi.RemoteException
	{
		this.sinal = false;
		if (this.dicionario.get(palavra)!= null) {
			this.dicionario.remove(palavra);
			this.sinal = true;
			return true;
		}else {
			this.sinal = true;
			return false;
		}
	}
	/**
	 * Metódo responsável pela execução de código em thread que é executado
	 * de 3 em 3 segundos para realizar a escrita do dicionario.
	   */
	@Override
	public void run()
	{
		while(true) 
		 {
			try 
			{	
				TimeUnit.SECONDS.sleep(3);
				try
				{
					BufferedWriter writer = new BufferedWriter(new FileWriter(dicionarioFile,false));
					this.dicionario.forEach(	(key,value)->
													{
														try
														{
														writer.write(key +"-"+ value+"\n");
														} 
														catch (IOException e)
														{
														e.printStackTrace();
														}
											});
					writer.close();
				} 
				catch (Exception e)
				{
					e.getMessage();
				}
				
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}		
			
		 }
	}
	
	public String tratarString (String texto)
	{
		texto = texto.replaceAll("-", "");
		texto = texto.trim();
		return texto;
		
	}
	
	public void printarDicionario()
	{
		System.out.println(dicionario.toString());
	}
}
