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
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class InterfacciaInserimentoArticoli extends JFrame {

	private JPanel contentPane;
	private JTextField txtInserisciInformazioniArticolo;
	private JTextField txtCodiceInterno;
	private JTextField txtDataIngresso;
	private JTextField txtPosizione;

	boolean codiceNonUnivoco = false; //serve per controllare univocità codice articolo
	boolean dataValida = true;
	boolean posizioneValida = true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaInserimentoArticoli frame = new InterfacciaInserimentoArticoli();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//per il controllo della data
	public static boolean isDateValid (int day, int month, int year) {
		GregorianCalendar cal = new GregorianCalendar (year, month-1, day);
		cal.setLenient (false);

		try {
			cal.get (Calendar.DATE);
			return true;
		} 
		catch (IllegalArgumentException e) {
			return false;
		}
	} //fine isValid()
	
	
	

	/**
	 * Create the frame.
	 */
	public InterfacciaInserimentoArticoli() {
		setTitle("Entrata articolo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInserisciInformazioniArticolo = new JTextField();
		txtInserisciInformazioniArticolo.setEditable(false);
		txtInserisciInformazioniArticolo.setText("Inserisci informazioni articolo per magazzino:");
		txtInserisciInformazioniArticolo.setBounds(95, 11, 322, 20);
		contentPane.add(txtInserisciInformazioniArticolo);
		txtInserisciInformazioniArticolo.setColumns(10);
		
		JLabel lblInserisciNuovoCodice = new JLabel("inserisci nuovo codice interno:");
		lblInserisciNuovoCodice.setBounds(10, 57, 246, 29);
		contentPane.add(lblInserisciNuovoCodice);
		
		JLabel lblDataIngresso = new JLabel("data ingresso:");
		lblDataIngresso.setBounds(10, 111, 128, 29);
		contentPane.add(lblDataIngresso);
		
		JLabel lblPosizioneArticolo = new JLabel("posizione articolo:");
		lblPosizioneArticolo.setBounds(10, 161, 152, 29);
		contentPane.add(lblPosizioneArticolo);
		
		txtCodiceInterno = new JTextField();
		txtCodiceInterno.setBounds(284, 57, 187, 29);
		contentPane.add(txtCodiceInterno);
		txtCodiceInterno.setColumns(10);
		
		txtDataIngresso = new JTextField();
		txtDataIngresso.setColumns(10);
		txtDataIngresso.setBounds(284, 111, 187, 29);
		contentPane.add(txtDataIngresso);
		
		txtPosizione = new JTextField();
		txtPosizione.setColumns(10);
		txtPosizione.setBounds(284, 161, 187, 29);
		contentPane.add(txtPosizione);
		
		JButton btnNewButton = new JButton("conferma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//se clicca conferma entrata
				//devo controllare i restanti parametri di ingresso in file entrate...
				
				try {
					File f= new File("entrate.txt");
					BufferedReader buf = new BufferedReader(new FileReader(f));
					
					//dopo apertura del buffer..
					String s = new String();
					
					s=buf.readLine();
				
					while(s!=null&&!codiceNonUnivoco&&dataValida&&posizioneValida) {
						
						String [] token = s.split(":");
						
						if(token[0].equals(txtCodiceInterno.getText())) {//se il codice non è univoco
							JOptionPane.showMessageDialog(null, "codice interno entrata già presente");
							codiceNonUnivoco=true;
						}
						String data=txtDataIngresso.getText();
						String [] pezziData = data.split("/");
						
						if(!isDateValid(new Integer(pezziData[0]).intValue(), new Integer(pezziData[1]).intValue(), new Integer(pezziData[2]).intValue())) {
							JOptionPane.showMessageDialog(null, "ERRORE: data non valida");
							dataValida=false;
						}
						if(new Integer(new String(txtPosizione.getText() ) ).intValue() < 0 || token[2].equals(txtPosizione.getText() ) ) {
							JOptionPane.showMessageDialog(null, "ERRORE: posizione negativa o non valida");
							posizioneValida=false;
							
						}
						
						
						s=buf.readLine();
						
						
					}//fine while(s!=null)
				
				
					//se articolo passa il controllo...lo metto in entrate
					if(!codiceNonUnivoco&&dataValida&&posizioneValida) {
						//aggiungo l'ultimo articolo nel file....il magazzino è già stato aggiornato
						FileWriter w = new FileWriter("entrate.txt",true);//true sta per append quindi senza sovrscrivere!!
		    			BufferedWriter bwriter = new BufferedWriter(w);
						//recupero info articolo
						while(prendiDatiArticolo.numeroInserimenti>0) {
							String infoArticolo= new String();
							int dim=magazzino.articoli.size();
							infoArticolo = magazzino.getArticolotoString(dim - prendiDatiArticolo.numeroInserimenti); //metodo statico

			    			StringBuffer nuovaEntrata = new StringBuffer();
			    			nuovaEntrata.append(txtCodiceInterno.getText()+":");
			    			nuovaEntrata.append(txtDataIngresso.getText()+":");
			    			nuovaEntrata.append(txtPosizione.getText()+":");
			    			nuovaEntrata.append(infoArticolo);
			    		
			    			//vado a capo solo se il file non è vuoto
			    			if(new File("entrate.txt").length()!=0) {
			    				bwriter.newLine();
			    			}
			    			bwriter.write(new String(nuovaEntrata),0, nuovaEntrata.length());
							JOptionPane.showMessageDialog(null, "articolo inserito correttamente in entrate");
							prendiDatiArticolo.numeroInserimenti--;
						}//fine while() 
						
						bwriter.close();
						
			    		dispose();//chiude la finestra
			    		
					}
					else {
						JOptionPane.showMessageDialog(null, "ERRORE: articolo non inserito");
						dispose();
					}
					buf.close();
					
				}//fine try
				catch (Exception errore) {
					errore.printStackTrace();
				}	
				
			}
		});
		btnNewButton.setBounds(280, 231, 191, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("inserisci info articolo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//creo l'oggetto articolo
				prendiDatiArticolo infoArticolo = new prendiDatiArticolo();
				infoArticolo.setVisible(true);

			}
		});
		btnNewButton_1.setBounds(45, 231, 192, 50);
		contentPane.add(btnNewButton_1);
	}

}
