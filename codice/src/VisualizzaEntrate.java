import java.awt.BorderLayout;
import javax.swing.table.*;
import java.awt.EventQueue;
import java.io.*;
import java.util.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizzaEntrate extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public static boolean lock = true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaEntrate frame = new VisualizzaEntrate();
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
	public VisualizzaEntrate() {

		JFrame frm = new JFrame();
		frm.setTitle("Entrate:");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		//magazzino magazzino = new magazzino();							
			
		Object rows[][] = new Object[magazzino.getSize()][4];
			
		for(int i = 0; i<magazzino.getSize(); i++) {
			articolo elem = magazzino.getArticolo(i);
			rows[i][0] = elem.getCodice();
			rows[i][1] = elem.getTipo();
			rows[i][2] = elem.getPrezzo();
			rows[i][3] = elem.getData(); 
			
		}
			
		Object headers[] = {"Codice articolo", "tipo Articolo", "Prezzo","data produzione"};
		frm.getContentPane().setLayout(null);
		
		JTable table = new JTable(rows,headers);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 13, 408, 227);
		frm.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("chiudi entrate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frm.dispose();
			}
		});
		btnNewButton.setBounds(252, 256, 146, 48);
		frm.getContentPane().add(btnNewButton);
		
		JButton btnVisualizzaUscite = new JButton("visualizza uscite");
		btnVisualizzaUscite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//quando chiude entrate visualizzo le uscite
				VisualizzaUscite visual2 = new VisualizzaUscite();
				visual2.setVisible(true);
			}
		});
		btnVisualizzaUscite.setBounds(22, 256, 146, 48);
		frm.getContentPane().add(btnVisualizzaUscite);
		
		
		frm.setSize(450,  382);
		frm.setBounds(100, 100,450,382);
		frm.setVisible(true);
		}
}
