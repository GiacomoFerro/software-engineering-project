import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
public class magazzino {
	
	/*singleton (opzione 2)
	 * 
	 * mettere costruttore magazzino private.. mettere array list non static
	 * 
	 * private static magazzino instance = new magazzino();
	 * 
	 * public static magazzino instance(){return instance;}
	 * 
	 * private ArrayList<articolo> articoli = new ArrayList();
	 * 
	 * 
	 * */

	public static ArrayList<articolo> articoli = new ArrayList();//lista articoli
	
	public magazzino() {
		try {
			File f= new File("entrate.txt");
			BufferedReader buf = new BufferedReader(new FileReader(f));
			//leggo gli articoli in magazzino e li salvo dinamicamente nell'array list
			
			String s=buf.readLine();
			
			while(s!=null) {
				String[] token = s.split(":");				
				articolo nuovo =new articolo(token[3],catalogoTipi.ricercaTipo(token[4]),Double.valueOf(token[5]),token[6]);
				articoli.add(nuovo);//lo aggiungo
				s=buf.readLine();
			}
			//this.getAllArticoli();
			buf.close();
			
		}
		catch(Exception errore) {
			errore.printStackTrace();
		}
		
	}
	
	public static int getSize() {
		
		return articoli.size();
		
	}
	
	public static articolo getArticolo(int posizione) {
		return articoli.get(posizione);//ritorno articolo in una certa posizione
		
	}
	
	public static String getArticolotoString(int posizione) {
		return articoli.get(posizione).toString();
	}
	
	public void getAllArticoli() {
		Iterator<articolo> iteratore = articoli.iterator();
		articolo elem;
		
		while(iteratore.hasNext()) {
			elem=iteratore.next();
			System.out.println(elem.toString());
		}
		return;
	}
	
	public static String getUltimoArticoloInserito() {
		articolo elem= articoli.get(articoli.size()-1);
		return elem.toString();//ritorno stringa
	}
	
	public static boolean controllaOrdineTipoArticolo(String tipoArticoloOrdine, int quantità) {
		Iterator<articolo> iteratore = articoli.iterator();
		articolo elem;
		
		int num=0;
		
		while(iteratore.hasNext()) {
			elem=iteratore.next();

			if(elem.tipo.getNome().equals(tipoArticoloOrdine)) {
				num++;
			}
		}
		
		if(num>=quantità) {//se la quantità è suff
			return true;
		}
		
		return false;
	}
	
	public static boolean controllaCodice(String codiceArticolo) {
		Iterator<articolo> iteratore = articoli.iterator();
		articolo elem;
		
		while(iteratore.hasNext()) {
			elem=iteratore.next();

			if(elem.getCodice().equals(codiceArticolo)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public static void aggiornaMagazzino() {
		//è come il costruttore e si aggiorna
		try {
			File f= new File("entrate.txt");
			BufferedReader buf = new BufferedReader(new FileReader(f));
			//leggo gli articoli in magazzino e li salvo dinamicamente nell'array list
			
			String s=buf.readLine();
			
			while(s!=null) {
				String[] token = s.split(":");				
				articolo nuovo =new articolo(token[3],catalogoTipi.ricercaTipo(token[4]),Double.valueOf(token[5]),token[6]);
				articoli.add(nuovo);//lo aggiungo
				s=buf.readLine();
			}
			//this.getAllArticoli();
			buf.close();
			
		}
		catch(Exception errore) {
			errore.printStackTrace();
		}
	}
	
}//fine classe
