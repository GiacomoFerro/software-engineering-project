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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AcquisisciTipiArticoli extends JFrame {

	private JPanel contentPane;
	private JTextField txtInserisciDatiTipi;
	private JTextField txtNomeTipoArticolo;
	private JTextField txtQuantit‡TipoArticolo;

	private boolean tipoValido=true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcquisisciTipiArticoli frame = new AcquisisciTipiArticoli();
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
	public AcquisisciTipiArticoli() {
		setTitle("ordine tipo articolo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInserisciDatiTipi = new JTextField();
		txtInserisciDatiTipi.setEditable(false);
		txtInserisciDatiTipi.setText("inserisci dati tipi articolo:");
		txtInserisciDatiTipi.setBounds(86, 11, 234, 37);
		contentPane.add(txtInserisciDatiTipi);
		txtInserisciDatiTipi.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("nome tipo articolo:");
		lblNewLabel.setBounds(10, 82, 115, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantit = new JLabel("quantit\u00E0:");
		lblQuantit.setBounds(10, 130, 115, 37);
		contentPane.add(lblQuantit);
		
		JButton btnNewButton = new JButton("conferma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controllo su inserimento tipi articoli in ordine 
				
				//controllo che il tipo articolo sia presente e che la quantit‡ non sia <0
				
				if(catalogoTipi.ricercaTipo(txtNomeTipoArticolo.getText()) == null) {
					JOptionPane.showMessageDialog(null, "ERRORE: tipo articolo non presente.");
					tipoValido=false;
				}
				if(new Integer(txtQuantit‡TipoArticolo.getText())<=0) {
					JOptionPane.showMessageDialog(null, "ERRORE: quantit‡ negativa o pari a zero.");
					tipoValido=false;
				}
				
				if(tipoValido) {
					JOptionPane.showMessageDialog(null, "tipo articolo presente e quantit‡ valida.");
					try {
						//scrivo su file articoli tmp per poi andare da PrendiOrdine a comporre l'ordine finale
						FileWriter w = new FileWriter("articoli_tmp.txt", true);
						//true append quindi non sovrascrivere!!
		    			BufferedWriter bwriter = new BufferedWriter(w);
		    			
		    			int q=new Integer(txtQuantit‡TipoArticolo.getText()).intValue() ;
		    			
		    			//controllo  che il tipo articolo ci sia in magazzino e sia in qt‡ suff
		    			boolean presenza=true;
		    			presenza=magazzino.controllaOrdineTipoArticolo(txtNomeTipoArticolo.getText(),q);

		    			if(presenza) {
							JOptionPane.showMessageDialog(null, "magazzino contiene quelle quantit‡");
		    			}
		    			else {
							JOptionPane.showMessageDialog(null, "magazzino non contiene quelle quantit‡");

		    			}
		    			
		    			if(presenza) {
		    				//lo scrivo nel file temporaneo
		    				StringBuffer nuovoOrdine = new StringBuffer();
		    				nuovoOrdine.append(txtNomeTipoArticolo.getText()+":");
		    				nuovoOrdine.append(q);
		    				
		    				bwriter.write(new String(nuovoOrdine),0, nuovoOrdine.length());
		    				//bwriter.write("\n");
		    				bwriter.close();
							JOptionPane.showMessageDialog(null, "articolo salvato");

		    			}
		    			
					}//fine try
		    		catch(Exception e1) {
		    			e1.printStackTrace();
		    		}
				
				}//fine if tipi valido
				dispose();
				
			}//fine funzione
		});
		btnNewButton.setBounds(163, 196, 204, 56);
		contentPane.add(btnNewButton);
		
		txtNomeTipoArticolo = new JTextField();
		txtNomeTipoArticolo.setBounds(163, 82, 204, 37);
		contentPane.add(txtNomeTipoArticolo);
		txtNomeTipoArticolo.setColumns(10);
		
		txtQuantit‡TipoArticolo = new JTextField();
		txtQuantit‡TipoArticolo.setColumns(10);
		txtQuantit‡TipoArticolo.setBounds(163, 130, 204, 37);
		contentPane.add(txtQuantit‡TipoArticolo);
	}

}
