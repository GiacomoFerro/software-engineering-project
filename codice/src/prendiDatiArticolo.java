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
import java.awt.event.ActionEvent;

import java.util.*;

public class prendiDatiArticolo extends JFrame {

	private JPanel contentPane;
	private JTextField txtMettiIDati;
	private JTextField txtCodice;
	private JTextField txtTipo;
	private JTextField txtPrezzo;
	private JTextField txtDataProduzione;
	
	boolean check=true;
	static int numeroInserimenti = 0;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prendiDatiArticolo frame = new prendiDatiArticolo();
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
	
	
	
	public prendiDatiArticolo() {
		setTitle("dati articolo in entrata");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMettiIDati = new JTextField();
		txtMettiIDati.setEditable(false);
		txtMettiIDati.setText("metti i dati:");
		txtMettiIDati.setBounds(125, 11, 164, 20);
		contentPane.add(txtMettiIDati);
		txtMettiIDati.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("codice");
		lblNewLabel.setBounds(10, 51, 102, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipoArticolo = new JLabel("tipo articolo");
		lblTipoArticolo.setBounds(10, 100, 102, 25);
		contentPane.add(lblTipoArticolo);
		
		JLabel lblPrezzo = new JLabel("prezzo");
		lblPrezzo.setBounds(10, 149, 102, 25);
		contentPane.add(lblPrezzo);
		
		JLabel lblDataProduzione = new JLabel("data produzione");
		lblDataProduzione.setBounds(10, 197, 102, 25);
		contentPane.add(lblDataProduzione);
		
		txtCodice = new JTextField();
		txtCodice.setBounds(141, 53, 204, 23);
		contentPane.add(txtCodice);
		txtCodice.setColumns(10);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(141, 102, 204, 23);
		contentPane.add(txtTipo);
		
		txtPrezzo = new JTextField();
		txtPrezzo.setColumns(10);
		txtPrezzo.setBounds(141, 151, 204, 23);
		contentPane.add(txtPrezzo);
		
		txtDataProduzione = new JTextField();
		txtDataProduzione.setColumns(10);
		txtDataProduzione.setBounds(141, 199, 204, 23);
		contentPane.add(txtDataProduzione);
		
		JButton btnNewButton = new JButton("conferma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//quando clicca conferma...controllo che i dati dell'articolo siano sensati...
				//uso oggetto magazzino.
				
				//ricerco prima il tipo dell'articolo
				tipoArticolo tipo = catalogoTipi.ricercaTipo(txtTipo.getText());
				if(tipo == null) {
					//tipoarticolo non è in catalogo
					JOptionPane.showMessageDialog(null, "ERRORE: tipo articolo non presente non inserito");
					check=false;
				}
				//controllo codice univoco
				if(magazzino.controllaCodice(txtCodice.getText())) {//true se il codice articolo è già presente
					check=false;
				}
				
				//controllo se data valida e prezzo non negativo
				if(Double.valueOf(txtPrezzo.getText())<=0) {
					check=false;
				}
				String data=txtDataProduzione.getText();
				String [] pezziData = data.split("/");
				
				if(!isDateValid(new Integer(pezziData[0]).intValue(), new Integer(pezziData[1]).intValue(), new Integer(pezziData[2]).intValue())) {
					JOptionPane.showMessageDialog(null, "ERRORE: data non valida");
					check=false;
				}
				if(check) {//se passa controlli
					articolo nuovo = new articolo(txtCodice.getText(),tipo, Double.valueOf(txtPrezzo.getText()), data);
					magazzino.articoli.add(nuovo);//aggiungo articolo
					JOptionPane.showMessageDialog(null, "dati corretti");
					numeroInserimenti++;

				}
				if(!check) {
					JOptionPane.showMessageDialog(null, "dati NON corretti");
				}
				dispose();
			}
		});
		btnNewButton.setBounds(141, 269, 204, 42);
		contentPane.add(btnNewButton);
	}

}
