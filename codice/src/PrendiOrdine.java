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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import java.lang.Thread;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class PrendiOrdine extends JFrame {

	private JPanel contentPane;
	private JTextField txtInserisciArticoli;
	private JLabel lblCodiceOrdineUnivoco;
	private JTextField txtNegozio;
	private JTextField txtCodiceOrdine;
	private JButton btnNewButton;
	private JButton btnConferma;

	private boolean codiceNonUnivoco=false;
	private int numeroTipiOrdinati=0;
	private int n=1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrendiOrdine frame = new PrendiOrdine();
					frame.setVisible(true);
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrendiOrdine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInserisciArticoli = new JTextField();
		txtInserisciArticoli.setText("inserisci dati ordine:");
		txtInserisciArticoli.setEditable(false);
		txtInserisciArticoli.setBounds(130, 11, 234, 33);
		contentPane.add(txtInserisciArticoli);
		txtInserisciArticoli.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("nome negozio: ");
		lblNewLabel.setBounds(36, 69, 109, 33);
		contentPane.add(lblNewLabel);
		
		lblCodiceOrdineUnivoco = new JLabel("codice ordine univoco:");
		lblCodiceOrdineUnivoco.setBounds(36, 113, 109, 33);
		contentPane.add(lblCodiceOrdineUnivoco);
		
		txtNegozio = new JTextField();
		txtNegozio.setBounds(205, 69, 185, 33);
		contentPane.add(txtNegozio);
		txtNegozio.setColumns(10);
		
		txtCodiceOrdine = new JTextField();
		txtCodiceOrdine.setColumns(10);
		txtCodiceOrdine.setBounds(205, 113, 185, 33);
		contentPane.add(txtCodiceOrdine);
		
		btnNewButton = new JButton("inserisci nuovo tipo articolo");
		btnNewButton.addActionListener(new ActionListener() {//inserimento articoli in un ordine
			public void actionPerformed(ActionEvent e) {
				
				if(n==1) {//pulisco articoli tmp solo quando rilancio il frame
					File f= new File("articoli_tmp.txt");
					f.delete();
					try {
						f.createNewFile();
					}
					catch(Exception error2) {
						error2.getMessage();
					}
					n++;
				}
				AcquisisciTipiArticoli tipiArticoli = new AcquisisciTipiArticoli();
				tipiArticoli.setVisible(true);
					
				//ora ho inserito l'i-esimo tipo articolo dell'ordine corrente
				//devo salvare in ordini.txt l'ordine corrente con il relativo tipo articolo e qtà
			}
		});
		btnNewButton.setBounds(10, 227, 208, 50);
		contentPane.add(btnNewButton);
		
		btnConferma = new JButton("conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//click di conferma ordine..
				//controllo che il codice ordine inserito sia univoco...
				
				try {
					File f= new File("ordini.txt");
    				BufferedReader bufCodice = new BufferedReader(new FileReader(f));
    				
    				String cod=bufCodice.readLine();
    				
    				while(cod!=null) {
    					String [] token = cod.split(":");
    					if(token[0].equals(txtCodiceOrdine.getText() ) ) {
    						JOptionPane.showMessageDialog(null, "codice ordine già presente");
    						JOptionPane.showMessageDialog(null, "ORDINE NON EVASO");
    						codiceNonUnivoco=true;
    						dispose();
    					}
    					cod=bufCodice.readLine();
    				}
    				
    				bufCodice.close();
				}
				catch(Exception e) {
					e.getMessage();
				}
				
				if(!codiceNonUnivoco) {//se il cod è univoco
					try {
						FileWriter w = new FileWriter("ordini.txt", true);
						//false sta per NON append quindi sovrascrivere!!
						BufferedWriter bwriter = new BufferedWriter(w);
	    				
						try {
	    					
							File f= new File("articoli_tmp.txt");
							BufferedReader buf = new BufferedReader(new FileReader(f));
	    				
							String s=new String();
							s=buf.readLine();
	    				
							while(s!=null) {
								String [] token = s.split(":");
							
								//primo articolo dell'ordine
								StringBuffer nuovoOrdine = new StringBuffer();
								nuovoOrdine.append(txtCodiceOrdine.getText()+":");
								nuovoOrdine.append(txtNegozio.getText()+":");
								nuovoOrdine.append(token[0]+":");//tipo articolo dell'articolo
								nuovoOrdine.append(token[1]);//q.tà
								//devo aggiungere il tag evaso o no per il magazziniere
								nuovoOrdine.append(":"+"false");//non evaso all'inizio
	    					
	    					
								bwriter.write(new String("\n"+nuovoOrdine),0, nuovoOrdine.length()+1);
	    					
	    					
		    				
								numeroTipiOrdinati++;//incremento il contatore per il controllo alla conferma
								s=buf.readLine();
								if(s!=null) bwriter.newLine();
							}	
	    						bwriter.close();
	    						buf.close();
	    				
						}//fine try
						catch(Exception e) {
							e.getMessage();
						}
					}//fine primo try
					catch(Exception e2) {
						e2.getMessage();
					}
				
				
					try {
						File f= new File("ordini.txt");
						BufferedReader buf = new BufferedReader(new FileReader(f));
					
						//dopo apertura del buffer..
						String s = new String();
						int num=0;
					
						s=buf.readLine();
						while(s!=null) {
							String [] token = s.split(":");
							if(token[0].equals(txtCodiceOrdine.getText())) {//conto gli ord con un certo codice
								num++;
							}
							s=buf.readLine();
						}//fine while
					
						buf.close();
						//System.out.println("num="+num);
						//System.out.println("ord="+numeroTipiOrdinati);
						if(num==numeroTipiOrdinati) {
							//se il codice ordine è presente le volte giuste rispetto a quelle previste
							JOptionPane.showMessageDialog(null, "ordine inserito correttamente");
						}
						
					}//fine try
					catch (Exception e2) {
						e2.printStackTrace();
					}	
				}//fine di if codice non univoco
				dispose();
			}
		});
		btnConferma.setBounds(276, 227, 208, 50);
		contentPane.add(btnConferma);
	}
}
