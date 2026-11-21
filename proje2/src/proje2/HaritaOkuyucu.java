package proje2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class HaritaOkuyucu {
	private final String dosyaYolu;
	private final TurKayitlari turKayitlari = new TurKayitlari();
	
	private int genislik;
	private int yukseklik;
	private int enerji;
	
	private boolean enerjiOkundu=false;
	private boolean boyutOkundu=false;
	
	private final Govde[] robotGovdeleri=new Govde[Dunya.MAX_NO+1];
	private final int[] robotSatirlari=new int[Dunya.MAX_NO+1];
	
	private Harita harita;
	private Dunya dunya;
	private int okunanSatir=0;
	public HaritaOkuyucu(String dosyaYolu) {
		this.dosyaYolu=dosyaYolu;
	}
	public Dunya oku() throws IOException, DosyaHatalari {
	    int satirNo = 0;
	    boolean haritaBasladi = false;
	    try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaYolu))) {
	        String satir = okuyucu.readLine();
	        while (satir != null) {
	            satirNo++;
	            String metin = temizle(satir);
	            if (!metin.isEmpty()) {
	                if (haritaBasladi) {
	                    haritaSatiriIsle(metin, satirNo);
	                } else if (metin.equals("HARITA")) {
	                    baslikKontrolu(satirNo);
	                    harita = new Harita(genislik, yukseklik);
	                    dunya = new Dunya(harita);
	                    haritaBasladi = true;
	                } else {
	                    baslikSatiriIsle(metin, satirNo);
	                }
	            }
	            satir = okuyucu.readLine();
	        }
	    }
	    if (!haritaBasladi) {
	        throw new DosyaHatalari(dosyaYolu, satirNo, "HARITA satiri bulunamadi");
	    }
	    sonKontroller(satirNo);
	    return dunya;
	}
	private void haritaSatiriIsle(String metin,int satirNo) throws DosyaHatalari{
		if(okunanSatir>=yukseklik) {
			throw new DosyaHatalari(dosyaYolu,satirNo,"harita "+yukseklik+" satir olmali,fazla satir var.");
		}
		if(metin.length()!=genislik) {
			throw new DosyaHatalari(dosyaYolu,satirNo,"harita satiri "+ genislik+" karakter olmali, " +metin.length() + " karakter var");
		}
		for(int x=0;x<genislik;x++) {
		     hucreIsle(metin.charAt(x), new Konum(x, okunanSatir), satirNo);
		}
		okunanSatir++;
	}
	private void hucreIsle(char karakter,Konum konum,int satirNo) throws DosyaHatalari {
		int no=karakter-'0';
		if(no>= Dunya.MIN_NO && no <= Dunya.MAX_NO) {
			robotYerlestir(no,konum,satirNo);
			return;
		}
		Zemin zemin=zeminBul(karakter);
		if(zemin==null) {
			 throw new DosyaHatalari(dosyaYolu, satirNo, "gecersiz karakter '" + karakter+ "' (sutun " + (konum.getX() + 1) + ")");
		}
		harita.yerlestir(konum, zemin);
	}
	private void robotYerlestir(int no,Konum konum,int satirNo) throws DosyaHatalari{
		if(robotGovdeleri[no]==null) {
			throw new DosyaHatalari(dosyaYolu,satirNo,"haritada "+ no + " var ama ROBOT " + no + " tanimlanmamis");
		}
		if(dunya.robotGetir(no)!=null) {
			throw new DosyaHatalari(dosyaYolu,satirNo,"robot "+ no + " haritada iki kez var");
		}
		dunya.robotEkle(new Robot(no,robotGovdeleri[no],konum,enerji));
	}
	private Zemin zeminBul(char sembol) {
		return turKayitlari.zeminBul(sembol);
	}
	private void sonKontroller(int sonSatirNo) throws DosyaHatalari{
		if(okunanSatir!=yukseklik) {
			throw new DosyaHatalari(dosyaYolu,sonSatirNo,"harita "+yukseklik+" satir olmali,"+okunanSatir+"satir var.");	
		}
		for(int no=Dunya.MIN_NO;no<=Dunya.MAX_NO;no++) {
			if(robotGovdeleri[no]!=null && dunya.robotGetir(no)==null) {
				throw new DosyaHatalari(dosyaYolu, robotSatirlari[no],"ROBOT " + no + " tanimlanmis ama haritada yok");
			}
		}
	}
	private String temizle(String satir) {
		String metin=satir.trim();
		if(metin.startsWith("#")) {
			return "";
		}
		return metin;
	}
	public void baslikKontrolu(int satirNo) throws DosyaHatalari{
		if(!boyutOkundu) {
			throw new DosyaHatalari(dosyaYolu,satirNo,"HARITA satirindan once boyut tanımlamali!");
		}
		if(!enerjiOkundu) {
			throw new  DosyaHatalari(dosyaYolu,satirNo,"HARITA satirindan once enerji tanimlanmali!");
		}
	}
private void baslikSatiriIsle(String metin,int satirNo) throws DosyaHatalari{
	String[] parcalar=metin.split("\\s+");
	String anahtar=parcalar[0];
	if(anahtar.equals("BOYUT")) {
	boyutIsle(parcalar,satirNo);
	}
	else if(anahtar.equals("ENERJI")) {
		enerjiIsle(parcalar,satirNo);
	}
	else if(anahtar.equals("ROBOT")) {
		robotIsle(parcalar,satirNo);
	}
	else {
		throw new DosyaHatalari(dosyaYolu,satirNo,"Bilinmeyen satir: "+anahtar);
	}
	
}
private void boyutIsle(String[] parcalar,int satirNo) throws DosyaHatalari {
	if(boyutOkundu) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"BOYUT 2 kez tanimlanmis!");
	}
	if(parcalar.length!=3) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"BOYUT satiri 'BOYUT  g y' biciminde olmali!");
		
	}
	int g=sayiOku(parcalar[1],satirNo,"BOYUT genisligi");
	int y=sayiOku(parcalar[2],satirNo,"BOYUT yuksekligi");
	if(g<=0 || y<=0) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"BOYUT degerleri pozitif olmalidir!");
	}
	genislik=g;
	yukseklik=y;
	boyutOkundu=true;
}
private void enerjiIsle(String[] parcalar,int satirNo) throws DosyaHatalari {
	if(enerjiOkundu) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"ENERJI 2 kez tanimlanmis!");
	}
	if(parcalar.length!=2) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"ENERJI satiri 'ENERJI e' biciminde olmali!");
	}
	int deger=sayiOku(parcalar[1],satirNo,"ENERJI degeri");
	if(deger<0) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"ENERJI negatif olamaz: "+deger);
	}
	enerji=deger;
	enerjiOkundu=true;
}
private void robotIsle(String[] parcalar,int satirNo) throws DosyaHatalari {
	if(parcalar.length!=3){
		throw new DosyaHatalari(dosyaYolu,satirNo,"ROBOT satiri 'ROBOT n GOVDE' biciminde olmali!");
	}
	int no=sayiOku(parcalar[1],satirNo,"ROBOT numarasi");
	if(no<Dunya.MIN_NO || no>Dunya.MAX_NO) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"ROBOT numarasi "
                + Dunya.MIN_NO + "-" + Dunya.MAX_NO + " arasinda olmali: " + no);
	}
	if(robotGovdeleri[no]!=null) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"ROBOT " +no
                + " iki kez tanimlanmis (ilk tanimlanmis satir " +robotSatirlari[no]+")");
		
	}
	Govde govde=govdeBul(parcalar[2]);
	if(govde==null) {
		throw new DosyaHatalari(dosyaYolu,satirNo,"bilinmeyen govde: "+ parcalar[2]);
	}
	robotGovdeleri[no]=govde;
	robotSatirlari[no]=satirNo;
	
}
private int sayiOku(String metin,int satirNo,String sorun) throws DosyaHatalari{
	try{
		return Integer.parseInt(metin);
	}catch(NumberFormatException e) {
		throw new DosyaHatalari(dosyaYolu,satirNo,sorun +"sayi olmali: "+metin);
	}
}
public void ozetYaz() {
    if (dunya == null) {
        throw new IllegalStateException("Once oku() cagrilmalidir.");
    }
    for (Robot robot : dunya.robotDizisi()) {
        System.out.println("ROBOT " + robot.getNumara() + " " + robot.getGovde().ad()
                + " KONUM " + robot.getKonum() + " ENERJI " + robot.getEnerji());
    }
}
private Govde govdeBul(String ad) {
	return turKayitlari.govdeBul(ad);
}

}







