
public class articolo {

	protected String codiceArticoloUnivoco;
	protected tipoArticolo tipo;
	protected double prezzo;
	protected String dataProduzione;
	
	public articolo(String codice,tipoArticolo type) {
		this.codiceArticoloUnivoco=codice;
		this.tipo=type;
		prezzo=0.0;
		dataProduzione="31/01/18";
	}
	
	public articolo(String codice, tipoArticolo type, double prezzo, String data) {
		this.codiceArticoloUnivoco=codice;
		this.tipo=type;
		this.prezzo=prezzo;
		this.dataProduzione=data;
	}
	
	public String getCodice() {
		return codiceArticoloUnivoco;
	}
	
	public String getTipo() {
		return tipo.nomeUnivoco;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public String getData() {
		return dataProduzione;
	}
	
	public String toString() {//ritorno articolo in formato csv
		return this.getCodice()+":"+this.getTipo()+":"+this.getPrezzo()+":"+this.getData();
		
	}
	
	
	
	
}
