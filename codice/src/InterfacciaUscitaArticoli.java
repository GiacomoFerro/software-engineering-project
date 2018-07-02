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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class InterfacciaUscitaArticoli extends JFrame {

	private JPanel contentPane;
	private JTextField txtInserisciDatiUscita;
	private JTextField txtCodiceUscita;
	private JButton btnConferma;
	private JTextField txtDataUscita;
	
	private boolean dataValida = true;
	private boolean codValido= true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaUscitaArticoli frame = new InterfacciaUscitaArticoli();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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
	public InterfacciaUscitaArticoli() {
		setTitle("dati uscita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInserisciDatiUscita = new JTextField();
		txtInserisciDatiUscita.setText("inserisci dati uscita:");
		txtInserisciDatiUscita.setEditable(false);
		txtInserisciDatiUscita.setBounds(95, 11, 223, 26);
		contentPane.add(txtInserisciDatiUscita);
		txtInserisciDatiUscita.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("inserisci codice uscita:");
		lblNewLabel.setBounds(32, 66, 147, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("inserisci data uscita:");
		lblNewLabel_1.setBounds(32, 132, 147, 31);
		contentPane.add(lblNewLabel_1);
		
		txtCodiceUscita = new JTextField();
		txtCodiceUscita.setBounds(189, 68, 202, 26);
		contentPane.add(txtCodiceUscita);
		txtCodiceUscita.setColumns(10);
		
		btnConferma = new JButton("conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//leggo file uscita per controllare 
				
				String data=txtDataUscita.getText();
				String [] pezziData = data.split("/");
				
				if(!isDateValid(new Integer(pezziData[0]).intValue(), new Integer(pezziData[1]).intValue(), new Integer(pezziData[2]).intValue())) {
					JOptionPane.showMessageDialog(null, "ERRORE: data non valida");
					dataValida=false;
				}
				try {
					File f= new File("uscite.txt");
					BufferedReader buff = new BufferedReader(new FileReader(f));
					String s = new String();
					
					s=buff.readLine();
					while(s!=null) {
						String [] token = s.split(":");
						if(token[0].equals(txtCodiceUscita.getText())) {
							codValido=false;
						}
						s=buff.readLine();
					}//fine while
					
					buff.close();
				}//fine try
				catch(Exception er) {
					er.getMessage();
				}
				//controllo i dati
				if(codValido&&dataValida) { JOptionPane.showMessageDialog(null, "dati corretti");}
				else { JOptionPane.showMessageDialog(null, "dati NON corretti. Ritentare.");dispose();}
				
				//procedo a processare gli ordini in ORDINE CRONOLOGICO
				boolean continuaOrdini=true;
				//devo controllare il magazzino e poi leggere ordini.txt e aggiornarlo
				
				try {
					//FileWriter w = new FileWriter("ordini.txt", true);
					//true append quindi non sovrascrivere!!
					//BufferedWriter bwriter = new BufferedWriter(w);
					File f= new File("ordini.txt");
					BufferedReader bufOrd = new BufferedReader(new FileReader(f));
					
					while(codValido&&continuaOrdini) {
						//leggo la prima riga di ordini, controllo disponibilità e se ok 
						//processo l'ordine.
						String leggo=new String();
						leggo=bufOrd.readLine();
						
						if(leggo!=null) {
							String [] ordine = leggo.split(":");
							
							//controllo disponibilità e flag. Processo solo se è false il flag
							if(ordine[4].equals("false")) {//flag
								int qtà = new Integer(ordine[3]).intValue();
								String tipoArticolo = new String(ordine[2]);
							
								//creo l'ordine di uscita
								StringBuffer nuovaUscita = new StringBuffer();
								nuovaUscita.append(txtCodiceUscita.getText()+":");
								nuovaUscita.append(txtDataUscita.getText()+":");
								nuovaUscita.append(tipoArticolo+":");//tipo
								nuovaUscita.append(ordine[3]+":");//qtà
								
								//se disponibile in magazzino
								if(continuaOrdini=magazzino.controllaOrdineTipoArticolo(tipoArticolo,qtà)) {
									
									//aggiorno le entrate.txt e poi anche il magazzino
									File tmp = new File("entrateTmp.txt");
								
									FileWriter w1 = new FileWriter(tmp,true);// append=true
									BufferedWriter bwriterTmp = new BufferedWriter(w1);
								
									BufferedReader buf2 = new BufferedReader(new FileReader("entrate.txt"));
					    		
									String riga=new String();
									riga=buf2.readLine();

									int cont=qtà;
								
									double prezzo = 0.0;
									
									while(riga!=null) {
										String [] magazz = riga.split(":");
										if(magazz[4].equals(tipoArticolo)&&cont!=0) {
											//becco un tipo articolo da vendere non lo salvo in magazzino
											cont--;
											
											//aggiorno contatore
											//salvo codice articolo e prezzo nella nuova uscita
											nuovaUscita.append(magazz[3]+":");//cod art
											prezzo=prezzo + Double.parseDouble(magazz[5]);//calcolo prezzo finale
										}
										else {
											
											bwriterTmp.write(riga+"\n",0,riga.length()+1);
										}
										riga=buf2.readLine();
									}//ho file entrateTmp.txt aggiornato!!
								
									nuovaUscita.append(Double.toString(prezzo));
									//System.out.println("fine=="+nuovaUscita);
									
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
										bwriterW2.write(riga,0, riga.length());
										riga=bufUpdate.readLine();
										if(riga!=null) {
											bwriterW2.write("\n");
										}
									}
					    		
									JOptionPane.showMessageDialog(null, "entrate aggiornate");
									bwriterW2.close();
									buf2.close();
									bufUpdate.close();
									tmp.delete();//elimino entrateTmp
									
									//devo aggiornare flag dell'ordine in ordini.txt
									//devo scrivere in uscite.txt
									//devo aggiornare magazzino
									ordine[4]="true";
									StringBuffer lineaProcessata = new StringBuffer();
									for(int i=0; i<=3; i++) {
										lineaProcessata.append(ordine[i]+":");
									}
									lineaProcessata.append(ordine[4]);//non metto : in fondo
									
									BufferedReader up = new BufferedReader(new FileReader("ordini.txt"));
									
									File tmp2 = new File("ordiniTmp.txt");
									
									FileWriter upw = new FileWriter(tmp2,true);// append=true
						    		BufferedWriter upWTmp = new BufferedWriter(upw);
						    		
									riga=up.readLine();

									String lineaOld=new String(ordine[0]+":"+ordine[1]+":"+ordine[2]+":"+ordine[3]+":"+"false");
									
									while(riga!=null) {
						    			if(riga.equals(lineaOld)) {
						    				upWTmp.write(new String(lineaProcessata),0,lineaProcessata.length());
						    				//upWTmp.newLine();
						    			}
						    			else {
						    				
						    				upWTmp.write(riga,0,riga.length());
						    				//upWTmp.newLine();

						    			}
						    			riga=up.readLine();
						    			if(riga!=null) upWTmp.newLine();
						    		}//ho file ordiniTmp.txt aggiornato!!
									
									up.close();
									upWTmp.close();
									
									BufferedReader up2= new BufferedReader(new FileReader("ordiniTmp.txt"));
									
									FileWriter aggiorna = new FileWriter("ordini.txt",false);// sovrascrivo il file
						    		BufferedWriter awriterW = new BufferedWriter(aggiorna);
									awriterW.write("");
									awriterW.close();//svuoto il file ordini.txt
									
									FileWriter aggiorna2 = new FileWriter("ordini.txt",true);// append=true
						    		BufferedWriter a2writerW2 = new BufferedWriter(aggiorna2);
						    		
									riga=up2.readLine();
									
									//System.out.println("riga tmp:"+riga);
									while(riga!=null) {
										a2writerW2.write(riga,0, riga.length());
							    		riga=up2.readLine();
							    		if(riga!=null) a2writerW2.newLine();
									}//ho aggiornato ordini.txt
									
									up2.close();
									a2writerW2.close();
									//elimino file temporaneo
									//tmp2.deleteOnExit();
									tmp2.delete();//ordiniTmp.txt
									
									
									JOptionPane.showMessageDialog(null, "ordini aggiornati");

								
									
									//ora scrivo in uscite l'ordine
									
									FileWriter uscita = new FileWriter("uscite.txt",true);
									BufferedWriter uscitaW = new BufferedWriter(uscita);
									
									uscitaW.write(new String("\n"+nuovaUscita),0, nuovaUscita.length()+1);
									uscitaW.close();
									
									//ora manca aggiornare il magazzino
									magazzino.aggiornaMagazzino();
									JOptionPane.showMessageDialog(null, "uscite aggiornate");
									
									continuaOrdini=false;//ho processo 1 SOLO ORDINE ED ESCO
									JOptionPane.showMessageDialog(null, "ORDINE PROCESSATO con successo.");

								}//fine di if controlloDisponibilità
								else {//se non c'è disponibilità in magazzino avviso ed esco
									JOptionPane.showMessageDialog(null, "magazzino non ha disponibilità ritentare prossimamente");
									dispose();
									continuaOrdini=false;
								}
							}
							else {//se è già stato processato lo salto
								;
							}
						}//fine leggo!=null
						else{
							JOptionPane.showMessageDialog(null, "tutti gli ordini sono stati evasi.");
							continuaOrdini=false;
							dispose();
						}
						
					}//fine while continuaOrdini 
					
					bufOrd.close();
					dispose();
				}//fine try
				catch(Exception e1) {
					e1.getMessage();
				}	
			}
		});
		btnConferma.setBounds(134, 190, 147, 57);
		contentPane.add(btnConferma);
		
		txtDataUscita = new JTextField();
		txtDataUscita.setColumns(10);
		txtDataUscita.setBounds(189, 134, 202, 26);
		contentPane.add(txtDataUscita);
		
	}
}
