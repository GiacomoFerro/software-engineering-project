import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public class PrendiNomeNegozio extends JFrame {

	private JPanel negozio;
	private JTextField txtNegozio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrendiNomeNegozio frame = new PrendiNomeNegozio();
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
	public PrendiNomeNegozio() {
		setTitle("negozio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		negozio = new JPanel();
		negozio.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(negozio);
		negozio.setLayout(null);
		
		txtNegozio = new JTextField();
		txtNegozio.setBounds(177, 50, 190, 38);
		negozio.add(txtNegozio);
		txtNegozio.setColumns(10);
		
		JLabel nomeNegozio = new JLabel("nome negozio");
		nomeNegozio.setBounds(15, 50, 106, 38);
		negozio.add(nomeNegozio);
		
		JButton btnNewButton = new JButton("conferma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//apro gli ordini del negozio
				VisualizzaOrdini visual = new VisualizzaOrdini(txtNegozio.getText());
				visual.setVisible(true);				
				
			}//fine action list
		});
		btnNewButton.setBounds(102, 130, 190, 66);
		negozio.add(btnNewButton);
	}
}
