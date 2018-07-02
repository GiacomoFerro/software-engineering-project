import java.util.*;

public class tipoArticolo {

	public String nomeUnivoco;
	public String descrizione=null;
	public String sport=null;
	
	public String mat1=null;
	public String mat2=null;
	public String mat3=null;
	
	public tipoArticolo(String nome) {
		
		this.nomeUnivoco=nome;
		
	}
	
	
	public tipoArticolo(String nome , String descr, String sport, String mat1, String mat2, String mat3) {
		this.nomeUnivoco=nome;
		this.descrizione=descr;
		this.sport=sport;
		this.mat1=mat1;
		this.mat2=mat2;
		this.mat3=mat3;
		
	}
	
	public String getNome() {
		return nomeUnivoco;
	}
	
	public String getAllinfo() {
		return this.getNome()+":"+this.descrizione+":"+this.sport+":"+this.mat1+":"+this.mat2+":"+this.mat3;
	}
	
	
	
}
