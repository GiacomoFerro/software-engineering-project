import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class catalogoTipi {

	public static ArrayList<tipoArticolo> TipiArticoli=new ArrayList();//lista di tipi articoli presenti

	public catalogoTipi() {	
		try {
			File f= new File("tipiArticolo.txt");
			BufferedReader buf = new BufferedReader(new FileReader(f));
			//leggo i tipi articolo disponibili e li salvo dinamicamente nell'array list
			
			String s=buf.readLine();
			
			while(s!=null) {
				String[] token = s.split(":");
				tipoArticolo nuovo =new tipoArticolo(token[0],token[1],token[2],token[3],token[4],token[5]);
				TipiArticoli.add(nuovo);//lo aggiungo
				s=buf.readLine();
			}
		
			buf.close();
		}
		catch(Exception errore) {
			errore.printStackTrace();
		}
	}//fine costruttore
	
	public static tipoArticolo ricercaTipo(String nomeTipo) {
		Iterator<tipoArticolo> iteratore = TipiArticoli.iterator();
		tipoArticolo elem;
		
		while(iteratore.hasNext()) {
			elem=iteratore.next();
			if(elem.getNome().compareTo(nomeTipo)==0) {
				return elem;
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
}//fine classe
