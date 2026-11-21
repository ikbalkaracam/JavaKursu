package proje2;

public abstract class Zemin {
	public abstract boolean gecilebilir(Govde g);
	public abstract int maliyet(Govde g);
	public abstract char sembol();
	
	public boolean kaydirir(Govde g) {
		return false;
	}
	public boolean savurur(Govde g) {
		return false;
	}
	public boolean atla(Govde g) {
		return false;
	}
	public boolean inisyapabilir(Govde g) {
		return gecilebilir(g);
	}
	public boolean teslim() {
		return false;
	}
	

}
