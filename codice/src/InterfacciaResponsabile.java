import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfacciaResponsabile extends JFrame {

	private JPanel contentPane;
	private JTextField txtSeiLoggatoCome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaResponsabile frame = new InterfacciaResponsabile("nomeAcaso");
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
	public InterfacciaResponsabile(String nomeUtente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSeiLoggatoCome = new JTextField();
		txtSeiLoggatoCome.setText("sei loggato come: "+nomeUtente);
		txtSeiLoggatoCome.setEditable(false);
		txtSeiLoggatoCome.setBounds(86, 11, 238, 33);
		contentPane.add(txtSeiLoggatoCome);
		txtSeiLoggatoCome.setColumns(10);
		
		JButton btnNewButton = new JButton("effettua un ordine");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//effettua ordine
				//lancio interfaccia di acquisizione ordine
				PrendiOrdine ordine = new PrendiOrdine();
				ordine.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(86, 63, 238, 83);
		contentPane.add(btnNewButton);
		
		JButton btnVisualizzaMo = new JButton("visualizza ordini passati");
		btnVisualizzaMo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PrendiNomeNegozio negozio = new PrendiNomeNegozio();
				negozio.setVisible(true);
				
			}
		});
		btnVisualizzaMo.setBounds(86, 166, 238, 83);
		contentPane.add(btnVisualizzaMo);
	}
}
