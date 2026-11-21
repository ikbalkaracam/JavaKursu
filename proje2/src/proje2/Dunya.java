package proje2;

public class Dunya {
	public static final int MIN_NO=1;
	public static final int MAX_NO=9;
	private final Harita harita;
	private final Robot[] robotlar;
	public Dunya(Harita harita) {
		this.harita=harita;
		this.robotlar=new Robot[MAX_NO+1];//dizi 0dan basladıgı icin +1 ekledik
	}
	public Harita getHarita() {
		return harita;
	}
	public void robotEkle(Robot r) {
		numaraKontrolEt(r.getNumara());
		robotlar[r.getNumara()]=r;
	}
 public Robot robotGetir(int numara) {
		numaraKontrolEt(numara);
	return robotlar[numara];
}
 public boolean robotVarmi(Konum k) {
	for(int no=1;no<=MAX_NO;no++) {
		Robot r=robotlar[no];
		if(r!=null && r.getDurum()!=Durum.TESLIM && r.getKonum().ayniMi(k)) {//equals() ayni hucreler mi diye soruyor.
		return true;//robot var.
		
	}
}
	return false;
}
 public Robot[] robotDizisi() {
	 int sayi=0;
	 for(Robot r:robotlar) {
		 if(r!=null) {
			 sayi++;
		 }
	 }
	 Robot[] sonuc=new Robot[sayi];
	 int sira=0;
	 for(Robot r:robotlar) {
		 if(r!=null) {
			 sonuc[sira]=r;
			 sira++;
		 }
	 }
	 return sonuc;
 }
 private void numaraKontrolEt(int numara) {
	    if (numara < MIN_NO || numara > MAX_NO) {
	        throw new IllegalArgumentException(
	            "Robot numarasi " + MIN_NO + "-" + MAX_NO + " arasinda olmali: " + numara);
	    }
	}
 public Konum hedefHucre(Robot r) {
	 return r.getKonum().ilerle(r.getYon());
 }
 public Zemin hedefZemin(Robot r) {
	 Konum hedef=hedefHucre(r);
	 return harita.zemin(hedefHucre(r));
 }
 
}