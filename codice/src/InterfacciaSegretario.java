import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class InterfacciaSegretario extends JFrame {

	private JPanel contentPane;
	private JTextField txtSeiLoggatoCome;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaSegretario frame = new InterfacciaSegretario("nomeACaso");
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
	
	
	public InterfacciaSegretario(String nomeUtente) {
		setTitle("Azioni Segretario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("inserisci tipo articolo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//devo inserire un nuovo articolo...
				InterfacciaInserimentoTipoArticolo inserisci = new InterfacciaInserimentoTipoArticolo();
				inserisci.setVisible(true);
				
				
				
			}
		});
		btnNewButton.setBounds(92, 48, 234, 82);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("visualizza movimenti \r\nmagazzino");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//visual entrate e poi in entrate metto la visual di uscite		
				VisualizzaEntrate visual = new VisualizzaEntrate();
				visual.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(92, 143, 234, 82);
		contentPane.add(btnNewButton_1);
		
		txtSeiLoggatoCome = new JTextField();
		txtSeiLoggatoCome.setEditable(false);
		txtSeiLoggatoCome.setText("sei loggato come: "+nomeUtente);
		txtSeiLoggatoCome.setBounds(92, 17, 234, 20);
		contentPane.add(txtSeiLoggatoCome);
		txtSeiLoggatoCome.setColumns(10);
	}
}
