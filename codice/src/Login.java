import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.util.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;



import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.util.StringTokenizer;
import java.lang.String;



//per la lettura e scrittura da file
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JPasswordField;


public class Login {

	private JFrame frame;
	private JLabel lblPassword;
	private JTextField txtUsername;

	private String utente[]=new String[3];//per controllare il login
	boolean trovato=false; //per vedere se trovo l'utente
	private JPasswordField txtPassword;
	
	private String username;
	private String password;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(161, 13, 84, 25);
		frame.getContentPane().add(lblLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//se preme conferma...
				
				//devo controllare che l'user esista e che la password sia giusta
				// e poi devo selezionare se è un user di tipo magazziniere, resp, segretario
							
				try {
					File f= new File("utenti.txt");
					BufferedReader buf = new BufferedReader(new FileReader(f));
				
					//dopo apertura del buffer..
					String s = new String();
					
					s=buf.readLine();
				
					while(s!=null&&trovato==false) {
						
						String [] token = s.split(":");

						username = new String(txtUsername.getText());
						char[] pass = txtPassword.getPassword();
						
						password=String.valueOf(pass);
						
						if(token[0].equals(username) && token[1].equals(password)) {
							JOptionPane.showMessageDialog(null, "utente presente");
							utente=token;//salvo l'utente
							trovato=true;
							
						}
						else {
							s=buf.readLine();
						}
						
					}//fine while(s!=null)
				
					//se l'utente è presente
					if(trovato) {

						//1=segretario, 2= magazziniere e 3 =responsabile
						if(utente[2].equals("1")) {
							JOptionPane.showMessageDialog(null, "sei loggato come segretario");
							//creo la struttura dati catalogo tipi
							catalogoTipi tipiArticolo= new catalogoTipi();
							magazzino magazzino = new magazzino();							

							
							//lancio l'interfaccia segretario
							InterfacciaSegretario segretario = new InterfacciaSegretario(username);
							frame.dispose();//tolgo finestra login
							segretario.setVisible(true);//mostro il frame
							
						}
						if(utente[2].equals("2")) {
							JOptionPane.showMessageDialog(null, "sei loggato come magazziniere");
							//lancio l'interfaccia magazziniere
							//devo aprire il magazzino istanziando un oggetto di tipo magazzino
							catalogoTipi tipiArticolo= new catalogoTipi();
							magazzino magazzino = new magazzino();							
							
							InterfacciaMagazziniere magazziniere = new InterfacciaMagazziniere(username);
							frame.dispose();//tolgo finestra login
							magazziniere.setVisible(true);//mostro il frame
						
						}
						if(utente[2].equals("3")) {
							JOptionPane.showMessageDialog(null, "sei loggato come responsabile");
							//lancio l'interfaccia responsabile
							
							catalogoTipi tipiArticolo= new catalogoTipi();
							magazzino magazzino = new magazzino();		
							
							InterfacciaResponsabile responsabile = new InterfacciaResponsabile(username);
							frame.dispose();
							responsabile.setVisible(true);
						}
					
					}
					else {//trovato==false
						JOptionPane.showMessageDialog(null, "utente non trovato");
					}
					buf.close();
				}//fine try
				catch (Exception e) {
					e.printStackTrace();
				}	
			
			}//fine actionListener
		});
		
		btnLogin.setBounds(161, 187, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(212, 65, 116, 22);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(56, 68, 84, 16);
		frame.getContentPane().add(lblUserName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(56, 125, 84, 19);
		frame.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(212, 123, 116, 25);
		frame.getContentPane().add(txtPassword);
	}
}
