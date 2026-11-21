package proje2;

public class Robot {
private final int numara;//final olanlar proje boyunca degismez 
private final Govde govde;
private Konum konum;
private Yon yon;
private int enerji;
private Durum durum;

public Robot(int numara,Govde govde,Konum baslangic,int enerji) {
	this.numara=numara;
	this.govde=govde;
	this.konum=baslangic;
	this.yon=Yon.E;
	this.enerji=enerji;
	this.durum=Durum.AKTIF;
}
public int getNumara() {
	return numara;
}
public Govde getGovde() {
	return govde;
}
public Konum getKonum() {
	return konum;
}
public Yon getYon() {
	return yon;
}
public int getEnerji() {
	return enerji;
}
public Durum getDurum() {
	return durum;
}
public boolean aktifMi() {
	return durum==Durum.AKTIF;
}
public void sagadon() {
	yon=yon.sag();
}
public void soladon() {
	yon=yon.sol();
}
public boolean enerjiHarca(int maliyet) {
	if(maliyet>enerji) {
		durum=Durum.ENERJISIZ;
		return false;
	}
	else {
		enerji=enerji-maliyet;
		return true;
	}
}
public boolean hareket(Konum hedef,int maliyet) {

	if(!enerjiHarca(maliyet)) {
		return false;
	}
	konum=hedef;
	return true;
	}
	

public void teslimEt() {
	durum=Durum.TESLIM;
}
}
