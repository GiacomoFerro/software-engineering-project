import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class aggiornaPosizione extends JFrame {

	private JPanel contentPane;
	private JTextField txtInserisciDatiArticolo;
	private JLabel lblInserisciNuovaPosizione;
	private JTextField txtCodiceArticolo;
	private JTextField txtNewPos;

	boolean oggettoTrovato=false;
	boolean posizioneValida=true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aggiornaPosizione frame = new aggiornaPosizione();
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
	public aggiornaPosizione() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInserisciDatiArticolo = new JTextField();
		txtInserisciDatiArticolo.setHorizontalAlignment(SwingConstants.CENTER);
		txtInserisciDatiArticolo.setText("inserisci dati articolo: ");
		txtInserisciDatiArticolo.setEditable(false);
		txtInserisciDatiArticolo.setBounds(163, 16, 303, 26);
		contentPane.add(txtInserisciDatiArticolo);
		txtInserisciDatiArticolo.setColumns(10);
		
		JLabel lblInserisciCodiceInterno = new JLabel("inserisci codice articolo:");
		lblInserisciCodiceInterno.setBounds(49, 76, 241, 81);
		contentPane.add(lblInserisciCodiceInterno);
		
		lblInserisciNuovaPosizione = new JLabel("inserisci nuova posizione:");
		lblInserisciNuovaPosizione.setBounds(49, 203, 241, 81);
		contentPane.add(lblInserisciNuovaPosizione);
		
		JButton btnNewButton = new JButton("conferma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//procedo ai controllo prima di aggiornare la posizione
				
				try {
					File f= new File("entrate.txt");
					BufferedReader buf = new BufferedReader(new FileReader(f));
					
					//dopo apertura del buffer..
					String s = new String();
					
					s=buf.readLine();
					
					StringBuffer oggetto = new StringBuffer();
					
					int i=0;
					
					if(new Integer(new String(txtNewPos.getText())).intValue()<0 ) {
						JOptionPane.showMessageDialog(null, "posizione negativa");
						posizioneValida=false;
					}
					
					while(s!=null&&!oggettoTrovato) {
						
						if(txtCodiceArticolo.getText().equals(magazzino.getArticolo(i).getCodice() ) ) {
							oggettoTrovato=true;
							oggetto=new StringBuffer(s);
						}
						s=buf.readLine();
						i++;
					}//fine while(s!=null)
										
					if(oggettoTrovato&&posizioneValida) {//aggiorno il file entrate.txt
						
						File tmp = new File("entrateTmp.txt");
						
						FileWriter w = new FileWriter(tmp,true);// append=true
			    		BufferedWriter bwriterTmp = new BufferedWriter(w);
			    		
			    		String pezzi []  = new String(oggetto).split(":");
			    		
			    		StringBuffer articoloAggiornato  = new StringBuffer();
			    		
			    		for (int j=0; j<7; j++) {//8 è il numero dei campi del file entrate
			    			if(j==2){
			    				articoloAggiornato.append(txtNewPos.getText()+":");
			    			}
			    			else {
			    				if(j==6) {
				    				articoloAggiornato.append(pezzi[j]);
			    				}
			    				else {
			    					articoloAggiornato.append(pezzi[j]+":");
			    				}
			    			}
			    		}
			    		System.out.println("nuovo articolo:"+articoloAggiornato);
						
			    		BufferedReader buf2 = new BufferedReader(new FileReader("entrate.txt"));
			    		
						String riga=new String();
						
						riga=buf2.readLine();
						
						
						while(riga!=null) {
			    			if(riga.compareTo(new String(new String(oggetto)))==0) {
			    				bwriterTmp.write(new String(articoloAggiornato)+"\n",0, articoloAggiornato.length()+1); 				
			    			}
			    			else {
			    				bwriterTmp.write(riga+"\n",0,riga.length()+1);
			    			}	
			    			riga=buf2.readLine();
			    		}//ho file entrateTmp.txt aggiornato!!
						
						
						buf2.close();
						bwriterTmp.close();
						
			    		//leggo il file temporaneo e aggiorno il file entrate
						BufferedReader bufUpdate= new BufferedReader(new FileReader("entrateTmp.txt"));
						
						FileWriter newW = new FileWriter("entrate.txt",false);// sovrascrivo il file
			    		BufferedWriter bwriterW = new BufferedWriter(newW);
						bwriterW.write("");
						bwriterW.close();//svuoto il file entrate.txt
						
						FileWriter newW2 = new FileWriter("entrate.txt",true);// append=true
			    		BufferedWriter bwriterW2 = new BufferedWriter(newW2);
			    		
						riga=bufUpdate.readLine();
						
						
						while(riga!=null) {
							bwriterW2.write(riga);
							riga=bufUpdate.readLine();
							if(riga!=null) {
								bwriterW2.write("\n");
							}
						}
			    		
						JOptionPane.showMessageDialog(null, "tipo articolo spostato correttamente in entrate");
			    		bwriterW2.close();
						bufUpdate.close();
						
						//elimino file temporaneo
						tmp.delete();		
			    		dispose();//chiude la finestra
					}
					else {
						JOptionPane.showMessageDialog(null, "ERRORE: articolo non spostato");
						dispose();
					}
					buf.close();
				}//fine try
				catch(Exception error) {
					error.printStackTrace();
					dispose();
				}
				
				
			}
		});
		btnNewButton.setBounds(249, 331, 187, 68);
		contentPane.add(btnNewButton);
		
		txtCodiceArticolo = new JTextField();
		txtCodiceArticolo.setBounds(305, 96, 289, 41);
		contentPane.add(txtCodiceArticolo);
		txtCodiceArticolo.setColumns(10);
		
		txtNewPos = new JTextField();
		txtNewPos.setColumns(10);
		txtNewPos.setBounds(305, 223, 289, 41);
		contentPane.add(txtNewPos);
	}
}
