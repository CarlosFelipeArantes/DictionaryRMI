import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DicionarioClient {
	
	private static String palavra,significado;

	public static void main(String[] args) {
				try {
			        System.setProperty("java.security.policy","/home/carlos/git/DictionaryRMI/bin/rmi.policy");
					Registry r = LocateRegistry.getRegistry("localhost");
					Dicionario dicionario = (Dicionario) r.lookup("DictionaryService");
		
					janela(dicionario);

				} catch (Exception e) {
					e.printStackTrace();
				}
		
		}
	private static void janela(Dicionario dic) {
		JFrame janelaCentral = new JFrame();
		janelaCentral.setTitle("Janela-Cliente");
		janelaCentral.setSize(600, 100);
		janelaCentral.getContentPane().setLayout(new GridBagLayout());
		
		JButton consultar = new JButton("Consultar palavra");
		consultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame novo = new JFrame();
				novo.getContentPane().setLayout(new GridBagLayout());
				novo.setSize(600, 100);
				JTextField texto = new JTextField();

				JLabel label = new JLabel();
				JButton botao = new JButton();
				botao.setText("Confirmar!");
				texto.setText("Digite a palavra aqui!");

				texto.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    texto.setText("");
					  }
				});
				
				botao.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						setPalavra(texto.getText());
						try {
							String consulta = dic.consultarPalavra(palavra);
							if (consulta == null) {
								JOptionPane.showMessageDialog(null, "Palavra inexistente!", "Mensagem", JOptionPane.ERROR_MESSAGE);

							}else if (consulta.charAt(0)=='1') {
								JOptionPane.showMessageDialog(null, "Sistema em atualização!", "Mensagem", JOptionPane.ERROR_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null, "Palavra encontrada com sucesso!\nSignificado é:"+consulta+".");

							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						
							
						novo.setVisible(false);
					}
					
				});
				
				novo.add(label);
				novo.add(texto);
				novo.add(botao);
				
				novo.setLocationRelativeTo(null);
				novo.setVisible(true);
				
				
			}
			
			
		});
		
		

		JButton incluir = new JButton("Incluir palavra");
		incluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame novo = new JFrame();
				novo.getContentPane().setLayout(new GridBagLayout());
				novo.setSize(600, 100);
				JTextField texto = new JTextField();
				JTextField texto2 = new JTextField();

				JLabel label = new JLabel();
				JButton botao = new JButton();
				botao.setText("Confirmar!");
				texto.setText("Digite a palavra aqui!");
				texto2.setText("Digite o significado aqui!");

				texto.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    texto.setText("");
					  }
				});
				
				texto2.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    texto2.setText("");
					  }
				});
				
				botao.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						setPalavra(texto.getText());
						setSignificado(texto2.getText());
						try {
							boolean incluir = dic.incluirPalavra(palavra, significado);
							if(incluir) {
								JOptionPane.showMessageDialog(null, "Palavra inserida com sucesso!");
							}else {
								JOptionPane.showMessageDialog(null, "Palavra Existente!", "Mensagem", JOptionPane.ERROR_MESSAGE);
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						novo.setVisible(false);
					}
					
				});
				
				novo.add(label);
				novo.add(texto);
				novo.add(texto2);
				novo.add(botao);
				
				novo.setLocationRelativeTo(null);
				novo.setVisible(true);
				
				
			}
			
			
		});



		JButton remover = new JButton("Remover palavra");
		remover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame novo = new JFrame();
				novo.getContentPane().setLayout(new GridBagLayout());
				novo.setSize(600, 100);
				JTextField texto = new JTextField();


				JLabel label = new JLabel();
				JButton botao = new JButton();
				botao.setText("Confirmar!");
				texto.setText("Digite a palavra aqui!");

				texto.addMouseListener(new MouseAdapter() {
					  public void mouseClicked(MouseEvent e) {
					    texto.setText("");
					  }
				});
				
				botao.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						setPalavra(texto.getText());
						try {
							boolean excluir = dic.excluirPalavra(palavra);
							if(excluir) {
								JOptionPane.showMessageDialog(null, "Palavra deletada com sucesso!");
							}else {
								JOptionPane.showMessageDialog(null, "Palavra Inexistente!", "Mensagem", JOptionPane.ERROR_MESSAGE);
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						novo.setVisible(false);
					}
					
				});
				
				novo.add(label);
				novo.add(texto);
				novo.add(botao);
				
				novo.setLocationRelativeTo(null);
				novo.setVisible(true);
				
				
			}
			
			
		});

		JPanel panel = new JPanel();

		panel.add(consultar);
		panel.add(incluir);
		panel.add(remover);

		janelaCentral.getContentPane().add(panel);
		janelaCentral.setLocationRelativeTo(null);
		janelaCentral.setVisible(true);
		janelaCentral.setVisible(true);


	}
	
	private static void setPalavra(String p) {
		palavra=p;
	}
	
	private static void setSignificado(String s) {
		significado=s;
	}
}
