package proje2;

public class Simulasyon {
private final Dunya dunya;
private final BirAdimIlerlet adim;
public Simulasyon(Dunya dunya) {
	this.dunya=dunya;
	this.adim=new BirAdimIlerlet(dunya);
}
public void calistir(Komut[] komutlar) {
	for(Komut komut:komutlar) {
		for(Robot r:dunya.robotDizisi()) {
			if(r.aktifMi()) {
				komut.uygula(r, adim);
				
			}
		}
	}
}
private String robotSatiri(Robot r) {
	return "ROBOT "+r.getNumara()+" "+r.getGovde().ad()
			+" KONUM "+r.getKonum()+" YON "+r.getYon().name()
			+" ENERJI "+r.getEnerji()+" DURUM "+r.getDurum().name();
}
public void sonuc() {
	int teslimSayisi=0;
	int kalanEnerji=0;
	for(Robot r:dunya.robotDizisi()) {
		System.out.println(robotSatiri(r));
		if(r.getDurum()==Durum.TESLIM) {
			teslimSayisi++;
		}
		kalanEnerji+=r.getEnerji();
	}
    System.out.println("TESLIM " + teslimSayisi);
    System.out.println("KALAN_ENERJI "+kalanEnerji);
}
}

