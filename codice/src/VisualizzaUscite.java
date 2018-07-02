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

public class VisualizzaUscite extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaUscite frame = new VisualizzaUscite();
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
	public VisualizzaUscite() {

		JFrame frm = new JFrame();
		frm.setTitle("Uscite:");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int cont=0;
		
		try {
			
			File f= new File("uscite.txt");
			BufferedReader buf = new BufferedReader(new FileReader(f));
			//leggo gli articoli usciti e conto quanti sono
		
			String s=buf.readLine();
		
			while(s!=null) {
				cont++;
				s=buf.readLine();
			}

			buf.close();
		
		}
		catch(Exception e) {
			e.getMessage();
		}
		
		Object rows[][] = new Object[cont][4];
		
		try {
			BufferedReader buf2 = new BufferedReader(new FileReader("uscite.txt"));
			//leggo gli articoli usciti e metto le informazioni nella tabella
		
			for(int i = 0; i<cont; i++) { 
				String s=buf2.readLine();
				String[] token = s.split(":");
				rows[i][0] = token[0];
				rows[i][1] = token[2];
				rows[i][2] = token[1];
				rows[i][3] = token[3];
			}
			buf2.close();
		}
		catch(Exception e) {
			e.getMessage();
		}
			
		Object headers[] = {"Codice uscita", "tipo Articolo","data uscita","quantità uscita"};
		frm.getContentPane().setLayout(null);
		
		JTable table = new JTable(rows,headers);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 13, 408, 227);
		frm.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("chiudi uscite");
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
