import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class VisualizzaOrdini extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaOrdini frame = new VisualizzaOrdini("nomeNegozio");
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
	public VisualizzaOrdini(String negozio) {
		
		JFrame frm = new JFrame();
		frm.setTitle("negozio:");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int cont=0;				
		boolean check=true;
		
		//conto le righe del file
		try {
			File f= new File("ordini.txt");
			BufferedReader buf = new BufferedReader(new FileReader(f));
			String line=buf.readLine();
			
			while(line!=null) {				
				cont++;
				line=buf.readLine();
			}
			
			buf.close();
		}
		catch(Exception er) {
			er.getMessage();
		}
		
		Object rows[][] = new Object[cont][3];

		//leggo gli ordini del negozio a true
		try {
			
			File f= new File("ordini.txt");
			BufferedReader buf = new BufferedReader(new FileReader(f));
			
			String s=buf.readLine();
								
			int i = 0;
			
			while(s!=null) {
				String[] token = s.split(":");				
				if(token[1].equals(negozio)&&token[4].equals("true")) {//se è un ordine del negozio
					check=false;
					rows[i][0] = token[0];//codice ordine
					rows[i][1] = token[2];//tipo articolo
					rows[i][2] = token[3];//qtà
					i++;
				}
				s=buf.readLine();
			}
			
			buf.close();
			
			if(check){
				JOptionPane.showMessageDialog(null, "non sono presenti ordini per il negozio");
			}
		}//fine try
		catch(Exception errore) {
			errore.printStackTrace();
		}
		
		Object headers[] = {"Codice ordine", "tipo Articolo","quantità uscita"};
		frm.getContentPane().setLayout(null);
		
		JTable table = new JTable(rows,headers);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 13, 408, 227);
		frm.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("chiudi");
		btnNewButton.setBounds(150, 253, 146, 48);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frm.dispose();
			}
		});
		frm.getContentPane().add(btnNewButton);
		
		
		frm.setSize(608,  473);
		frm.setBounds(100, 100,450,382);
		frm.setVisible(true);
		
	}

}
