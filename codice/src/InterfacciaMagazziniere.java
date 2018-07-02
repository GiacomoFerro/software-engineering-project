import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class InterfacciaMagazziniere extends JFrame {

	private JPanel contentPane;
	private JTextField txtSeiLoggatoCome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaMagazziniere frame = new InterfacciaMagazziniere("nomeACaso");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//per il controllo della data

	
	
	/**
	 * Create the frame.
	 */
	public InterfacciaMagazziniere(String nomeUtente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSeiLoggatoCome = new JTextField();
		txtSeiLoggatoCome.setEditable(false);
		txtSeiLoggatoCome.setText("sei loggato come: "+nomeUtente);
		txtSeiLoggatoCome.setBounds(91, 11, 247, 27);
		contentPane.add(txtSeiLoggatoCome);
		txtSeiLoggatoCome.setColumns(10);
		
		JButton btnNewButton = new JButton("registra entrate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//aggiungo un articolo al magazzino
				//occorre assicurarsi che il codice interno di entrata sia univoco
				
				InterfacciaInserimentoArticoli articoli = new InterfacciaInserimentoArticoli();
				articoli.setVisible(true);//mostro il frame
				
				
			}
		});
		btnNewButton.setBounds(101, 63, 229, 56);
		contentPane.add(btnNewButton);
		
		JButton btnRegistraUscite = new JButton("registra uscite");
		btnRegistraUscite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//aggiungo una uscita che deve corrispondere ad un ordine da parte del responsabile
				//del negozio
				
				InterfacciaUscitaArticoli uscita = new InterfacciaUscitaArticoli();
				uscita.setVisible(true);//mostro frame
				
			}
		});
		btnRegistraUscite.setBounds(101, 145, 229, 56);
		contentPane.add(btnRegistraUscite);
		
		JButton btnSpostaPosizioneArticoli = new JButton("sposta posizione articoli");
		btnSpostaPosizioneArticoli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aggiorno posizione articolo nel magazzino devo controllare che non
				//sia occupata
				
				//N.B. file entrate.txt coincide con gli articoli che sono presenti in magazzino
				aggiornaPosizione inserisciPosizioneArt = new aggiornaPosizione();
				inserisciPosizioneArt.setVisible(true);//mostro il frame
				
				
				
			}
		});
		btnSpostaPosizioneArticoli.setBounds(101, 229, 229, 56);
		contentPane.add(btnSpostaPosizioneArticoli);
	}

}
