import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;

import java.io.*;


public class InterfacciaInserimentoTipoArticolo extends JFrame {

	private JPanel contentPane;
	private JTextField txtInserisciLeSpecifiche;
	private JTextField txtNome;
	private JTextField txtDescrizione;
	private JTextField txtSport;
	private JTextField txtMateriale1;
	private JTextField txtMateriale2=null;//metto a null per controllarli dopo..
	private JTextField txtMateriale3=null;

	
	boolean trovato=false;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaInserimentoTipoArticolo frame = new InterfacciaInserimentoTipoArticolo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfacciaInserimentoTipoArticolo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInserisciLeSpecifiche = new JTextField();
		txtInserisciLeSpecifiche.setText("inserisci le specifiche del tipo articolo");
		txtInserisciLeSpecifiche.setEditable(false);
		txtInserisciLeSpecifiche.setBounds(108, 11, 223, 27);
		contentPane.add(txtInserisciLeSpecifiche);
		txtInserisciLeSpecifiche.setColumns(10);
		
		JLabel lblNomeTipoArticolo = new JLabel("nome univoco:");
		lblNomeTipoArticolo.setBounds(10, 58, 100, 27);
		contentPane.add(lblNomeTipoArticolo);
		
		JButton btnNewButton = new JButton("conferma inserimento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*a questo punto devo controllare che il tipo articolo non sia già presente (nome univoco)
				e se supera tale controllo lo inserisco */
			
				try {
					
					File f= new File("tipiArticolo.txt");
					BufferedReader buf = new BufferedReader(new FileReader(f));

					String s=buf.readLine();//leggo la prima riga del file					
					
					while(s!=null&&trovato==false) {
						
						String nome = s.split(":")[0];//salvo solo il nome del i-esimo tipo articolo
						if(nome.equals(txtNome.getText())){
							JOptionPane.showMessageDialog(null, "ERRORE: tipo articolo già presente");
							trovato=true;
						}
						
						s=buf.readLine();//altrimenti procedo a controllare il prossimo oggetto
					}//fine di while
					
					buf.close();	
					
					if(!trovato) {
						try {
							//se il controllo ha funzionato bene lo aggiungo alla lista
							FileWriter w = new FileWriter("tipiArticolo.txt",true);//true sta per append quindi senza sovrscrivere!!
				    		BufferedWriter bwriter = new BufferedWriter(w);
				    						    		
				    		StringBuffer nuovoArticolo = new StringBuffer();
				    		nuovoArticolo.append(txtNome.getText()+":");
				    		nuovoArticolo.append(txtDescrizione.getText()+":");
				    		nuovoArticolo.append(txtSport.getText()+":");
				    		nuovoArticolo.append(txtMateriale1.getText());
				    
				    		if(txtMateriale2.getText()!=null) {//fino ad un massimo di 4 materiali per tipo di articolo
					    		nuovoArticolo.append(":"+txtMateriale2.getText());
				    		}				    
				    		if(txtMateriale3.getText()!=null) {
					    		nuovoArticolo.append(":"+txtMateriale3.getText());
				    		}
				    		
				    		//vado a capo prima di inserire elemento
				    		bwriter.newLine();
				    		
				    		bwriter.write(new String(nuovoArticolo),0, nuovoArticolo.length());
							JOptionPane.showMessageDialog(null, "tipo articolo inserito correttamente");
				    		bwriter.close();
				    		
						}//fine di try
						catch(FileNotFoundException  e) {
							e.printStackTrace();
						}
						
					}//fine di if trovato
				}//fine try
				catch (Exception  e) {
					e.printStackTrace();
				}

				dispose();
			}//fine listener
			
		});
		btnNewButton.setBounds(108, 379, 199, 51);
		contentPane.add(btnNewButton);
		
		JLabel lblDescrizione = new JLabel("descrizione:");
		lblDescrizione.setBounds(10, 109, 100, 27);
		contentPane.add(lblDescrizione);
		
		JLabel lblSport = new JLabel("sport:");
		lblSport.setBounds(10, 158, 100, 27);
		contentPane.add(lblSport);
		
		JLabel lblMaterialiUtilizzati = new JLabel("materiali utilizzati:");
		lblMaterialiUtilizzati.setBounds(10, 223, 100, 27);
		contentPane.add(lblMaterialiUtilizzati);
		
		txtNome = new JTextField();
		txtNome.setBounds(103, 59, 204, 24);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtDescrizione = new JTextField();
		txtDescrizione.setColumns(10);
		txtDescrizione.setBounds(103, 110, 204, 24);
		contentPane.add(txtDescrizione);
		
		txtSport = new JTextField();
		txtSport.setColumns(10);
		txtSport.setBounds(103, 159, 204, 24);
		contentPane.add(txtSport);
		
		txtMateriale1 = new JTextField();
		txtMateriale1.setColumns(10);
		txtMateriale1.setBounds(103, 224, 204, 24);
		contentPane.add(txtMateriale1);
		
		txtMateriale2 = new JTextField();
		txtMateriale2.setColumns(10);
		txtMateriale2.setBounds(103, 269, 204, 24);
		contentPane.add(txtMateriale2);
		
		txtMateriale3 = new JTextField();
		txtMateriale3.setColumns(10);
		txtMateriale3.setBounds(103, 316, 204, 24);
		contentPane.add(txtMateriale3);
	}
}
